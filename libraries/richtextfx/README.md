
RichTextFX
==========

RichTextFX provides a memory-efficient text area for JavaFX that allows the developer to style ranges of text and display custom objects in-line (no more HTMLEditor).

It is intended as a base for rich-text editors and code editors with syntax highlighting. Since it is a base, a number of suggested features (specific syntax highlighters, search-and-replace, specific support for hyperlinks, etc.) have not be implemented directly in this project. Rather, developers can implement these on top of RichTextFX and submit their work as a PR to the `richtextfx-demos` package.

For a greater explanation of RichTextFX, its design principles, how it works, and how to style its areas via CSS, please [see the wiki](https://github.com/FXMisc/RichTextFX/wiki)

Demos
-----

Stand-alone Applications that demonstrate some of the features of RichTextFX can be found [here](https://github.com/FXMisc/RichTextFX/richtextfx-demos/)

Features
--------

* Assign arbitrary styles to arbitrary ranges of text. A style can be an object, a CSS string, or a style class string.
* Display line numbers or, more generally, any graphic in front of each paragraph. Can be used to show breakpoint toggles on each line of code.
* Support for displaying other `Node`s in-line
* Positioning a popup window relative to the caret or selection. Useful e.g. to position an autocompletion box.
* Getting the character index under the mouse when the mouse stays still over the text for a specified period of time. Useful for displaying tooltips depending on the word under the mouse.
* Overriding the default behavior only where necessary without overriding any other part.

Flavors
-------

The following explains the different rich text area classes. The first one is the base class from which all others extend: it needs further customization before it can be used but provides all aspects of the project's features. The later ones extend this base class in various ways to provide out-of-box functionality for specific use cases. **Most will use one of these subclasses.**

### GenericStyledArea

`GenericStyledArea` allows one to inline custom objects into the area alongside of text. As such, it uses generics and functional programming to accomplish this task in a completely type-safe way.

### StyledTextArea

`StyledTextArea`, or one of its subclasses below, is the area you will most likely use if you don't need to display custom objects in your area.

It extends `GenericStyledArea`, where your styled text is simply a text `String` with a style object (`S`). 

The style object (`S`) can either be a CSS String (`-fx-fill: red;`), a CSS styleclass (`.red { -fx-fill: red; }`), or an object that handles this in a different way. Since most will use either the CSS String or CSS style class approach, there are two subclasses that already handle this correctly.

### InlineCssTextArea and InlineCssTextField

`InlineCssTextArea` uses the `Node#setStyle(String cssStyle)` method to style `Text` objects:

```java
area.setStyle(from, to, "-fx-font-weight: bold;");
```

### StyleClassedTextArea and StyleClassedTextField

`StyleClassedTextArea` uses the `Node#setStyleClass(String styleClass) method to style `Text` objects. You can define the style classes in your stylesheet.

example.css:

```css
.red { -fx-fill: red; }
```

Example.java:

```java
area.setStyleClass(from, to, "red");
```

This renders the text in the range `[from, to)` in red.

#### CodeArea

`CodeArea` is a variant of `StyleClassedTextArea` that uses a fixed width font by default, making it a convenient base for source code editors. `CodeArea` is used in the [Java Keywords demo](https://github.com/FXMisc/RichTextFX/richtextfx-demos/README.md#automatic-highlighting-of-java-keywords).

