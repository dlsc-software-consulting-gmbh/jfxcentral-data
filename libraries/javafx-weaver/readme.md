## javafx-weaver

javafx-weaver is a library that integrates Spring Boot's dependency injection and component model with JavaFX's FXML view system. It allows Spring-managed beans to be used as FXML controllers directly, eliminating the gap between the two frameworks and enabling full Spring features (transaction management, AOP, data access, etc.) inside JavaFX controllers.

The library provides a `FxWeaver` component that loads FXML views and resolves controllers from the Spring application context. It also supports view caching, nested views, and a convenient Spring Boot auto-configuration starter for zero-boilerplate setup.

javafx-weaver is the recommended approach for building enterprise-quality JavaFX applications that leverage the full power of the Spring ecosystem.
