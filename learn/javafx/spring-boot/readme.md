## JavaFX and Spring Boot

Spring Boot gives you a component model, dependency injection, an event bus, an environment
abstraction for configuration, resource loading, internationalization and a very large ecosystem
of ready-made integrations. JavaFX gives you a modern UI toolkit for the desktop. There is no
reason why you cannot have both in the same application.

In this tutorial, based on the video by [**Josh Long**](https://www.jfx-central.com/people/j.long), we build a small JavaFX desktop application
that is fully managed by Spring, and we finish by compiling it to a GraalVM native image so that
the end user only needs to download one binary - no JRE required.

> "*I think there's a sort of existence proof that there is a source or need for fast, interesting,
> viable desktop applications that do something more elegantly, more efficiently than Electron.*"

![alt](youtube:Od2NDwHED58)

In a [follow-up tutorial](https://www.jfx-central.com/learn-javafx/spring-boot-oauth) the same application is secured with
Spring Security and OAuth 2.0.

### Generating the project

Start from [start.spring.io](https://start.spring.io) and pick the dependencies you need. For this
example: **OAuth2 Client** (used in the next tutorial), **Spring Web**, the **HTTP client** support,
**Spring Boot DevTools** and **GraalVM Native Support**. Startup time and RAM footprint matter a lot
for a user-facing application, which is why AOT and native image support are in the list from the
start.

The JavaFX dependencies are not offered by the Spring Initializr, so we add them ourselves.

```xml
<properties>
    <javafx.version>26.0.2</javafx.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>${javafx.version}</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>${javafx.version}</version>
    </dependency>
</dependencies>
```

`javafx-controls` and `javafx-fxml` go hand in hand here, but they are not the same thing: the first
one brings the controls, the second one the FXML loader.

### Starting Spring and JavaFX in the right order

Both frameworks have a claim on `public static void main`. JavaFX wants to own the launch of the
application, Spring wants to bootstrap the application context. The solution is to let JavaFX start
first, and to create the Spring application context inside `init()`, before the stage exists.

Two things are important here:

* `headless(false)` - by default Spring Boot starts a web application in headless mode, but we very
  much need the graphics subsystem.
* Once the JavaFX stage is available, we publish a Spring event so that any interested Spring
  component can get a reference to it. That keeps the UI code decoupled from the bootstrap code.

```java
@SpringBootApplication
public class DesktopApplication extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(DesktopApplication.class)
                .headless(false)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) {
        this.context.publishEvent(new StageInitializedEvent(stage));
    }
}
```

The event itself is a plain Spring `ApplicationEvent`.

```java
public class StageInitializedEvent extends ApplicationEvent {

    public StageInitializedEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
```

It would be perfectly fine in a demo this size to put all of this next to each other, but as the
program grows, that coupling becomes too much. With the event in place, anything in the Spring
context can react to the stage being ready.

### Describing the UI in FXML

JavaFX has a long history. Sun originally wanted to catch up with Flash and its scene-based
animation model, and built a separate language, JavaFX Script, on top of the JDK. With JavaFX 2.0
that language disappeared and JavaFX became a Java library, which gave Swing developers a smooth
path forward. Today JavaFX lives outside the JDK as the open source [OpenJFX](https://openjfx.io/)
project.

Just like Flash had MXML in Adobe's Flex programming model, JavaFX has **FXML**: you describe scenes
and user interfaces declaratively instead of assembling widgets by hand. Create
`src/main/resources/fxml/UI.fxml` with a label, two buttons and a text area, and give each of them
an `fx:id`. Stylesheets work too, so you can add a `styles.css` next to it.

### Loading the FXML from a Spring component

The listener for our event is an ordinary Spring bean. It uses the Spring Framework resource
abstraction to find the FXML file on the classpath, hands its input stream to the `FXMLLoader`, and
puts the resulting object graph into a scene.

```java
@Component
public class StageInitializer implements ApplicationListener<StageInitializedEvent> {

    private final Environment environment;

    private Label greeting;
    private Button signIn, call;
    private TextArea output;

    public StageInitializer(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(StageInitializedEvent event) {
        var fxml = new ClassPathResource("fxml/UI.fxml");
        var loader = new FXMLLoader();

        Parent root;
        try (var in = fxml.getInputStream()) {
            root = loader.load(in);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        var stage = event.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
```

Do not forget `stage.show()`. If the stage is not visible, you will not see anything.

### Getting hold of the components

The FXML loader creates the components programmatically, so we look them up by their ID - much like
`getElementById` in the DOM - and then give them their business behaviour.

```java
this.greeting = (Label) root.lookup("#greeting");
this.output = (TextArea) root.lookup("#output");
this.signIn = (Button) root.lookup("#signIn");
this.call = (Button) root.lookup("#call");
```

JavaFX can also inject these fields for you through a controller, but in this design Spring is the
dependency injection container, and a little bit of service locator code inside a widget is fine -
it is all internal state anyway.

### Using the Spring environment

Two small but useful details. First, a JavaFX window closing does not stop the JVM by itself, so we
do that explicitly. Second, the window title comes from the Spring `Environment`, which means it can
live in `application.properties` together with the rest of your configuration (and the same trick
works with Spring's resource bundle support for internationalization).

```java
stage.setOnHidden(_ -> System.exit(0));
stage.setTitle(environment.getProperty("app.title"));
```

```properties
app.title=Beautiful JavaFX
```

### Compiling to a native image

Do you have to ship a JRE with this? No. GraalVM needs some hints to understand JavaFX - reflection,
resources, and so on - and Josh Long maintains an open source library with exactly those hints,
available on Maven Central.

```shell
./mvnw -DskipTests native:compile
```

The result in the `target` directory is a single binary of roughly 113 MB that starts immediately,
loads the properties and the FXML, and needs nothing else to be distributed.

At this point you have a native, Spring-lifecycle-managed JavaFX application. The next step is
authentication, which is covered in the
[second tutorial](https://www.jfx-central.com/learn-javafx/spring-boot-oauth).
