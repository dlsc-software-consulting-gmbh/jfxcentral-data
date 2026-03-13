# GemsFX

A collection of JavaFX controls and utilities.

## Controls

| Control | Description |
|---------|-------------|
| `CalendarView` | Displays the month of a year. |
| `CalendarPicker` | Date picker built on `CalendarView`. |
| `YearMonthPicker` | Picker for a `YearMonth` value; inherits styling from the standard date picker. |
| `StripView` | Horizontally positions nodes with animated scroll buttons on either side and single-item selection support. |
| `InfoCenterPane` | Notification center with slide-in animations, group stacking/expanding, pinned groups, and a list view for overflow notifications. |
| `ScreensView` | Displays screen geometries, application windows, and arbitrary shapes (useful for debugging). |
| `SearchTextField` | Text field decorated to express search intent. |
| `SearchField` | Auto-suggest field with Spotlight-like completion; can create new objects when no result is found. See the [wiki](https://github.com/dlsc-software-consulting-gmbh/GemsFX/wiki/SearchField). |
| `TagsField` | Extends `SearchField` to convert selections into tags (on Enter, →, or Tab). Uses a `FlowPane` for multi-row layout. |
| `DialogPane` | Overlay layer supporting: Information, Warning, Error, Confirmation, Node, Busy (spinner), single-line and multi-line text input dialogs. |
| `ResizableTextArea` | Text area with a resize handle in the lower-right corner (horizontal, vertical, or both directions). |
| `ExpandingTextArea` | Text area that grows with its content and never shows scrollbars. |
| `TimePicker` | Time entry with configurable step rate, valid time range, rollover, linked fields, and a mouse-only popup. |
| `DurationPicker` | Duration entry similar to `TimePicker`: rollover, linked fields, leading zeros, and a popup. |
| `PhotoView` | Profile photo editor: open via file chooser or drag-and-drop, pan, zoom, and crop. Shortcuts: SPACE/ENTER to open chooser, DELETE/BACK_SPACE to remove. |
| `PaymentOptionView` | `ImageView` subclass for displaying payment option graphics. |
| `DrawerStackPane` | `StackPane` with an animated slide-in/out drawer and a semi-transparent glass pane backdrop. Drawer height is persisted via the Preferences API. |
| `FilterView` | Filters an `ObservableList` for use with `TableView`, `ListView`, or similar controls. |

## Utilities

| Utility | Description |
|---------|-------------|
| `StageManager` | Persists and restores a stage's location and size across sessions via `java.util.prefs`. |
| `SessionManager` | Monitors observables and persists their state across sessions (e.g. a `SplitPane` divider position). |
