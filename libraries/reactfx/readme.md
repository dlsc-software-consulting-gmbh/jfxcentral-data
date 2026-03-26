## ReactFX

ReactFX is a library that extends JavaFX with reactive event streams and enriched observable values. It provides a fluent API for creating, transforming, and combining event streams, making it possible to express complex asynchronous event logic as readable pipeline declarations.

The library introduces `EventStream`, a push-based alternative to JavaFX's `Observable`, along with combinators like `map`, `filter`, `merge`, `zip`, and `accumulate`. It also provides `Val` and `Var` — enhanced observable values that support lazy evaluation and a richer set of transformations than standard JavaFX properties.

ReactFX is used as a foundation by several other FXMisc libraries such as RichTextFX and UndoFX, and is a great building block for applications that need fine-grained control over event processing.
