# AtlantaFX

A modern flat design inspired by a variety of web component frameworks.

## Features

* CSS first. It works with existing JavaFX controls.
* Light and dark themes included.
* Simple and intuitive color system based on
  the [GitHub Primer guidelines](https://primer.style/design/foundations/color).
* Fully customizable. Easily change global accent (brand) color or individual control via looked-up color variables.
* Written in modular SASS. No more digging in 3,500 lines of CSS code.
* Custom themes support. Compile your own theme from existing SASS sources.
* Additional controls that are essential for modern GUI development.
* Sampler app:
    * play with themes and fonts
    * test every feature of each existing control and check source code directly in the app to learn how to implement it
    * check color palette and modify theme color contrast
    * hot reload; play with control styles without restarting the whole app
    * showcases that demonstrate real-world project usage

## Getting Started

AtlantaFX requires JavaFX 17+ (because of `data-url` support).

To set the CSS theme:

```java
Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
// ... find more themes in 'atlantafx.base.theme' package
```
