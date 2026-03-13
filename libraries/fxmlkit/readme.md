**FxmlKit = Automatic FXML Loading + Hot Reload + Optional Dependency Injection**

A modern JavaFX FXML framework that eliminates boilerplate, provides FXML/CSS hot reload, and optional dependency injection.


---

## Features

| Feature | Native JavaFX | FxmlKit |
|---------|---------------|---------|
| Hot Reload (FXML + CSS) | ❌ Restart required | ✅ Instant refresh |
| fx:include Hot Reload | ❌ None | ✅ Full support |
| User Agent Stylesheet Hot Reload | ❌ None | ✅ All levels |
| Automatic FXML Loading | ❌ Manual code | ✅ Zero-config |
| Automatic Stylesheet Attachment | ❌ Manual code | ✅ Auto-attach |
| Controller Dependency Injection | ⚠️ Manual factory | ✅ Automatic |
| FXML Node Injection | ❌ Nearly impossible | ✅ @FxmlObject |
| @PostInject Lifecycle | ❌ None | ✅ Supported |
| JPro Multi-user Isolation | ❌ Manual | ✅ Native support |

---

## Quick Start

**1. FXML** (`src/main/resources/com/example/HelloView.fxml`):
```xml
<VBox xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.example.HelloController"
      spacing="10" alignment="CENTER">
    <Label fx:id="messageLabel" text="Hello, FxmlKit!"/>
    <Button text="Click Me" onAction="#handleClick"/>
</VBox>
```

**2. Controller** (`HelloController.java`):
```java
public class HelloController {
    @FXML private Label messageLabel;
    @FXML private void handleClick() { messageLabel.setText("Clicked!"); }
}
```

**3. View** (`HelloView.java`):
```java
public class HelloView extends FxmlView<HelloController> { }
```

**4. Application:**
```java
@Override
public void start(Stage stage) {
    stage.setScene(new Scene(new HelloView()));
    stage.show();
}
```

Place `HelloView.css` alongside the FXML and it is automatically attached.

---

## Usage

### Method 1: Zero Configuration

```java
public class MainView extends FxmlView<MainController> { }
stage.setScene(new Scene(new MainView()));
```

Automatic FXML loading, stylesheet attachment, and controller creation — no DI required.

### Method 2: Global Dependency Injection

```java
Injector injector = Guice.createInjector(new AppModule());
FxmlKit.setDiAdapter(new GuiceDiAdapter(injector));  // Set once, use everywhere

LoginView view = new LoginView();  // Controller receives injection automatically
```

### Method 3: Per-Instance DI (JPro / isolated containers)

```java
Injector userInjector = Guice.createInjector(new UserModule(userId));
LoginView view = new LoginView(new GuiceDiAdapter(userInjector));
```

Useful for JPro web apps (one container per user session) or per-Tab/Window isolation.

### Reactive Controller API

```java
// FxmlView — eager loading
MainView view = new MainView();
MainController ctrl = view.getController();  // Available immediately
view.controllerProperty().addListener((obs, old, c) -> c.refreshData());  // Reacts to hot reload

// FxmlViewProvider — lazy loading
MainViewProvider provider = new MainViewProvider();
Parent node = provider.getView();             // FXML loaded here
MainController ctrl = provider.getController(); // Available after getView()
```

---

## Hot Reload

```java
@Override
public void start(Stage stage) {
    FxmlKit.enableDevelopmentMode();  // Call BEFORE creating any views
    stage.setScene(new Scene(new MainView()));
    stage.show();
}
```

| File Type | Behavior | Runtime State |
|-----------|----------|---------------|
| `.fxml` | Full view reload | Lost |
| `.css` / `.bss` | Stylesheet refresh | **Preserved** |

`fx:include` changes (add/remove/edit child FXML) automatically propagate to the parent view. All nesting levels are monitored.

**User Agent Stylesheet hot reload:**
```java
// Scene/SubScene: works automatically with native JavaFX API
scene.setUserAgentStylesheet("/styles/theme.css");

// Application level — use FxmlKit's bridged property instead:
FxmlKit.setApplicationUserAgentStylesheet("/styles/dark-theme.css");
```

**Custom control UA stylesheets** (opt-in — promotes stylesheet to author scope, may affect cascade):
```java
FxmlKit.enableDevelopmentMode();
FxmlKit.setControlUAHotReloadEnabled(true);  // Only enable during development
```

**Fine-grained control:**
```java
FxmlKit.setFxmlHotReloadEnabled(true);
FxmlKit.setCssHotReloadEnabled(true);
FxmlKit.disableDevelopmentMode();
```

**Production:** Guard with a JVM flag to avoid hot reload in production:
```java
if (Boolean.getBoolean("dev.mode")) FxmlKit.enableDevelopmentMode();
// Development: java -Ddev.mode=true -jar myapp.jar
```

**File change detection:** near-instant on Windows/Linux; ~2 s on macOS.

---

## Core Concepts

### File Resolution Convention

```text
src/main/resources/com/example/
├── UserView.fxml    ← matched by UserView.java (same name, same package)
├── UserView.css     ← auto-attached
└── UserView.bss     ← binary stylesheet (takes priority over .css)
```

### FxmlView vs FxmlViewProvider

| Feature | FxmlView | FxmlViewProvider |
|---------|----------|------------------|
| Type | IS-A Node (extends StackPane) | HAS-A Node (holds Parent) |
| Loading | Eager (constructor) | Lazy (first `getView()` call) |
| Use case | Direct use as node | Deferred loading to save resources |

```java
// FxmlView
LoginView view = new LoginView();  // FXML loaded immediately
scene.setRoot(view);

// FxmlViewProvider
MainViewProvider provider = new MainViewProvider();
scene.setRoot(provider.getView());  // FXML loaded here
```

### Injecting Custom Components

Mark custom FXML components with `@FxmlObject` to receive DI:

```java
@FxmlObject
public class StatusCard extends VBox {
    @Inject private StatusService statusService;
    @PostInject private void afterInject() { updateStatus(); }
}
```

```xml
<VBox><StatusCard /></VBox>  <!-- Injection happens automatically -->
```

FxmlKit defaults to `EXPLICIT_ONLY` strategy — only classes annotated with `@FxmlObject` receive injection. Set to `AUTO` to inject all FXML objects; exclude specific types with `FxmlKit.excludeNodeType()` or `@SkipInjection`.

---

## Annotations

### @FxmlPath
Override the default FXML file location (rarely needed):
```java
@FxmlPath("/shared/Common.fxml")
public class LoginView extends FxmlView<LoginController> {}
```

### @FxmlObject
Enable DI for any class instantiated via FXML (controls, layouts, non-visual objects):
```java
@FxmlObject
public class UserAvatar extends Circle {
    @Inject private UserService userService;
    @PostInject private void afterInject() { loadUserImage(); }
}
```

### @PostInject
Invoked after all `@Inject` fields are set. Essential for `@FxmlObject` nodes; for controllers, `initialize()` is usually sufficient since both `@Inject` and `@FXML` fields are available there.

```java
@FxmlObject
public class StatusLabel extends Label {
    @Inject private StatusService statusService;

    @PostInject
    private void afterInject() {
        setText(statusService.getStatus());  // Safe: dependency is ready
    }
}
```

Method must be no-arg; any access modifier is fine; parent-class `@PostInject` runs first.

---

## FAQ

**Must I use a DI framework?** No. `FxmlView` works standalone for automatic FXML loading and stylesheet attachment. Add DI only when you need it.

**When to use LiteDiAdapter?** For small projects and prototyping. Add `javax.inject:javax.inject:1`, then:
```java
LiteDiAdapter di = new LiteDiAdapter();
di.bindInstance(UserService.class, new UserService());
FxmlKit.setDiAdapter(di);
```
For larger projects, prefer Guice or another mature DI framework.

**Why are @Inject fields null?** Zero-config mode has no DI. Call `FxmlKit.setDiAdapter(diAdapter)` to enable injection.

**Stylesheet not working?** File name must match the class (`LoginView.css` for `LoginView.java`), be in the same resource package, and auto-attach must be enabled (default: true). `.bss` takes priority over `.css`.

**Hot reload not working?** Ensure `enableDevelopmentMode()` is called before any views are created. Enable IDE auto-build. Use `FxmlKit.setLogLevel(Level.FINE)` to see diagnostic messages.

**fx:include hot reload not working?** The parent `FxmlView` must be created after enabling FXML hot reload, and the included file must exist in the source directory.

**How to use with JPro?** Pass a per-user `DiAdapter` when constructing each view:
```java
MainView view = new MainView(new GuiceDiAdapter(userInjector));
```
See `fxmlkit-samples/tier3.multiuser` for a full example simulating multi-user sessions with TabPane.

---

## Sample Projects

The `fxmlkit-samples` module contains complete examples in three tiers:

```text
tier1/  hello, i18n, provider, viewpath, theme   # Zero configuration
tier2/  fxmlobject, guice, login                  # Dependency injection
tier3/  multiuser                                  # JPro multi-user isolation
```

---

## Acknowledgments

- **[afterburner.fx](https://github.com/AdamBien/afterburner.fx)** — Inspired the convention-over-configuration approach.
- **[CSSFX](https://github.com/McFoggy/cssfx)** — Inspired the CSS hot reload approach.
