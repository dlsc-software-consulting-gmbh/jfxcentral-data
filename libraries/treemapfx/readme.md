# TreeMapFX

A simple and flexible _"tree map"_ chart control for JavaFX.

## Usage

The `TreeMapPane` has two properties that are set in the constructor that determine how content is rendered,
but can also be configured on the fly:

- `ObjectProperty<Function<T, Node>> nodeFactoryProperty()`
- `ObjectProperty<ToDoubleFunction<T>> sizeFunctionProperty()`

The items shown in the `TreeMapPane` are also represented as a property:

- `ListProperty<T> valueListProperty()`

## Basic Example

```java
public class App extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final Random r = new Random(1);

    @Override
    public void start(Stage stage) {
        // The tree-map pane can represent any 'T' value, so long as you provide two things:
        //  1. ToDoubleFunction<T> to compute the 'size' or 'weight' of values
        //  2. Function<T, Node> to create Node representations of 'T' values
        //
        // In this example we'll represent a list of strings (of integers)
        List<String> values = Stream.of(1, 1, 1, 1, 2, 2, 2, 2, 2, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 7, 7, 14, 16, 30, 80)
                .map(String::valueOf)
                .collect(Collectors.toList());
        
        // The 'size' conversion is a simple 'parseInt' on the string.
        //  - Larger int values will appear as bigger rectangles
        // The Node mapping creates a label that shows the number, and a random background color to differentiate boxes.
        TreeMapPane<String> pane = new TreeMapPane<>(Integer::parseInt, text -> {
            Label label = new Label(text);
            label.setStyle("-fx-background-color: " + String.format("#%06x", r.nextInt(0xffffff + 1)) + "; " +
                    "-fx-background-radius: 0; -fx-border-width: 0.5; -fx-border-color: black;");
            label.setAlignment(Pos.CENTER);
            return label;
        });
        
        // Add the values to the tree-map
        pane.valueListProperty().addAll(values);
        
        // Create basic layout and show it
        BorderPane root = new BorderPane(pane);
        root.setStyle("-fx-background-color: black");
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
```

## Hierarchical Example

```java
public class DemoHierarchicalContent extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    @Override
    public void start(Stage stage) {
		// Model the 'src' directory as hierarchal data (directory/file sizes)
        Path src = Paths.get("src");
        TreeMapPane<TreeContent> pane = TreeMapPane.forTreeContent();
        pane.valueListProperty().addAll(hierarchyFromPath(src));

        // Create basic layout and show it
        BorderPane root = new BorderPane(pane);
        root.setStyle("-fx-background-color: black");
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    @Nonnull
    private List<TreeContent> hierarchyFromPath(@Nonnull Path path) {
        try {
            if (Files.isDirectory(path)) {
                ListProperty<TreeContent> children = new SimpleListProperty<>(observableArrayList(Files.list(path)
                        .flatMap(p -> hierarchyFromPath(p).stream())
                        .toList()));
                return Collections.singletonList(new SimpleHierarchicalTreeContent(children));
            } else {
                long size = Files.size(path);
                Label label = createLabel(path.getFileName().toString(), path.getParent(), size);
                return Collections.singletonList(new TreeContent() {
                    @Override
                    public double getValueWeight() {
                        return size;
                    }

                    @Nonnull
                    @Override
                    public Node getNode() {
                        return label;
                    }
                });
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
```
