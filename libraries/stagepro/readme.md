# StagePro

Techsenger StagePro is a library that allows you to create custom stages with nearly any configuration, while 
remaining easy to use. The project also includes a sampler module, featuring key samples to help you get started with 
the library.

## Features

Key features include:

* Fully customizable title bar configurations.
* Support for dynamic configuration changes.
* Ability to place basic buttons on either the left or right side.
* Two maximize button policies.
* Styling via CSS.
* Dark mode support.
* SVG-based button icons.
* Only two events triggered during resizing (start and finish).

## Usage

To create a standard Stage, use the following code:

```java
@Override
public void start(Stage stage) {
    var controller = new StandardStageController(stage, 800, 600);
    var content = new VBox(...);
    controller.setContent(content);
    stage.show();
}
```