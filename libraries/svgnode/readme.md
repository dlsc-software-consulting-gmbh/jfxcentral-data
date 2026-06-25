SvgNode is a lightweight, optimized JavaFX node for rendering SVG paths at any size. It fully supports FXML, property binding, and CSS styling.

## Features

- Render any SVG path as a JavaFX node
- No dependencies – uses your provided JavaFX runtime
- Optimized for a tiny footprint by extending from `Parent`, skipping size calculations and only initializing properties when needed
- Uniform rasterization with a single `size` property
- FXML-compatible with attribute and constant-based usage
- CSS-stylable via the `.svg-node` and `.svg` style classes; by default the SVG automatically adjusts its color based on the background – just like text!

It also works with SVG libraries that provide their icons as paths, such as
[SVG-MaterialDesign](https://github.com/Maran23/svg-materialdesign),
[SVG-Bootstrap](https://github.com/Maran23/svg-bootstrap), and
[SVG-FontAwesome](https://github.com/Maran23/svg-fontawesome).

## Usage

```java
import tools.maran.svgnode.SvgNode;

// Default size (24px)
SvgNode defaultIcon = new SvgNode("M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z");

// Icon with a size and color
SvgNode icon32 = new SvgNode("M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z", 32);
icon32.setSvgColor(Color.RED);

// Binding
SvgNode dynamic = new SvgNode();
dynamic.pathProperty().bind(viewModel.iconPathProperty());
dynamic.sizeProperty().bind(slider.valueProperty());
```

### FXML

```xml
<?import tools.maran.svgnode.SvgNode?>

<SvgNode path="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z" size="32" svgColor="RED" />
```

## Requirements

| Dependency | Version |
|------------|---------|
| Java       | 25+     |
| JavaFX     | 25+     |
