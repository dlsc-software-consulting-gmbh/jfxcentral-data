BootstrapFX is a partial port of [Twitter Bootstrap](http://getbootstrap.com/) for JavaFX. It mainly provides a CSS
stylesheet that closely resembles the original while being custom tailored for JavaFX's unique CSS flavor.

It's worth mentioning that Twitter Bootstrap delivers more than just a standardized look for common widgets. It also
provides new widgets, behavior and a grid system. Some of these features may be ported at a later stage to BootstrapFX.

## Installing

You can get the latest version of **BootstrapFX** from Maven Central.

gradle
```
repositories {
    mavenCentral()
}

dependencies {
    implementation '{project-group}:{project-name}-core:{project-version}'
}
```

Maven
```
<dependencies>
    <dependency>
        <groupId>{project-group}</groupId>
        <artifactId>{project-name}-core</artifactId>
        <version>{project-version}</version>
    </dependency>
</dependencies>
```

Once the `bootstrapfx-core` dependency is in your classpath you just need to apply the `boostrapfx.css` stylesheet to
a scene, for example

```
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Sampler extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {              // <1>
        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                       // <2>
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        Button button = new Button("Hello BootstrapFX");
        button.getStyleClass().setAll("btn","btn-danger");                // <2>
        content.setCenter(button);
        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());  // <3>

        primaryStage.setTitle("BootstrapFX");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
```

<1> Custom widget from BootstrapFX
<2> Apply CSS class to widgets
<3> Apply BootstrapFX stylesheet to scene

## Supported CSS Classes

### Text

* b, strong
* i, em, italic, dfn
* small
* code, kbd, pre, samp
* h1, h2, h3, h4, h5, h6
* lead
* p
* text-mute
* text-primary, text-success, text-info, text-warning, text-danger
* bg-primary, bg-success, bg-info, bg-warning, bg-danger

### Buttons

* btn
* btn-default, btn-primary, btn-success, btn-info, btn-warning, btn-danger
* btn-lg, btn-sm, btn-xs

### SplitMenu Buttons

* split-menu-btn
* split-menu-btn-default, split-menu-btn-primary, split-menu-btn-success, split-menu-btn-info, split-menu-btn-warning, split-menu-btn-danger
* split-menu-btn-lg, split-menu-btn-sm, split-menu-btn-xs

### Labels

* lbl
* lbl-default, lbl-primary, lbl-success, lbl-info, lbl-warning, lbl-danger

### Panels

* panel
* panel-default, panel-primary, panel-success, panel-info, panel-warning, panel-danger
* panel-heading
* panel-title
* panel-body
* panel-footer

### Alerts

* alert
* alert-success, alert-info, alert-warning, alert-danger

### Groups

* btn-group-horizontal
* btn-group-vertical

> All elements inside the vertical button group must have the same width.

### Progress Bars

* progress-bar-primary
* progress-bar-success
* progress-bar-info
* progress-bar-warning
* progress-bar-danger

### Tooltips

* tooltip-primary
* tooltip-success
* tooltip-info
* tooltip-warning
* tooltip-danger

### Miscellaneous

* badge