A library for creating fully customizable JavaFX stages. Includes a sampler module with key usage examples.

## Features

- Fully customizable title bar with dynamic configuration changes
- Buttons placeable on left or right; two maximize button policies
- CSS styling with dark mode support and SVG-based button icons
- Only two events triggered during resizing (start and finish)

## Usage

```java
@Override
public void start(Stage stage) {
    var controller = new StandardStageController(stage, 800, 600);
    controller.setContent(new VBox(...));
    stage.show();
}
```