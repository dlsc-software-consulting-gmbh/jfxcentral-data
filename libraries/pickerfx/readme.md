# PickerFX

A lightweight framework for touch-friendly picker controls.

![alt](youtube:hGG4I7QIzx0)

## Available Pickers

| Picker | Description |
|--------|-------------|
| `IntegerPicker` | Scroll to select an integer |
| `ItemPicker` | Pick from a fixed list of items |
| `LocalDatePicker` | Date picker |
| `LocalTimePicker` | Time picker (12h or 24h) |
| `LocalDateTimePicker` | Combined date & time picker |
| `DurationPicker` | Pick a duration with configurable fields |

## Usage

```java
IntegerPicker integerPicker = new IntegerPicker();
ItemPicker itemPicker = new ItemPicker("A", "B", "C", "D", "E");
LocalDatePicker localDatePicker = new LocalDatePicker();
LocalTimePicker localTimePicker = new LocalTimePicker();
LocalDateTimePicker localDateTimePicker = new LocalDateTimePicker();
DurationPicker durationPicker = new DurationPicker();

// Configuration examples
localTimePicker.setTimeFormat(TimeFormat.TWELVE_HOURS);
durationPicker.getFields().setAll(ChronoUnit.DAYS, ChronoUnit.HOURS, ChronoUnit.MINUTES, ChronoUnit.SECONDS, ChronoUnit.MILLIS);
durationPicker.setMaximumDuration(Duration.ofDays(7));
```

See `DemoApp.java` in the source for a complete working example.