# Lottie4J :: Java library to handle Lottie files

The aim is to create Java libraries to handle and play Lottie files with Java.

Further technical information about this project can be found on [lottie4j.com](https://lottie4j.com).

## What is Lottie?

[Lottie](https://lottiefiles.com/what-is-lottie) is a JSON-based animation file format that enables designers to ship
animations on any platform as easily as shipping static assets. Created by Airbnb, Lottie has become the industry
standard for vector animations on mobile and web.

More info is available on [lottie4j.com/lottie-format](https://lottie4j.com/lottie-format/).

## Showing a Lottie Animation in a JavaFX Application

Add the dependency in your `pom.xml`:

```xml

<dependency>
    <groupId>com.lottie4j</groupId>
    <artifactId>fxplayer</artifactId>
    <version>${lottie4j.version}</version>
</dependency>
```

This is the minimal code needed to display a Lottie animation.

```java
import com.lottie4j.core.loader.LottieFileLoader;
import com.lottie4j.core.model.Animation;
import com.lottie4j.fxplayer.LottiePlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class DemoApplication extends Application {

    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        File lottieFile = new File("PATH_OF_LOTTIE_FILE.json");

        Animation animation = LottieFileLoader.load(lottieFile);

        var scene = new Scene(new LottiePlayer(animation),
                animation.width() != null ? animation.width() : 500,
                animation.height() != null ? animation.height() : 500
        );
        stage.setTitle(lottieFile.getName());
        stage.setScene(scene);
        stage.show();
    }
}
```

## Structure of the sources

### Implementation Libraries

* `core`
    * Java objects for the Lottie data model.
    * Reading and writing of Lottie files.
* `fxplayer`
    * JavaFX component to play Lottie animations.
    * Uses the `core` library.

### Demo application

* `fxfileviewer`
    * JavaFX application to show a Lottie animation.
    * Visualizes the structure of the Lottie file.
    * Uses `core` and `fxplayer` libraries.
    * This repo contains test files in `src/main/resources`, of which some were downloaded
      as [free samples from lottiefiles.com](https://lottiefiles.com/featured-free-animations).
