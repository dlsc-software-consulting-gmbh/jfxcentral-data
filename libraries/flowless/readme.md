## Flowless

Flowless is an efficient VirtualFlow implementation for JavaFX that serves as the foundation for virtualized list-like controls. Unlike JavaFX's built-in VirtualFlow, Flowless allows cells to have variable heights and widths, making it suitable for components like code editors, rich text areas, and any list where items differ in size.

The library exposes a clean API for building custom virtualized controls on top of it. It handles cell recycling, viewport scrolling, and hit-testing efficiently, ensuring smooth performance even with very large data sets.

Flowless is used internally by RichTextFX as its rendering engine and is the recommended choice when building any high-performance, variable-height virtualized control for JavaFX.
