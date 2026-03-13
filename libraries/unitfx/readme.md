A lightweight JavaFX framework for unit-of-measure (UOM) text field input controls. `QuantityInputField` validates input and converts between units automatically. A "base unit" can be set on a field — the field highlights when the user selects a different unit. Requires JDK 11+.

## Usage

The same three-line pattern applies to any `javax.measure` quantity type:

```java
QuantityInputField<Length> lengthField = new QuantityInputField<>();
lengthField.getAvailableUnits().addAll(Units.getInstance().getUnits(Length.class));
lengthField.setBaseUnit(Units.getInstance().getUnit(Length.class));
lengthField.setAutoFixValue(true);

QuantityInputField<Temperature> tempField = new QuantityInputField<>();
tempField.getAvailableUnits().addAll(Units.getInstance().getUnits(Temperature.class));
tempField.setBaseUnit(Units.getInstance().getUnit(Temperature.class));
tempField.setAutoFixValue(true);

// Same pattern works for: Angle, Speed, Mass, and other javax.measure quantities
```

See `DemoApp.java` in the source for a full working example.