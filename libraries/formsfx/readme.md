A framework for easily creating forms in JavaFX with a fluent API, pre-defined controls, validation, and localisation.

## Semantics

FormsFX has three semantic layers: a `Form` contains `Group`s (and `Section`s), which contain `Field`s. Fields are the end user's primary point of interaction for data input.

## Defining a Form

```java
Form.of(
    Group.of(
        Field.ofStringType("")
            .label("Username"),
        Field.ofStringType("")
            .label("Password")
            .required("This field can't be empty")
    ),
    Group.of(…)
).title("Login");
```

Field options:

| Option | Description |
|--------|-------------|
| `label(String)` | Concise description; always visible, usually placed beside the control. |
| `tooltip(String)` | Contextual hint displayed on hover or focus. |
| `placeholder(String)` | Hint describing expected input while the field is empty. |
| `required(boolean/String)` | Marks the field as required for form correctness. |
| `editable(boolean)` | Controls whether the user can edit the field. |
| `id(String)` | Unique identifier; not visible, but usable for styling. |
| `styleClass(List<String>)` | Adds CSS style classes to the field. |
| `span(int/ColSpan)` | Column span on the view layer (1–12 or a `ColSpan` fraction). |
| `render(SimpleControl)` | Sets a custom control to render this field. |

## Field Types

| Type | Example |
|------|---------|
| **String** | `Field.ofStringType("CHF").label("Currency")` |
| **Integer** | `Field.ofIntegerType(8401120).label("Population")` |
| **Double** | `Field.ofDoubleType(41285.0).label("Area")` |
| **Boolean** | `Field.ofBooleanType(false).label("Independent")` |
| **ComboBox** | `Field.ofSingleSelectionType(Arrays.asList("Zürich (ZH)", "Bern (BE)", …), 1).label("Capital")` |
| **RadioButton** | `Field.ofSingleSelectionType(Arrays.asList("Right", "Left"), 0).label("Driving on the").render(new SimpleRadioButtonControl<>())` |
| **CheckBox** | `Field.ofMultiSelectionType(Arrays.asList("Africa", "Asia", …), Collections.singletonList(2)).label("Continent").render(new SimpleCheckBoxControl<>())` |
| **ListView** | ```Field.ofMultiSelectionType(Arrays.asList("Zürich (ZH)", "Bern (BE)", …), Arrays.asList(0, 1, …)).label("Biggest Cities")``` |

## Rendering

Add a form to the scene using `FormRenderer`:

```java
root.getChildren().add(new FormRenderer(form));
```

Override the default control with `render()`:

```java
Field.ofMultiSelectionType(…).render(new SimpleCheckBoxControl<>())
```

## Model

Bind form fields directly to model properties:

```java
StringProperty name = new SimpleStringProperty("Hans");
Field.ofStringType(name);
```

Use `persist()` and `reset()` to store or revert values. Set `BindingMode.CONTINUOUS` on the form to enable automatic persistence.

## Localisation

All display strings support localisation via a `ResourceBundleService`:

```java
ResourceBundleService rbs = new ResourceBundleService(
    ResourceBundle.getBundle("demo.demo-locale", new Locale("en", "UK")));

Form.of(…).i18n(rbs);
```

## Validation

Fields are validated on every edit. Pre-defined validators:

| Validator | Description |
|-----------|-------------|
| `CustomValidator` | Predicate-based; returns valid/invalid for a field. |
| `DoubleRangeValidator` | Valid number range for doubles (one or both bounds). |
| `IntegerRangeValidator` | Valid number range for integers (one or both bounds). |
| `RegexValidator` | Validates text against a regex; includes presets for common cases (e.g. email). |
| `SelectionLengthValidator` | Valid selection length interval (one or both bounds). |
| `StringLengthValidator` | Valid string length interval (one or both bounds). |
