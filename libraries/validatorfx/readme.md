# ValidatorFX

ValidatorFX is a validation library for JavaFX inspired by [ControlsFX](https://github.com/controlsfx/controlsfx),
designed to overcome its limitations:

- Validations based on arbitrary observable values (not just a control's value)
- Validations can decorate any number of nodes (not just the one control)
- Validations can decorate any `Node` (not just `Control` subtypes)
- Immediate or on-demand validation (e.g. triggered by a button click)

The central class is `Validator`, which holds a collection of `Check`s. A form typically has one `Validator` and multiple `Check`s.

## Usage

```java
private final Validator validator = new Validator();

// inside start():
TextField userTextField = new TextField();

validator.createCheck()
    .dependsOn("username", userTextField.textProperty())  // (1) declare observable dependency
    .withMethod(c -> {                                     // (2) define check logic
        String userName = c.get("username");
        if (!userName.toLowerCase().equals(userName)) {
            c.error("Please use only lowercase letters.");
        }
    })
    .decorates(userTextField)                             // (3) decorate nodes on failure
    .immediate();                                         // (4) re-evaluate on every change
```

| API | Behaviour |
|-----|-----------|
| `.dependsOn(key, property)` | Registers an observable; call multiple times for multiple dependencies |
| `.withMethod(c -> ...)` | Defines the check logic; multiple calls install multiple checks (all execute — no short-circuit) |
| `.decorates(node)` | Marks nodes to decorate on failure; call multiple times for multiple nodes |
| `.immediate()` | Evaluates continuously; omit to validate on-demand via `Validator.validate()` |
| `.immediateClear()` | Clears decorations immediately on input, then re-validates |

![Screenshot of MinimalExample](MinimalDemo.png)
