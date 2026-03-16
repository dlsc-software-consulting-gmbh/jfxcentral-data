**Language Manager** is a JavaFX library that enables **dynamic language switching at runtime**, allowing you to update the application language without reloading the scene.

## Features

- Dynamic language switching using standard `.properties` files
- Automatic binding for `Label`, `Button`, `TextField`, `CheckBox`, `RadioButton`, `ChoiceBox`, `ComboBox`, `MenuItem`, `TreeItem`, `Tab`, and more
- Programmatic binding for dynamically created controls (no `@FXML` required)
- Custom annotations to ignore or customize specific field bindings

## Installation

**Maven:**
```xml
<dependency>
    <groupId>io.github.snoopy137</groupId>
    <artifactId>language-manager</artifactId>
    <version>1.1.1</version>
</dependency>
```

**Gradle:**
```groovy
implementation 'io.github.snoopy137:language-manager:1.1.1'
```

## Usage

### FXML-Based Auto Binding

Annotate controller fields and call `Language.autoBind(this)` to bind controls by their `@FXML` IDs:

```java
@FXML private Label greeting;
@FXML private Button submitButton;

public void initialize() {
    Language.autoBind(this);
}
```

To exclude a field, use `@IgnoreBind`:

```java
@FXML @IgnoreBind
private Label doNotTranslate;
```

### Programmatic Binding

For dynamically created controls, use `@Bind` and initialize before calling `autoBind`:

```java
@Bind
private Label dynamicLabel;

public void initialize() {
    dynamicLabel = new Label();
    rootPane.setCenter(dynamicLabel);
    Language.autoBind(this); // binds to key "dynamicLabel"
}
```
