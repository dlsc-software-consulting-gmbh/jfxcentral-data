## WellBehavedFX

WellBehavedFX provides composable event handler utilities and skin scaffolding for building well-behaved JavaFX controls. Its `InputMap` abstraction allows keyboard and mouse event handling to be defined as composable, overridable layers — making it easy to create controls that respect user customization and platform conventions.

The `InputMap` can be installed on a node and supports partial overrides, so a subclass or consumer can add/replace only the event handlers they care about while inheriting the rest. This is far more flexible than JavaFX's standard event filter/handler mechanism for control skins.

WellBehavedFX is used internally by RichTextFX and is the recommended approach for building complex, keyboard-friendly custom JavaFX controls.
