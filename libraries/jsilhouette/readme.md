# JSilhouette

JSilhouette provides additional shapes for Java applications.

Shapes provided by this library do not extend the standard JavaFX `Shape` class given that that particular type
is closed for extension, but these shapes provide a pair of methods that can return a `Shape` instance ready to
be used. These methods are:

```
javafx.scene.shape.Shape getShape()
javafx.beans.property.ObjectProperty<javafx.scene.shape.Shape> shapeProperty()
```

## Included Shapes

* Almond
* Arrow
* Asterisk
* Astroid
* Cross
* Donut
* Lauburu
* Multiround Rectangle
* Rays
* Regular Polygon
* RoundPin
* Star