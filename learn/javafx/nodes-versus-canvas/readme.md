In JavaFX you can add as many visual components as you need to the screen. But depending on the performance of the system your application is running on, you can see a drop in the responsiveness if you add too much components. Of course, having a vert high number of visual components in a typical JavaFX user interface would be a badly designed application. Imagine a long registration form with thousands of input fields and labels... it would drive your users crazy. 

But what if you want to create some kind of animation or a custom component which requires a lot of graphical elements? That's something you can do with the Canvas, as explained in [the tutorial "Getting Started with JavaFX
Canvas"](https://www.jfx-central.com/learn-javafx/canvas). 

In this article, we compare the result of an application with thousands of Nodes, versus the same number of elements being drawn on a Canvas.

![alt](youtube:nJGRW5xP_AE)

## Node versus Canvas

In JavaFX, both Node and Canvas are part of the scene graph but they have different use cases. The choice between the two often depends on the specific needs of your application. You use Nodes for static content like input forms, data tables, dashboards with graphs,... This is usually more convenient and efficient. The Canvas gives you more flexibility when you need to generate dynamic or custom content.

### JavaFX Node

`javafx.scene.Node` is the base class and all visual JavaFX components extend it. That goes several "layers" deep. For instance, for a Button > ButtonBase > Labeled > Control > Region > Parent > Node.

Summarized:

* A Node in JavaFX represents an element of the scene graph.
* This includes UI controls like buttons, labels, text fields, shapes, images, media, embedded web browsers, etc.
* Each Node can be positioned and transformed in 3D space, can handle events, and can have effects applied to it.
* Node is a base class for all visual items.
* Using Nodes is known as "retained mode rendering".

These are a few typical components that extend from Node:

```java
Label label = new Label("Hello World!");
Button button = new Button("Click Me!");
```

### JavaFX Canvas

`javafx.scene.canvas` also extends Node, with special functionality. You can draw your own content on the Canvas using a set of graphics commands provided by a `GraphicsContext`.

Summarized:

* You draw on a Canvas with a GraphicsContext.
* Direct drawing to a Canvas is known as "immediate mode rendering".
* This gives you more flexibility but is less efficient if the content does not change often.

This is an example that draws a rectangle:

```java
Canvas canvas = new Canvas(400, 300);
GraphicsContext gc = canvas.getGraphicsContext2D();
gc.setFill(Color.BLUE);
gc.fillRect(50, 50, 100, 70);
```

## Demo Code

The demo application can be found in [this GitHub Gist](https://gist.github.com/FDelporte/c74cdf59ecd9ef1b14df86e08faa0c56). The value at the beginning of the code defines which approach is used:

```java
private static int TYPE_OF_TEST = 1; // 1 = Nodes, 2 = Canvas
```

### Using Nodes

When you use Nodes, a Pane is added to the screen in which balls gets added. Each ball is a Circle Node with a move method:

```java
class BallNode extends Circle {
    private final Color randomColor = Color.color(Math.random(), Math.random(), Math.random());
    private final int size = r.nextInt(1, 10);
    private double dx = r.nextInt(1, 5);
    private double dy = r.nextInt(1, 5);

    public BallNode() {
        this.setRadius(size / 2);
        this.setFill(randomColor);
        relocate(r.nextInt(380), r.nextInt(620));
    }

    public void move() {
        if (hitRightOrLeftEdge()) {
            dx *= -1; // Ball hit right or left wall, so reverse direction
        }
        if (hitTopOrBottom()) {
            dy *= -1; // Ball hit top or bottom, so reverse direction
        }
        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);
    }

    private boolean hitRightOrLeftEdge() {
        return (getLayoutX() < (scene.getX() + getRadius())) ||
            (getLayoutX() > (scene.getWidth() - getRadius()));
    }

    private boolean hitTopOrBottom() {
        return (getLayoutY() < (scene.getY() - getRadius())) ||
            (getLayoutY() > (scene.getHeight() - getRadius() - 60));
    }
}
```

### Using Canvas

When you use the Canvas, each Ball is a data object, and all balls get drawn on the Canvas at every tick:

```java
class BallDrawing {
    private final Color fill = Color.color(Math.random(), Math.random(), Math.random());
    private final int size = r.nextInt(1, 10);
    private double x = r.nextInt(APP_WIDTH);
    private double y = r.nextInt(APP_HEIGHT - TOP_OFFSET);
    private double dx = r.nextInt(1, 5);
    private double dy = r.nextInt(1, 5);

    public void move() {
        if (hitRightOrLeftEdge()) {
            dx *= -1; // Ball hit right or left wall, so reverse direction
        }
        if (hitTopOrBottom()) {
            dy *= -1; // Ball hit top or bottom, so reverse direction
        }
        x += dx;
        y += dy;
    }

    private boolean hitRightOrLeftEdge() {
        return (x < (scene.getX() + size)) ||
            (x > (scene.getWidth() - size));
    }

    private boolean hitTopOrBottom() {
        return (y < (scene.getY() - size)) ||
            (y > (scene.getHeight() - size - 60));
    }

    // Getters
}
```

### Moving the Objects

The application uses a Timeline to add more objects, and move them, every five milliseconds:

```java
Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5), t -> onTick()));
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();

private void onTick() {
    if (TYPE_OF_TEST == 1) {
        // Add ball nodes to the pane
        for (var i = 0; i < ADD_BALLS_PER_TICK; i++) {
            paneBalls.getChildren().add(new BallNode());
        }

        // Move all the balls in the pane
        for (Node ballNode : paneBalls.getChildren()) {
            ((BallNode) ballNode).move();
        }
    } else if (TYPE_OF_TEST == 2) {
        // Add balls to the list of balls to be drawn
        for (var i = 0; i < ADD_BALLS_PER_TICK; i++) {
            ballDrawings.add(new BallDrawing());
        }
        
        // Clear the canvas (remove all the previously balls that were drawn)
        context.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

        // Move all the balls in the list, and draw them on the Canvas
        for (BallDrawing ballDrawing : ballDrawings) {
            ballDrawing.move();
            context.setFill(ballDrawing.getFill());
            context.fillOval(ballDrawing.getX(), ballDrawing.getY(), ballDrawing.getSize(),  ballDrawing.getSize());
        }
    } 
}
```

### Executing the Applications

You can use the following approach to run this demo application:

* Save the code to a file `FxNodesVersusCanvas.java`.
* Install a Java runtime with JavaFX, e.g. from [Azul Zulu](https://www.azul.com/downloads/?package=jdk-fx#zulu) or with [SDKMAN](https://sdkman.io/).
* Install J'BANG!, either from [jbang.dev](https://www.jbang.dev/) or with SDKMAN (`sdk install jbang`).
* Start the application with `jbang FxNodesVersusCanvas.java`.

## Conclusion

As you can see in the video, with this example, you can add roughly 10 times more objects to the Canvas before the framerate drops, compared to the number of Nodes. This is not a "scientific result" at all, but it gives a good impression of what can be achieved by using Canvas.