Shapes provided by this library do not extend the standard JavaFX `Shape` class given that that particular type 
is closed for extension, but these shapes provide a pair of methods that can return a `Shape` instance ready to 
be used. These methods are

```
Shape getShape()
ObjectProperty<Shape> shapeProperty()
```