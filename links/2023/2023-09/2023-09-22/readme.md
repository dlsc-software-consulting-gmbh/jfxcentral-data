## Core

* This week OpenJDK and OpenJDK 21 got officially released!
* [Announcement by **GluonHQ**](https://techhub.social/@gluonhq/111092296190292324): "We're proud to announce JavaFX 21 GA. Download the SDK/jmods from [gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/) or get the artifacts from Maven central. Release notes with many fixes and enhancements at [gluonhq.com/products/javafx/openjfx-21-release-notes](https://gluonhq.com/products/javafx/openjfx-21-release-notes/). Great work by a growing number of contributors!"
* [Release notes by **Kevin Rushforth** on GitHub](https://github.com/openjdk/jfx/blob/master/doc-files/release-notes-21.md).
* Builds are available from java.net:
  * [Linux / x64](https://download.java.net/java/GA/javafx21/69ca518c413e4df09f6be747a2400cf6/31/GPL/openjfx-21_linux-x64_bin-sdk.tar.gz)
  * [macOS / AArch64](https://download.java.net/java/GA/javafx21/69ca518c413e4df09f6be747a2400cf6/31/GPL/openjfx-21_macos-aarch64_bin-sdk.tar.gz)
  * [macOS / x64](https://download.java.net/java/GA/javafx21/69ca518c413e4df09f6be747a2400cf6/31/GPL/openjfx-21_macos-x64_bin-sdk.tar.gz)
  * [Windows / x64](https://download.java.net/java/GA/javafx21/69ca518c413e4df09f6be747a2400cf6/31/GPL/openjfx-21_windows-x64_bin-sdk.zip)
  * And on the [Gluon website](https://gluonhq.com/products/javafx/).

## Applications

* [**Biometrics Engineer** shared a video](https://twitter.com/Biometrics_Eng/status/1704443412546474137) showing a "JavaFX Linux Biometric Time and Attendance - Staff Registration DEMO that is implemented on Ubuntu Linux using ARATEK A600 Biometric Fingerprint Scanner."
* [**RNArtist** is announcing](https://twitter.com/rnartist_app/status/1702674834193252446): "Installers will be available next week. Thanks to the very good tool JDeploy, you will automatically get the latest version after each launch of RNArtist. Now it's time to write some documentation..."
  * And shared [screenshots of the tool 3 years ago versus now](https://twitter.com/rnartist_app/status/1702641134923583639), with some very nice comments and links to the project in the thread.
  * BTW, as someone pointed out, you can maybe use this tool to design airport terminals... ;-)
* [**Sean Phillips** is using Trinity for](https://twitter.com/SeanMiPhillips/status/1703038446854345000): "Detecting ChatGTP generated medium size text blocks using manifold approximation, polyhedral volume techniques and JavaFX 3D. Sorry not sorry bad guys."
  * And [Apple M2 silicon (Arm64) builds](https://twitter.com/SeanMiPhillips/status/1703172036669972494) now supported through Trinity's GitHub Actions thanks to the magnificent CI work of samypr100!

## Games

* [**OrangoMango** announces "Chess 2.0 is finally complete"](https://twitter.com/orango_mango/status/1703057689582993815): "The WebFX version is now available at [orangomango.itch.io/chess](https://orangomango.itch.io/chess). Play single player against stockfish. Play multiplayer against a friend in LAN or on the server."
* Always fun when [**Almas Baim** shares one of his experiments with FXGL](https://twitter.com/AlmasBaim/status/1703483255331094569): "... and in this episode of how to inefficiently clear the screen ..."
* [**Ahmed Bakr** completed a Tic Tac Toe Game with Tiva C and JavaFX](https://www.linkedin.com/feed/update/urn:li:activity:7109933250885685248/): "Our primary goal in undertaking this project was to explore the integration of microcontrollers with high-powered processing computers to tackle tasks beyond the capabilities of a standalone microcontroller."

## Components, Libraries, Tools

* [**Dirk Lemmermann** has a friendly reminder](https://twitter.com/dlemmermann/status/1704045289743495407): "When creating custom controls for your JavaFX project, please make sure you have a way to properly test them standalone (e.g. via [FXSampler](https://jfx-central.com/tools/fxsampler))."
  * He is also [pimping the CalendarView control in GemsFX](https://twitter.com/dlemmermann/status/1704846555004502306) and adding all kinds of options: "E.g. different layout for the header and also quick picking months and years). Come and check it out, let me know what you think or I missed."
* [**Pedro Duque Vieira** added a new API to FXComponents](https://twitter.com/P_Duke/status/1704488446138454335): "To allow you to change the native Window frame color, native Window text color, and border. (Standard JavaFX API will always show a native Window frame with the same light color with no possibility to change it.)"
  * The release of the new FXComponents library was also [announced on Foojay](https://foojay.io/today/new-fxcomponents-library-released/).

## Miscellaneous

* [**WhiteWoodCity** is combining JavaFX and JavaScript](https://twitter.com/WhiteWoodCity/status/1704296530117906743): "Using #Graal polyglot in a JavaFX program."
* [**Webswing**](https://twitter.com/Webswing_org), a specialized web server for running Java Swing and JavaFX based applications in a web browser, announced a [Spanish version of their website](https://www.webswing.org/es).
* From time to time, JavaFX (just like Java itself) is declared dead. Luckily, there are many fans to correct this mistake:
  * [**JavaFX3D**](https://twitter.com/JavaFX3D/status/1705089119041593691): "As someone who worked on #JavaFX at both Sun and Oracle, I can definitively say that #JavaFX is not deprecated. Period."
  * [**Jonathan Ellis**](https://twitter.com/spyced/status/1704888013094834602): "Users hate Electron's performance and memory footprint, but devs love it for write-once-run-anywhere. I know we stopped using Swing years ago for good reasons, but still: wouldn't JavaFX be better than Electron?"
  * [**JavaFX3D**](): "I keep seeing uninformed posts, so I'd like to set the record straight. [**Kevin Rushforth** and his team at Oracle are working on JavaFX](https://www.youtube.com/watch?v=FFlVaB8oTi0). Equally importantly, **Johan Vos** and his team at **Gluon** are the JavaFX WORA magicians."
  * [**Sean Phillips**](https://twitter.com/SeanMiPhillips/status/1703401715351904439): "As the lead developer for several of the tools referenced in that article I can say as ground truth that JavaFX was selected as the superior tech stack due to a combination of quality, performance and cross-platform support."
  * [**SystemsInCode**](https://twitter.com/SystemsInCode/status/1704796679340224546): "I would love to leverage the benefits of javaFX but it's a hard sell in enterprise when we need to push a critical fix...its a shame we have to use sub optimal tech for boring practical reasons :) Being able to FORCE latest version on people even with low rights is critical."

## JFX Central

* New content added to JFX Central
  * Library: [FXComponents](https://www.jfx-central.com/libraries/fxcomponents)
  * Showcase: [M-AID](https://www.jfx-central.com/real_world/maid)
* The post by **Frank Delporte** about the new version of JFX Central got republished on Foojay:
  * [Part 1: Description of the site and changes](https://foojay.io/today/new-user-interface-for-jfx-central-the-home-for-all-javafx-information-part-1/).
  * [Part 2: Interviews with some of the team members](https://foojay.io/today/new-user-interface-for-jfx-central-the-home-for-all-javafx-information-part-2/).
