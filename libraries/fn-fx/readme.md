## fn-fx

fn-fx is a functional Clojure API around JavaFX that takes inspiration from React's virtual DOM model. Instead of building a mutable scene graph, developers describe the UI as immutable Clojure data, and fn-fx computes and applies the minimal set of changes needed to update the actual JavaFX scene.

The library auto-generates Clojure wrappers from the JavaFX class hierarchy so that all JavaFX controls are accessible as plain Clojure maps. Event handlers are simple functions, and the entire state of the UI can be represented as data that can be inspected, serialized, and replayed.

fn-fx is ideal for Clojure developers who want to leverage the full power of JavaFX while maintaining a pure functional programming style.
