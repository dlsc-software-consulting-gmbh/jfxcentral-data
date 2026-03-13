A modern flat design JavaFX theme inspired by web component frameworks.

## Features

- **CSS-first** — works with existing JavaFX controls; no code changes required
- Light and dark themes included
- Intuitive color system based on [GitHub Primer guidelines](https://primer.style/design/foundations/color)
- Fully customizable via looked-up CSS color variables (global accent or per-control)
- Written in modular SASS; supports custom theme compilation
- Additional controls for modern GUI development
- Sampler app with live theme/font switching, color palette editor, hot reload, and real-world showcases

## Getting Started

Requires JavaFX 17+ (for `data-url` support).

```java
Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
// More themes available in 'atlantafx.base.theme' package
```
