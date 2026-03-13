# RichTextFX

A memory-efficient JavaFX text area for styling ranges of text and displaying custom objects in-line. Designed as a base for rich-text editors and syntax-highlighting code editors — higher-level features (syntax highlighters, search-and-replace, hyperlinks) are left to consumers or the _richtextfx-demos_ package.

See the [wiki](https://github.com/FXMisc/RichTextFX/wiki) for design principles, architecture, and CSS styling. Demo applications are [here](https://github.com/FXMisc/RichTextFX/tree/master/richtextfx-demos).

## Features

- Assign arbitrary styles (object, CSS string, or style class) to arbitrary text ranges
- Line numbers or custom graphics per paragraph (e.g., breakpoint toggles)
- Inline node display and popup window positioning relative to caret or selection
- Character index lookup on mouse hover (useful for tooltips)
- Overridable default behaviors without affecting unrelated parts