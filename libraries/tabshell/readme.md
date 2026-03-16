Techsenger TabShell is a platform for building tab-based applications in JavaFX, where an application is structured as
a tree of MVP components. The platform provides abstract classes for creating the main types of components: tab,
area, page, dialog, and popup, as well as containers for them.

It also includes ready-to-use implementations of containers (including a docking layout) and dialogs (including a
universal file chooser). In addition, the platform provides powerful devtools that allow you to inspect both the MVP
component tree and the underlying JavaFX scene graph. These tools make it easy to understand how the platform works
and are invaluable during development.

TabShell is built around two core subsystems: the dynamic main menu and the workspace. The main menu is assembled
at runtime and automatically adapts to the currently focused component. The workspace provides the structural
foundation of the application and defines how components are arranged and interact visually. The platform supports
two primary workspace models: browser-like and IDE-like.

TabShell is built on top of the [PatternFX](https://github.com/techsenger/patternfx) framework.

## Features

Key features of TabShell include:

* Dynamically configurable menu.
* Support for different types of workspace.
* Abstract classes to simplify component development.
* A set of ready-made components that can be used out of the box.
* Support for different layouts, including a docking layout.
* Set of devtools for inspecting the application at both the component layer and the JavaFX scene graph layer.
* Ability to preserve component history.
* Support for inline dialogs with two scopes — shell and tab.
* Window styling that matches the theme.
* Support for 7 themes (4 dark and 3 light).
* API for working with all colors in the palettes of all themes
* Styling with CSS.

