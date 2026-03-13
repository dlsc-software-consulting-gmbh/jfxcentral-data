A rapid JavaFX development framework following a classic MVC structure.

## Features

- HTML views and full application configuration for macOS, Windows, and Linux/UNIX
- Structured logging via [SLF4J](https://www.slf4j.org/) and [log4j2](https://logging.apache.org/log4j/2.x/)
- Installer component with configurable steps (license agreement, settings, etc.)
- Registration view and product update checker (configurable via online XML)
- Includes [autumo-commons](https://products.autumo.ch/javadoc/autumo-commons/index.html): caching, SMS/call API, config & DB manager, mailing, and utilities

[Purchase / Download / Demo](https://products.autumo.ch/modules/overview#at_ui)

## Quickstart

1. Reference the `lib` directory in your project, or install with `maven-install.sh|bat`.
2. Subclass `ch.autumo.ui.fx.BaseApp`.
3. API docs: [autumo-ui](https://products.autumo.ch/javadoc/autumo-ui/index.html) · [autumo-commons](https://products.autumo.ch/javadoc/autumo-commons/index.html)
4. See the `etc` directory for the developer guide.
