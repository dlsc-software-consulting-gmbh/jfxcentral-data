Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [Release notes for the JavaFX 27.0.1.1](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg31028.html) update have been integrated into the jfx27u branch.
* [JavaFX 28 Early-Access Build 17 published by **OpenJFX / Oracle**](https://jdk.java.net/javafx28/): standalone SDKs and jmods for Linux, macOS (x86_64 and AArch64) and Windows, for testing against early-access JDK 28 builds.
* Integrations from the mailing list:
  * [8394318: Weird characters appear in TextArea with surrogates](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30962.html): the fix for garbled characters in `TextArea` when surrogate pairs are involved has been integrated into OpenJFX.
  * [8395867: Update ICU4C to 79.1](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30984.html): OpenJFX updated its bundled ICU4C text-handling library to version 79.1. The completed change was announced on the openjfx-dev list at https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30991.html.
  * [8396583: WebSocket throws UnsatisfiedLinkError on Linux](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30990.html): A fix for the `UnsatisfiedLinkError` thrown by WebSocket on Linux has been integrated. The [backport to jfx27u](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg31016.html) has been integrated too.
  * [8397389: Add default style class to HeaderBar](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg31055.html): The change that gives `HeaderBar` a default style class has been integrated into OpenJFX.

## Applications

* [**Helal Anwar** shared a description and video on LinkedIn](https://www.linkedin.com/feed/update/urn:li:activity:7528563572105453568/) of "Graded Attendance", an education-management application built for his own coaching centre and used daily with about 100 students. It started as an attendance tool and grew to cover fees, timetables, a question bank, exam scheduling, automatic question-paper and result generation, OMR sheet processing, PDF reports, and camera-based face recognition. It is built with Java, JavaFX, SQLite, OpenCV/JavaCV, Apache PDFBox, ONNX Runtime and JLaTeXMath, and is still under development.
* [**Striking_Creme864** shared on Reddit](https://www.reddit.com/r/JavaFX/comments/1y08p3x/greywolf_the_beginning_of_a_story/) "GreyWolf - The Beginning of a Story", an integrated file management environment built from a core and dynamically loadable plugins, bringing together several earlier projects by the same team such as PatternFX, ShellFX, JediTermFX and CEFFX. Five plugins are available so far (Navigator, Terminal and three DevTools), with more than 20 planned. Navigator is a file manager with Details, List and Tiles views and asynchronous file loading for large folders, and Dual Panels allow two Navigator or Terminal instances side by side. Support for FTP, Samba, Google Drive and archive formats is planned. The project is still far from a first release, but binaries are available on [GitHub Releases](https://github.com/techsenger/greywolf/releases) and contributors are welcome.

## Components, Libraries, Tools

* **Frank Delporte** announced Lottie4J [1.3.0 and 1.4.0](https://webtechie.be/post/lottie4j-1.3.0-and-1.4.0-reading-and-writing-real-world-lottie-files/), the Java and JavaFX library to render [Lottie](https://lottie4j.com/) animations. Version 1.3.0 can load many more real-world Lottie files, and 1.4.0 adds saving animations as Lottie JSON and dotLottie files.

## Tutorials

* [**Fuzzy-System8568** shared on Reddit](https://www.reddit.com/r/JavaFX/comments/1xx1u3k/javafx_packaging_a_developers_guide_demo_guide/) "JavaFX Packaging: A Developer's Guide", a demo and guide repository that teaches how to package JavaFX applications via Maven. It is a default HelloApplication project with a `pom.xml` set up to build an OS-specific, self-contained distributable that is independent of the installed JDK/JRE. Several markdown documents explain the workflow and each step, such as the plugins and the `pom.xml` properties. The author developed it on CachyOS and is looking for contributors with details on other distros and operating systems, such as the WiX toolset requirements for Windows.

## Miscellaneous

* [**Antoinedeloup** asked on Reddit](https://www.reddit.com/r/JavaFX/comments/1xxvaul/writing_the_ui_programmatically_or_through_fxml/) "_Writing the UI programmatically or through FXML?_". A learning developer building a baker's percentage calculator is confused by the many ways to set up a JavaFX project. They like designing in Scene Builder, but wonder how to handle dynamic UI such as an "Add ingredient" button that creates new rows with spinners and labels, and whether the styling from Scene Builder can be reused. Most tutorials and the AtlantaFX Sampler are written programmatically, so they ask how others build their apps and which resources to follow as a novice. The replies mostly recommend FXML with Scene Builder for layout and plain CSS for styling (with a shared style class for children of an `HBox`/`VBox`, as these have no default style class), an MVVM or MVP structure, and the code.makery.ch tutorial. Others point to [FXML/2](https://github.com/jfxcore/fxml-compiler), which compiles FXML to bytecode and suits MVVM, and to the [luvjfx](https://github.com/luvml/luvjfx) and [int4-org/FX](https://github.com/int4-org/FX) DSLs for building UIs in code.
