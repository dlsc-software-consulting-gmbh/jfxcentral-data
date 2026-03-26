## afterburner.fx

afterburner.fx is a minimalistic MVP (Model-View-Presenter) framework for JavaFX created by Adam Bien. It leverages JavaFX's FXML support and the convention-over-configuration principle to wire together views, presenters, and CSS files automatically, with zero XML configuration.

The framework provides field-level dependency injection using `@Inject` without requiring a heavyweight DI container. By following naming conventions (e.g., `UserView.fxml`, `UserPresenter.java`, `user.css`), the framework auto-discovers and links all the pieces together.

afterburner.fx is designed to be opinionated but extremely lightweight — the entire framework fits in a handful of classes — making it easy to understand, learn, and integrate into new or existing JavaFX applications.
