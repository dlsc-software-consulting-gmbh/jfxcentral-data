# FxmlKit

[![JFXCentral](https://img.shields.io/badge/Find_me_on-JFXCentral-blue?logo=googlechrome&logoColor=white)](https://www.jfx-central.com/libraries/fxmlkit)
[![Maven Central](https://img.shields.io/maven-central/v/com.dlsc.fxmlkit/fxmlkit?color=brightgreen)](https://search.maven.org/search?q=g:com.dlsc.fxmlkit%20AND%20a:fxmlkit)
[![Java Version](https://img.shields.io/badge/Java_Version-11+-ff69b4)](https://github.com/openjdk/jdk)
[![JavaFX Version](https://img.shields.io/badge/JavaFX_Version-11+-brightgreen)](https://github.com/openjdk/jfx)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

**FxmlKit = Automatic FXML Loading + Hot Reload + Optional Dependency Injection**

```java
// Enable FXML/CSS hot reload during development
FxmlKit.enableDevelopmentMode();

// Zero configuration - automatic FXML loading
new MainView();

// With dependency injection
new MainView(diAdapter);
```

A modern JavaFX FXML framework that eliminates boilerplate code, provides FXML/CSS hot reload, and optional progressive dependency injection support.

[中文文档](README_CN.md) | [Sample Projects](fxmlkit-samples)

---

## Table of Contents

- [Why FxmlKit](#why-fxmlkit)
- [Core Features](#core-features)
- [Acknowledgments](#acknowledgments)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Hot Reload](#hot-reload)
- [Core Concepts](#core-concepts)
- [Annotations](#annotations)
- [FAQ](#faq)
- [Sample Projects](#sample-projects)

---

## Why FxmlKit?

### Pain Point 1: No Hot Reload During UI Development

With traditional JavaFX development, every FXML or CSS change requires restarting the application to see the effect. This slows down UI development significantly.

**FxmlKit Solution:**

```java
public class MyApp extends Application {
    @Override
    public void start(Stage stage) {
        FxmlKit.enableDevelopmentMode();  // ✅ One line to enable hot reload

        stage.setScene(new Scene(new MainView()));
        stage.show();
    }
}
```

Now edit your `.fxml` or `.css` files, save, and see changes instantly — no restart required!

---

### Pain Point 2: Native FXML Loading Requires Massive Boilerplate

Every view requires repetitive code: getting URL, configuring FXMLLoader, handling exceptions, loading stylesheets...

**FxmlKit Solution:**
```java
// ✅ One line of code, everything handled automatically
public class LoginView extends FxmlView<LoginController> {
}
```

Automatically handles FXML parsing, stylesheet attachment, controller creation, and exception handling.

---

### Pain Point 3: FXML Custom Components Cannot Receive Dependency Injection

In traditional approaches, custom components declared in FXML are directly instantiated by FXMLLoader and cannot access the DI container.

**FxmlKit Solution:**

Controller auto-injection:
```java
public class LoginController {
    @Inject private UserService userService;

    @PostInject  // Automatically invoked
    private void afterInject() {
        // Dependencies are ready
    }
}
```

FXML nodes can also receive auto-injection:
```java
@FxmlObject  // One annotation to enable injection
public class StatusCard extends VBox {
    @Inject private StatusService statusService;

    @PostInject
    private void afterInject() {
        updateStatus();
    }
}
```

FXML usage:
```xml
<VBox>
    <StatusCard />  <!-- Automatic dependency injection -->
</VBox>
```

**Key point: Dependency injection is optional!** If you don't need DI, FxmlKit still eliminates FXML loading boilerplate.

---

## Core Features

- **Zero Configuration** — Works out of the box, no setup required
- **Convention over Configuration** — Automatically discovers FXML and stylesheet files
- **Hot Reload** — FXML and CSS changes reflect instantly during development
- **fx:include Support** — Full hot reload for nested FXML hierarchies (including dynamically added/removed includes)
- **Optional Dependency Injection** — Works without DI frameworks, add them when needed
- **Automatic Stylesheets** — Auto-attaches `.bss` and `.css` files
- **JPro Ready** — Supports multi-user web application data isolation (independent DI container per user session for data security)
- **High Performance** — Intelligent caching and performance optimization

**Comparison with Native JavaFX:**

| Feature | Native JavaFX | FxmlKit |
|---------|---------------|---------|
| Hot Reload (FXML + CSS) | ❌ Restart required | ✅ Instant refresh |
| fx:include Hot Reload | ❌ None | ✅ Full support (parent auto-refreshes when child changes) |
| User Agent Stylesheet Hot Reload | ❌ None | ✅ All levels (App/Scene/SubScene/Custom Control) |
| Automatic FXML Loading | ❌ Manual loading code | ✅ Zero-config auto-loading |
| Automatic Stylesheet Attachment | ❌ Manual code required | ✅ Auto-attach for FxmlView |
| Controller Dependency Injection | ⚠️ Manual factory setup | ✅ Automatic injection |
| **FXML Node Injection** | ❌ **Nearly impossible** | ✅ **@FxmlObject - one line** |
| Multi-layer fx:include Support | ⚠️ Partial support | ✅ Full support (with injection & styles) |
| @PostInject Lifecycle | ❌ None | ✅ Supported |
| JPro Multi-user Isolation | ❌ Manual implementation | ✅ Native support |

---

## Acknowledgments

- **[afterburner.fx](https://github.com/AdamBien/afterburner.fx)** — Inspired our convention-over-configuration approach (auto-resolving FXML/CSS by class name). We extended this with FXML node injection, multi-layer nesting, and JPro multi-user isolation.
- **[CSSFX](https://github.com/McFoggy/cssfx)** — Inspired our CSS hot reload approach (file:// URI replacement). Our implementation features shared WatchService, debounced refresh, and WeakReference-based cleanup.

---

## Quick Start

### Requirements

- **Java:** 11 or higher
- **JavaFX:** 11 or higher (17+ recommended)

#### Hot Reload Performance Note

FxmlKit uses Java's built-in `WatchService` for FXML/CSS hot reload. Performance varies across operating systems:

| OS | Java Version | File Change Detection Delay | Note |
|----|--------------|----------------------------|------|
| Windows | 11+ | Near-instant | Native file system events |
| Linux | 11+ | Near-instant | Native file system events |
| macOS | 11+ | ~2 seconds | FxmlKit optimized |

### Installation

**Maven:**
```xml
<dependency>
    <groupId>com.dlsc.fxmlkit</groupId>
    <artifactId>fxmlkit</artifactId>
    <version>1.5.0</version>
</dependency>
```

**Gradle:**
```gradle
implementation 'com.dlsc.fxmlkit:fxmlkit:1.5.0'
```

**If you need to use Guice for dependency injection:** Directly depend on `fxmlkit-guice` (includes core module)

**Maven:**
```xml
<dependency>
    <groupId>com.dlsc.fxmlkit</groupId>
    <artifactId>fxmlkit-guice</artifactId>
    <version>1.5.0</version>
</dependency>
<dependency>
    <groupId>com.google.inject</groupId>
    <artifactId>guice</artifactId>
    <version>7.0.0</version>
</dependency>
```

**Gradle:**
```gradle
implementation 'com.dlsc.fxmlkit:fxmlkit-guice:1.5.0'
implementation 'com.google.inject:guice:7.0.0'
```

**If you need to use other DI frameworks:** Continue using the core `fxmlkit` module and implement the `DiAdapter` interface or extend the `BaseDiAdapter` class. Similarly, even when using Guice, you can choose not to depend on the `fxmlkit-guice` module and implement a `GuiceDiAdapter` yourself (refer to `fxmlkit-guice` source code - the implementation is very simple).

---

### Create Your First View

**1. Create FXML File**

`src/main/resources/com/example/HelloView.fxml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.control.*?>

<VBox xmlns:fx="http://javafx.com/fxml" 
      fx:controller="com.example.HelloController"
      spacing="10" alignment="CENTER">
    <Label fx:id="messageLabel" text="Hello, FxmlKit!"/>
    <Button text="Click Me" onAction="#handleClick"/>
</VBox>
```

**2. Create Controller**

`src/main/java/com/example/HelloController.java`:
```java
package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML private Label messageLabel;
    
    @FXML
    private void handleClick() {
        messageLabel.setText("Hello from FxmlKit!");
    }
}
```

**3. Create View**

`src/main/java/com/example/HelloView.java`:
```java
package com.example;

import com.dlsc.fxmlkit.fxml.FxmlView;

public class HelloView extends FxmlView<HelloController> {
    // That's it!
}
```

**4. Use View**

```java
public class HelloApp extends Application {
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new HelloView()));
        stage.setTitle("FxmlKit Demo");
        stage.show();
    }
}
```

**Optional: Add Stylesheet**

Create `src/main/resources/com/example/HelloView.css`, and FxmlKit will automatically attach it!

---

## Usage

FxmlKit supports three usage patterns - choose based on your needs:

### Method 1: Zero Configuration

**Use Case:** Learning JavaFX, quick prototyping, simple applications

```java
public class MainView extends FxmlView<MainController> {
}

// Usage
stage.setScene(new Scene(new MainView()));
```

**Features:**
- ✅ Automatic FXML loading
- ✅ Automatic stylesheet attachment
- ✅ Automatic controller creation
- ❌ No dependency injection

---

### Method 2: Optional Dependency Injection

**Use Case:** Desktop applications that need dependency injection

```java
// Application startup - set global DI adapter
Injector injector = Guice.createInjector(new AbstractModule() {
    @Override
    protected void configure() {
        bind(UserService.class).toInstance(new UserService());
        bind(ConfigService.class).toInstance(new ConfigService());
    }
});
FxmlKit.setDiAdapter(new GuiceDiAdapter(injector));

// Create view - controller and nodes automatically receive injection
public class LoginView extends FxmlView<LoginController> {
}

// Usage - same as zero configuration mode
LoginView view = new LoginView();
```

**Features:**
- ✅ Global DI configuration - set once, use everywhere
- ✅ Automatic controller injection
- ✅ Automatic FXML node injection (with `@FxmlObject`)
- ✅ Supports multiple DI frameworks (Guice, Spring, CDI, etc.)

---

### Method 3: Independent DI Container

**Use Case:** JPro web applications where each user needs isolated data

```java
// Create independent DI container for each user session
Injector userInjector = Guice.createInjector(new AbstractModule() {
    @Override
    protected void configure() {
        bind(UserSession.class).toInstance(new UserSession(userId));
        bind(UserService.class).toInstance(new UserService());
    }
});

// Pass DI adapter when creating view
LoginView view = new LoginView(new GuiceDiAdapter(userInjector));
```

**Use Cases:**
- JPro web applications (one DI container per user session)
- Desktop applications (one DI container per Tab/Window)
- Scenarios requiring strict data isolation

---

### Reactive Controller API

FxmlKit exposes controllers and views as JavaFX properties, enabling reactive programming patterns. This is especially useful with hot reload to automatically update UI when controllers change.

**With FxmlView (eager loading):**
```java
MainView view = new MainView();

// Access controller directly (returns null only if FXML has no fx:controller)
MainController controller = view.getController();

// React to controller changes (e.g., during hot reload)
view.controllerProperty().addListener((obs, oldController, newController) -> {
    if (newController != null) {
        newController.refreshData();  // Re-initialize on hot reload
    }
});
```

**With FxmlViewProvider (lazy loading):**
```java
MainViewProvider provider = new MainViewProvider();

// Controller is null until getView() is called
MainController controller = provider.getController();  // null - not loaded yet

// React to controller changes
provider.controllerProperty().addListener((obs, oldCtrl, newCtrl) -> {
    if (newCtrl != null) {
        newCtrl.loadData();
    }
});

// Trigger lazy loading
Parent view = provider.getView();  // Now FXML is loaded
controller = provider.getController();  // Now returns the controller
```

**Benefits:**
- ✅ Automatically respond to hot reload events
- ✅ Seamless integration with JavaFX property binding
- ✅ Cleaner code compared to manual reload handling

**Note:** With `FxmlView`, the FXML is loaded immediately in the constructor, so `getController()` always returns the controller (or null if FXML has no `fx:controller` attribute). With `FxmlViewProvider`, the FXML is loaded lazily when `getView()` is first called, so `getController()` returns null until then.

---

## Hot Reload

FxmlKit provides built-in hot reload for rapid UI development. Edit your FXML or CSS files and see changes instantly without restarting.

### Quick Start

```java
public class MyApp extends Application {
    @Override
    public void start(Stage stage) {
        // Enable hot reload (call BEFORE creating views)
        FxmlKit.enableDevelopmentMode();
        
        stage.setScene(new Scene(new MainView()));
        stage.show();
    }
}
```

**Important:** Call `enableDevelopmentMode()` **before** creating any views. Views created before enabling won't be monitored.

### How It Works

| File Type | Behavior | Runtime State | fx:include |
|-----------|----------|---------------|------------|
| `.fxml` | Full view reload | Lost (user input, scroll position) | ✅ Parent auto-refreshes when child changes |
| `.css` / `.bss` | Stylesheet refresh only | **Preserved** | ✅ Fully supported |

**fx:include Hot Reload:**
- Edit a child FXML → Parent view automatically reloads
- Dynamically add/remove `<fx:include>` → Works seamlessly
- Nested includes → All levels monitored

**Monitored Stylesheets:**
- Normal stylesheets (`scene.getStylesheets()`, `parent.getStylesheets()`)
- User Agent Stylesheets (Application, Scene, SubScene levels)
- Custom control stylesheets (`Region.getUserAgentStylesheet()` overrides)
- Auto-attached stylesheets (convention-based `.css`/`.bss` files)

### User Agent Stylesheet Support

For **Scene** and **SubScene** level User Agent Stylesheets, hot reload works automatically with native JavaFX API:

```java
scene.setUserAgentStylesheet("/styles/theme.css");  // Auto-monitored
```

For **Application** level, use FxmlKit's bridged property to enable hot reload:

```java
// ✅ Supports hot reload + property binding
FxmlKit.setApplicationUserAgentStylesheet("/styles/dark-theme.css");

// Or bind to a theme selector
FxmlKit.applicationUserAgentStylesheetProperty()
    .bind(themeComboBox.valueProperty());
```

Note: Using `Application.setUserAgentStylesheet()` directly still works, but won't trigger hot reload.


### Custom Control User Agent Stylesheet

FxmlKit supports hot reload for custom controls that override `getUserAgentStylesheet()`, but **this feature is opt-in** due to CSS priority implications:
```java
public class VersionLabel extends Label {
    
    @Override
    public String getUserAgentStylesheet() {
        return VersionLabel.class.getResource("version-label.css").toExternalForm();
    }
}
```

**Enable custom control UA hot reload:**
```java
// Option 1: Enable development mode (FXML + CSS hot reload)
FxmlKit.enableDevelopmentMode();
FxmlKit.setControlUAHotReloadEnabled(true);

// Option 2: Enable CSS hot reload only
FxmlKit.setCssHotReloadEnabled(true);
FxmlKit.setControlUAHotReloadEnabled(true);
```

**⚠️ Prerequisites:**
- Control UA hot reload requires CSS monitoring to be active
- Enable CSS hot reload via `enableDevelopmentMode()` or `setCssHotReloadEnabled(true)` first

**How it works:** When enabled, FxmlKit automatically detects custom controls with overridden `getUserAgentStylesheet()` and promotes the stylesheet to `getStylesheets().add(0, ...)` for monitoring. The stylesheet is added at index 0 (lowest priority among author stylesheets) to approximate the original low-priority behavior.

**⚠️ Why opt-in?** This promotion changes CSS cascade semantics — the stylesheet becomes an "author stylesheet" instead of a true "user agent stylesheet". This may cause custom control styles to unexpectedly override user-defined or scene-level stylesheets in some cases.

**Recommendation:**
- Only enable during development when you need to edit custom control stylesheets
- Disable in production: `FxmlKit.setControlUAHotReloadEnabled(false)`
- Test your styling if you experience unexpected style conflicts

### Fine-Grained Control

```java
// Enable only FXML hot reload
FxmlKit.setFxmlHotReloadEnabled(true);

// Enable only CSS hot reload
FxmlKit.setCssHotReloadEnabled(true);

// Enable control UA stylesheet hot reload (opt-in, requires CSS hot reload)
FxmlKit.setControlUAHotReloadEnabled(true);

// Enable both (equivalent to enableDevelopmentMode())
FxmlKit.setFxmlHotReloadEnabled(true);
FxmlKit.setCssHotReloadEnabled(true);

// Disable all
FxmlKit.disableDevelopmentMode();
```

### Using with CSSFX

If you prefer [CSSFX](https://github.com/McFoggy/cssfx) for CSS hot reload, disable FxmlKit's built-in CSS monitoring:

```java
// Use FxmlKit for FXML hot reload only
FxmlKit.setFxmlHotReloadEnabled(true);
FxmlKit.setCssHotReloadEnabled(false);

// Use CSSFX for CSS
CSSFX.start();
```

### Production Warning

**Hot reload is for development only.** Do not enable in production environments.

**Option 1: Simply comment out the line before release**

```java
public class MyApp extends Application {
    @Override
    public void start(Stage stage) {
        // FxmlKit.enableDevelopmentMode();  // Comment out for production
        
        stage.setScene(new Scene(new MainView()));
        stage.show();
    }
}
```

**Option 2: Use JVM argument for automatic switching**

```java
public class MyApp extends Application {
    // Set via JVM argument: -Ddev.mode=true
    private static final boolean DEV_MODE = Boolean.getBoolean("dev.mode");
    
    @Override
    public void start(Stage stage) {
        if (DEV_MODE) {
            FxmlKit.enableDevelopmentMode();
        }
        
        stage.setScene(new Scene(new MainView()));
        stage.show();
    }
}
```

Run in development: `java -Ddev.mode=true -jar myapp.jar`

Run in production: `java -jar myapp.jar`

---

## Core Concepts

### Automatic File Resolution

FxmlKit uses convention over configuration to automatically find FXML and stylesheet files:

```
src/main/resources/com/example/
├── UserView.fxml          ← UserView.java auto-matches
├── UserView.css           ← Auto-attached
└── UserView.bss           ← Binary stylesheet (priority)
```

**Convention:** FXML file has the same name as Java class and is in the same resource directory.

---

### FxmlView vs FxmlViewProvider

| Feature | FxmlView | FxmlViewProvider |
|---------|----------|------------------|
| **Type** | IS-A Node (extends StackPane) | HAS-A Node (holds Parent) |
| **Loading** | Eager (loads immediately) | Lazy (loads on first `getView()`) |
| **Usage** | Direct use as Node | Call `getView()` to get Node |
| **Use Case** | Used directly as a node | Lazy loading to save resources |

**FxmlView Example:**
```java
public class LoginView extends FxmlView<LoginController> {
}

// Usage - direct use as a Node
LoginView view = new LoginView();  // FXML loaded immediately
scene.setRoot(view);  // view itself is a StackPane
```

**FxmlViewProvider Example:**
```java
public class MainViewProvider extends FxmlViewProvider<MainController> {
}

// Usage - need to call getView()
MainViewProvider provider = new MainViewProvider();  // FXML not loaded yet
Parent view = provider.getView();  // FXML loaded here
scene.setRoot(view);
```

---

### Injecting Custom Components

Mark custom components with `@FxmlObject` to enable dependency injection:

```java
@FxmlObject
public class StatusCard extends VBox {
    @Inject private StatusService statusService;
    
    @PostInject
    private void afterInject() {
        // statusService is ready
        updateStatus();
    }
}
```

Use in FXML:
```xml
<VBox>
    <StatusCard />  <!-- Automatic injection -->
</VBox>
```

---

### Injection Strategy

FxmlKit defaults to `EXPLICIT_ONLY` strategy (only injects objects marked with `@FxmlObject`).

---

## Annotations

### @FxmlPath - Custom FXML File Path

**Purpose:** Specify FXML file location, overriding default auto-resolution rules.

**Use Case:** Typically not needed, as FxmlKit auto-resolves. Only use when FXML file is not in default location.

```java
@FxmlPath("/shared/Common.fxml")
public class LoginView extends FxmlView<LoginController> {}
```

---

### @FxmlObject - Enable FXML Object Injection

**Purpose:** Mark a class to enable dependency injection when created in FXML.

**Supported Object Types:**
- Custom JavaFX controls (Button, TextField subclasses, etc.)
- Layout containers (Pane, HBox, VBox subclasses, etc.)
- Non-visual objects (MenuItem, ContextMenu, etc.)
- Any custom class declared in FXML

**Example - Custom Control:**
```java
@FxmlObject
public class UserAvatar extends Circle {
    @Inject private UserService userService;
    
    @PostInject
    private void afterInject() {
        loadUserImage();
    }
}
```

**Example - Non-Visual Object:**
```java
@FxmlObject
public class CustomMenuItem extends MenuItem {
    @Inject private ActionService actionService;
    
    @PostInject
    private void afterInject() {
        setOnAction(e -> actionService.execute());
    }
}
```

FXML usage:
```xml
<MenuBar>
    <Menu text="Actions">
        <CustomMenuItem text="Execute"/>  <!-- Also receives injection -->
    </Menu>
</MenuBar>
```

**Note:**
- Custom objects without `@FxmlObject` won't receive dependency injection (unless injection strategy is set to `AUTO`)
- If using `AUTO` strategy but want to exclude certain types, use `FxmlKit.excludeNodeType()` or add `@SkipInjection` annotation on the class

---

### @PostInject - Post-Injection Callback

**Purpose:** Mark a method to be automatically invoked after all `@Inject` field injections are complete.

**Use Case:** Initialize components, load data, or set up listeners after dependencies are ready.

**Execution Timing:** Immediately after all `@Inject` field injections are complete.

**Example:**
```java
public class UserProfileController {
    @Inject private UserService userService;
    @Inject private ConfigService configService;
    
    @PostInject
    private void afterInject() {
        // All @Inject fields are now ready
        userService.loadUserData();
        configService.applySettings();
    }
}
```

**Method Requirements:**
- Must be no-arg
- Can have any access modifier (private, protected, public)
- Supports inheritance (parent class `@PostInject` methods execute first)

---

## FAQ

### Q: Must FxmlKit use a dependency injection framework?

**A:** No! FxmlKit works perfectly without DI frameworks. You can use just `FxmlView` for automatic FXML loading and stylesheet attachment. Add DI only when you need it.

---

### Q: When to use the built-in LiteDiAdapter?

**A:** LiteDiAdapter is a simple DI container for small projects and learning scenarios.

**Dependency Required:**
```xml
<dependency>
    <groupId>javax.inject</groupId>
    <artifactId>javax.inject</artifactId>
    <version>1</version>
</dependency>
```

**Usage:**
```java
import javax.inject.Inject;

LiteDiAdapter di = new LiteDiAdapter();
di.bindInstance(UserService.class, new UserService());
FxmlKit.setDiAdapter(di);
```

**When to use:** Small projects, learning, prototyping  
**When to upgrade:** For larger projects or advanced features, use Guice or other mature DI frameworks

---

### Q: Why are @Inject fields null?

**Reason:** Zero-configuration mode (Method 1) doesn't support dependency injection.

**Solution:** Configure a DiAdapter:
```java
FxmlKit.setDiAdapter(diAdapter);
```

---

### Q: When does @PostInject method execute?

**A:** After dependency injection completes. The timing differs between controllers and nodes:

#### For Controllers

**Execution order:** `Constructor → @Inject → @FXML → initialize() → @PostInject`

Usually **not needed** - use `initialize()` instead:
```java
public class LoginController implements Initializable {
    @Inject private UserService userService;  // ① Injected
    @FXML private Button loginButton;          // ② Injected by JavaFX
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ③ Both @Inject and @FXML available here
        loginButton.setOnAction(e -> userService.login());
    }
    
    @PostInject
    private void afterInject() {
        // ④ Called after initialize() - usually not needed for controllers
        userService.loadSettings();  // Example: if you need it
    }
}
```

#### For @FxmlObject Nodes

**Execution order:** `Constructor → @Inject → @PostInject`

**Required** if you need injected dependencies:
```java
@FxmlObject
public class StatusLabel extends Label {
    @Inject private StatusService statusService;
    
    public StatusLabel() {
        // ① Constructor - statusService is null here ❌
    }
    
    @PostInject
    private void afterInject() {
        // ② Injected - statusService available now ✅
        setText(statusService.getStatus());
    }
}
```

**Rule:** Use `@PostInject` for initialization requiring `@Inject` dependencies in `@FxmlObject` nodes.

---

### Q: Stylesheet not working?

**A:** Check these:

1. **File naming:** Must match class name - `LoginView.java` → `LoginView.css`
2. **File location:** Must be in same package resource directory
3. **Auto-attach enabled:** `FxmlKit.setAutoAttachStyles(true)` (default is true)
4. **CSS priority:** `.bss` files have higher priority than `.css`

---

### Q: Hot reload not working?

**A:** Check these:

1. **Call order:** `enableDevelopmentMode()` must be called **before** creating views
2. **File location:** Source files must be in `src/main/resources` (Maven/Gradle standard)
3. **IDE auto-build:** Enable automatic build in your IDE for seamless hot reload
4. **Debug logging:** Set `FxmlKit.setLogLevel(Level.FINE)` to see hot reload messages

---

### Q: fx:include hot reload not working?

**A:** FxmlKit automatically monitors fx:include dependencies. Check these:

1. **FXML hot reload enabled:** Parent FxmlView must be created after enabling FXML hot reload (`enableDevelopmentMode()` or `setFxmlHotReloadEnabled(true)`)
2. **File exists:** Included FXML must exist in source directory

---

### Q: How to use with JPro?

**A:** FxmlKit is JPro-ready. Create an independent DI container for each user session:

```java
public class JProApp extends Application {
    @Override
    public void start(Stage stage) {
        // Create independent DI container per user
        Injector userInjector = createUserInjector();
        
        // Pass DI adapter when creating view
        MainView view = new MainView(new GuiceDiAdapter(userInjector));
        
        Scene scene = new Scene(view);
        stage.setScene(scene);
    }
}
```

See `fxmlkit-samples` module's `tier3.multiuser` package for a complete implementation simulating JPro multi-user scenarios (using TabPane to simulate multi-user sessions).

---

## Sample Projects

Complete sample code is in the `fxmlkit-samples` module, organized into three tiers by complexity:

### Tier 1 - Zero Configuration Mode

```
tier1/
├── hello/          # Simplest Hello World
├── i18n/           # Internationalization example
├── provider/       # FxmlViewProvider usage examples
└── viewpath/       # Custom FXML path
└── theme/          # User Agent Stylesheet hot reload (Application level + Custom Control)
```

### Tier 2 - Optional Dependency Injection

```
tier2/
├── fxmlobject/     # @FxmlObject node injection examples
├── guice/          # Guice integration examples
└── login/          # Complete login application example
```

### Tier 3 - JPro Multi-User Isolation

```
tier3.multiuser/    # Simulates JPro multi-user scenario (using TabPane)
```
