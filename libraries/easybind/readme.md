## EasyBind

EasyBind is a JavaFX library that makes it easy to create custom bindings using lambda expressions. JavaFX's built-in `Bindings` utility class requires verbose anonymous class boilerplate for complex bindings; EasyBind replaces all of that with concise functional expressions.

The library provides `EasyBind.map`, `EasyBind.combine`, and `EasyBind.select` methods that accept lambdas and return fully functional observable values. It also supports monadic operations for optional bindings and flat-mapping over nested properties.

EasyBind is a small but high-value utility library that significantly improves the readability and conciseness of data-binding code in JavaFX applications.
