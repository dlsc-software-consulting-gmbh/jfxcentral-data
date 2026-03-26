## javafx-d3

javafx-d3 provides a Java API for using D3.js — the powerful JavaScript data visualization library — from within a JavaFX WebView. Instead of writing JavaScript, developers can use a Java/JavaFX-friendly API that calls into D3.js running in the embedded browser engine.

The library bridges the Java-JavaScript boundary using JavaFX's `JSObject` interop, wrapping D3's selections, scales, axes, shapes, and layouts in Java classes. This allows data-driven SVG visualizations to be created and manipulated from pure Java code.

javafx-d3 is ideal for applications that need sophisticated data visualizations beyond what JavaFX Charts provides, while keeping the rendering logic accessible from Java without requiring deep JavaScript knowledge.
