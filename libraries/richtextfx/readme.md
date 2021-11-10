RichTextFX provides a memory-efficient text area for JavaFX that allows the developer to style ranges of text and display custom objects in-line (no more HTMLEditor).

It is intended as a base for rich-text editors and code editors with syntax highlighting. Since it is a base, a number of suggested features (specific syntax highlighters, search-and-replace, specific support for hyperlinks, etc.) have not be implemented directly in this project. Rather, developers can implement these on top of RichTextFX and submit their work as a PR to the `richtextfx-demos` package.

For a greater explanation of RichTextFX, its design principles, how it works, and how to style its areas via CSS, please [see the wiki](https://github.com/FXMisc/RichTextFX/wiki)

### Demos

Stand-alone applications that demonstrate some of the features of RichTextFX can be found [here](https://github.com/FXMisc/RichTextFX/richtextfx-demos/)

### Features

* Assign arbitrary styles to arbitrary ranges of text. A style can be an object, a CSS string, or a style class string.
* Display line numbers or, more generally, any graphic in front of each paragraph. Can be used to show breakpoint toggles on each line of code.
* Support for displaying other Nodes in-line.
* Positioning a popup window relative to the caret or selection. Useful e.g. to position an autocompletion box.
* Getting the character index under the mouse when the mouse stays still over the text for a specified period of time. Useful for displaying tooltips depending on the word under the mouse.
* Overriding the default behavior only where necessary without overriding any other part.