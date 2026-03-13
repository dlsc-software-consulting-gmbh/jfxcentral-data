A JavaFX equivalent of Swing's `JDesktopPane` — a container for MDI-style internal windows (like `JInternalFrame`). Forked from [JavaFXMDI](https://github.com/lincolnminto/javaFXMDI) by Lincoln Minto.

## Usage

```java
DesktopPane desktopPane = new DesktopPane();

int[] count = {0};
Button newWindow = new Button("New Window");
newWindow.setOnAction(e -> {
    InternalWindow window = new InternalWindow(
        "window-" + count[0],
        new FontIcon("mdi-application:20"),
        "Title " + count[0]++,
        new Label("Content"));
    desktopPane.addInternalWindow(window);
});

BorderPane mainPane = new BorderPane();
mainPane.setPrefSize(800, 600);
mainPane.setTop(newWindow);
mainPane.setCenter(desktopPane);

stage.setScene(new Scene(mainPane));
stage.show();
```
