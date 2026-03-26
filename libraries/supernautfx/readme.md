## SupernautFX

SupernautFX is a lightweight dependency injection framework for JavaFX applications built on top of the Micronaut framework. It provides the conveniences of full Spring-style DI — `@Singleton`, `@Inject`, `@Value`, configuration files — without the heavy runtime overhead of Spring Boot.

The framework starts the Micronaut application context and uses it to instantiate and wire the JavaFX `Application` and its primary controller, making it straightforward to inject services, repositories, and configuration into the JavaFX layer.

SupernautFX is ideal for developers who want production-quality dependency injection in a lightweight package, particularly for desktop applications where startup time and memory footprint matter.
