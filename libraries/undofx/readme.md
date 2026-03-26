## UndoFX

UndoFX is an undo manager for JavaFX that provides a clean, composable API for implementing undo/redo functionality. Rather than requiring a traditional Command pattern with explicit undo objects, UndoFX works with `UndoManager` instances that monitor ReactFX change streams and automatically record changes for reversal.

Multiple undo managers can be composed into a single manager that tracks changes across different parts of the application model. The current undo/redo state is exposed as JavaFX properties, making it easy to bind to toolbar buttons or menu items.

UndoFX is used internally by RichTextFX and is an excellent choice for any editor-style JavaFX application where undo/redo is a core user requirement.
