DesktopPaneFX is a JavaFX version of Swing's JDesktopPane which can be used as a container for individual "child" similar to JInternalFrames.

![Screenshot](desktoppanefx.png "Screenshot")

## Installing

You can get the latest version of **DesktopPaneFX** directly from the [JCenter](https://bintray.com) repository or Maven Central.

Gradle
```
repositories {
    jcenter()
}

dependencies {
    implementation 'org.kordamp.desktoppanefx:desktoppanefx-core:0.15.0'
}
```

Maven
```
<dependencies>
    <dependency>
        <groupId>org.kordamp.desktoppanefx</groupId>
        <artifactId>desktoppanefx-core</artifactId>
        <version>0.15.0</version>
    </dependency>
</dependencies>
```

## Example

```
package com.acme;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.desktoppanefx.scene.layout.DesktopPane;
import org.kordamp.desktoppanefx.scene.layout.InternalWindow;
import org.kordamp.ikonli.javafx.FontIcon;

public class Example extends Application {
private static int count = 0;

    @Override
    public void start(Stage stage) throws Exception {
        DesktopPane desktopPane = new DesktopPane();
        Button newWindow = new Button("New Window");
        newWindow.setOnAction(e -> {
            InternalWindow window = new InternalWindow(
                "window-" + count,
                new FontIcon("mdi-application:20"),
                "Title " + count++,
                new Label("Content"));
            desktopPane.addInternalWindow(window);
        });

        BorderPane mainPane = new BorderPane();
        mainPane.setPrefSize(800, 600);
        mainPane.setTop(newWindow);
        mainPane.setCenter(desktopPane);

        stage.setScene(new Scene(mainPane));
        stage.show();
    }
}
```

## Incubating Features

The following features can be activated by defining a System property with a `"true"` value:

| Property                         | Description                                                      |
|----------------------------------|------------------------------------------------------------------|
| desktoppanefx.detachable.windows | `InternalWindow` may be detached/attached from/to a `DesktopPane`|