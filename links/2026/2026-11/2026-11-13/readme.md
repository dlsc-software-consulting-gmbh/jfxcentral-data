Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [Integrated: 8396109: [Linux] Robot: screencast restore token is wrongly assumed to be a UUID](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30775.html): A fix was integrated into OpenJFX correcting an incorrect assumption that the Robot screencast restore token on Linux is always formatted as a UUID.
* [Integrated: 8395994: Update GStreamer to 1.28.8](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30777.html): The bundled GStreamer dependency was updated to version 1.28.8 (integrated for the jfx27u maintenance branch).
* [Integrated: 8391822: Use Gradle's Java Toolchain](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30812.html): A change to use Gradle's Java Toolchain support has been integrated into the OpenJFX codebase.
* [Intregrated: JDK-8394228: Property value set by a listener during CSS reset is not overridden by its style](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30831.html): The OpenJFX fix for issue 8394228 was integrated into the mainline, resolving a CSS reset edge case where a listener-set property value could be incorrectly overridden.


## SceneBuilder

* 

## Applications

* 

## Games

* 

## Components, Libraries, Tools

* [**Shai Almog** published "Flutter Better in Java"](https://www.codenameone.com/blog/flutter-better-in-java/) on the Codename One blog, introducing a week-long series on Kotlin Multiplatform, Swing, JavaFX, Android compatibility and new templates. A large part of Swing and JavaFX now builds for iOS, Android and the web with Codename One: a JavaFX 2048 game runs on an Android phone, and FXML and CSS forms run on an iPhone. On Linux, the native build is 4 to 6 times smaller zipped than the same app on the JVM and starts faster in all 8 tested apps, even against a JVM tuned with AppCDS. A full article on what carries over from JavaFX and what doesn't is announced for Sunday, November 1.

## Podcasts, Videos, Books

*

## Conferences, Presentations

*

## Tutorials

* [**Libán Bande González** published "Building a GlassPaneBase Control in JavaFX"](https://foojay.io/today/building-a-glasspanebase-control-in-javafx/) on Foojay Today, showing how to build a reusable control for frosted-glass surfaces. Using the Control and Skin architecture, the skin stacks a blurred backdrop snapshot, a tint, a border and a content layer. The same approach is used in the Vitra UI library, created by the author.

## Miscellaneous

* [**neopointer** asked on Reddit](https://www.reddit.com/r/JavaFX/comments/1xk68hx/javafx_architecture/) which architecture to use for a JavaFX application. Commenters suggested MVC with FXML and Scene Builder, MVVM as used by [JabRef](https://devdocs.jabref.org/code-howtos/javafx.html#architecture-model---view---controller---viewmodel-mvcvm) with data binding between View and ViewModel, and the MVCI pattern described on [Pragmatic Coding](https://www.pragmaticcoding.ca/javafx/mvci/). Some also pointed out that JavaFX lacks a definitive guide on architecture and packaging.
* [**V_Bifi** asked on Reddit](https://www.reddit.com/r/JavaFX/comments/1xknh3j/javafx_environment/) how others set up their JavaFX environment, from IDE and embedded database to deployment and distribution, as it feels harder than the old Eclipse with e(fx)clipse days. Commenters recommended a JDK with JavaFX included such as Liberica JDK Full or Azul JDKFX, IntelliJ IDEA with JavaFX as a Maven dependency and jpackage for distribution, or Gradle with the JavaFX plugin and jlink, as in [this build.gradle](https://github.com/CodeDead/opal/blob/master/build.gradle). The author ran into problems combining jlink with the multi-module H2 database.

## JFX Central

* Content update: the commercial library autumo UI got replaced by the open-source (free) library [autumo UI FX](https://www.jfx-central.com/libraries/autumouifx), "_a rapid Java FX development framework_".
