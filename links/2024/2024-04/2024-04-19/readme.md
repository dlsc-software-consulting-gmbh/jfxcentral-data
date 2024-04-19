## Core

* **Gluon** published new releases of JavaFX: 22.0.1 and the LTS releases 17.0.11 and 21.0.3. You can get them from Maven Central and the [Gluon website](https://gluonhq.com/products/javafx/).
* The new builds of Java and JavaFX, that were released this week, contain several fixes related to Common Vulnerabilities and Exposures (CVE), see for example the [release notes of Azul Zulu](https://docs.azul.com/core/release-notes#fixed-common-vulnerabilities-and-exposures). The 3-monthly security update schedule, guarantees that detected CVEs are fixed quickly and become available in a well-organized and documented way. Thanks to the power of the OpenJDK community!

## Components, Libraries, Tools

* [**Dirk Lemmermann**](https://twitter.com/dlemmermann/) shared a lot of updates for the GemsFX library, thanks to contributions by [**Li Wang Yang**](https://twitter.com/LeeWyatt_7788), see [sources on GitHub](https://github.com/dlsc-software-consulting-gmbh/GemsFX). Here are some screenshots:
  * [LimitedTextArea](https://twitter.com/dlemmermann/status/1780122735177339359), which allows you to specify the maximum content length (and is resizable as it inherits from ResizableTextArea). 
  * [Responsive pane](https://twitter.com/dlemmermann/status/1780123307901136965), which allows you to show a node on one of the four sides either fully or in a smaller size depending on available space (or not at all when there is too little space).
  * [Ccircle progress indicator](https://twitter.com/dlemmermann/status/1780123562163961858).
  * [EnhancedPasswordField](https://twitter.com/dlemmermann/status/1780122006823883068), which gives you additional capabilities compared to the standard #avaFX password field (e.g. "show password").
  * [Tree and graph view](https://twitter.com/dlemmermann/status/1780123785334522160).

## Tutorials

* [**Rushi Bhatti** shared a video showing three small JavaFX experiments](https://twitter.com/RB_Bhatti_171/status/1776931544688734495), you can find the [sources on GitHub](https://github.com/RushiBhatti/JavaFX_Projects):
  * Tic Tac Toe (basic gameplay)
  * BMI Calculator (calculates your Body Mass Index)
  * Indian Flag (a simple tribute)

## Miscellaneous

* For macOS 14 users: [**Gerrit Grunwald** published a new version of JDKUpdater](https://twitter.com/hansolo_/status/1778774663939707037) with the ability to download builds of OpenJDK from different distributions, with or without JavaFX included. [Sources and releases are available on GitHub](https://github.com/HanSolo/JDK-Updater/releases).
* Do you want to use JavaFX on the Raspberry Pi? Thanks to contributions by **Robert von Burg**, the [Pi4J JavaFX example project](https://github.com/Pi4J/pi4j-example-javafx) downloads JavaFX 22 from the Gluon website automatically when you build the application with Maven.
