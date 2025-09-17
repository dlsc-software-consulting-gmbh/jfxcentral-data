# Language Manager

**Language Manager** is a JavaFX library that enables **dynamic language switching at runtime**, allowing you to update the application language without needing to refresh the scene.

## Features

* Support for multiple languages using standard `.properties` files.
* Change language dynamically at runtime — no need to reload the scene.
* Automatic binding for JavaFX controls such as `Label`, `Button`, `TextField`, `CheckBox`, `RadioButton`, `ChoiceBox`, `ComboBox`, `MenuItem`, and more.
* Programmatic binding for controls without `@FXML` IDs — perfect for dynamically created interfaces.
* Custom annotations to ignore or customize specific field bindings.
* Support for `TreeItem` and `Tab` bindings as well.

## Installation

Add the library to your project using Maven or Gradle

### Maven

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.snoopy137</groupId>
    <artifactId>language-manager</artifactId>
    <version>1.1.1</version>
</dependency>
```

### Gradle

Add this to your `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.snoopy137:language-manager:1.1.1'
}
```

## Usage

### FXML-Based Auto Binding

If you're using FXML, annotate your controller fields and call `Language.autoBind(this)` to automatically bind controls based on their @FXML IDs.

```java
@FXML
private Label greeting;

@FXML
private Button submitButton;

public void initialize() {
   Language.autoBind(this); // Binds all supported @FXML controls automatically
}
```

Supported controls include Label, Button, TextField, TextArea, CheckBox, MenuItem, Tab, Tooltip, and more.

To exclude a specific field from being auto-bound, use the `@IgnoreBind` annotation:

```java
@FXML @IgnoreBind
private Label doNotTranslate;
```

### Programmatic Binding (No FXML Required)

If you're not using FXML or want to create and bind controls dynamically, you can use the `@Bind` annotation without @FXML. Make sure to initialize your controls before calling `Language.autoBind(this)`

```java
@Bind
private Label dynamicLabel;

public void initialize() {
   dynamicLabel = new Label();
   rootPane.setCenter(dynamicLabel);

   Language.autoBind(this); // Binds to key "dynamicLabel"
}
```
