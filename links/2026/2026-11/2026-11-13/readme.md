**Took some days off last week, so skipped a week for the links overview. And as a result... yes, a much longer list then usual today :-)**

Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [Integrated: 8396109: [Linux] Robot: screencast restore token is wrongly assumed to be a UUID](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30775.html): A fix was integrated into OpenJFX correcting an incorrect assumption that the Robot screencast restore token on Linux is always formatted as a UUID.
* [Integrated: 8395994: Update GStreamer to 1.28.8](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30777.html): The bundled GStreamer dependency was updated to version 1.28.8 (integrated for the jfx27u maintenance branch).
* [Integrated: 8391822: Use Gradle's Java Toolchain](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30812.html): A change to use Gradle's Java Toolchain support has been integrated into the OpenJFX codebase.
* [Integrated: JDK-8394228: Property value set by a listener during CSS reset is not overridden by its style](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30831.html): The OpenJFX fix for issue 8394228 was integrated into the mainline, resolving a CSS reset edge case where a listener-set property value could be incorrectly overridden.

## SceneBuilder

* [**Emad Hanif** shared on LinkedIn](https://www.linkedin.com/posts/emad-hanif-00b4aa227_the-one-core-feature-id-love-to-see-in-scenebuilder-share-7525911503078752256-td8G/) the one core feature he would love to see in SceneBuilder: automatic conversion of FXML into abstract Java classes, such as `public abstract class Home extends StackPane {...}`, formatted with Google Java Formatter. He already automates this in his own workflow, but thinks it should be the default for two reasons: reusability and obfuscation, as FXML files can't be obfuscated. Do you agree? Join the discussion on LinkedIn.
* [**Gluon** (**José Pereda**) published the Scene Builder 27.0.0-RC release candidate on GitHub](https://github.com/gluonhq/scenebuilder/releases/tag/27.0.0-RC): The changelog covers build and packaging changes, including removal of the macOS Intel installer. Installers are available for Windows, Linux and macOS Apple Silicon, plus the Scene Builder Kit jar.

## Applications

* [**xdsswar** showed on Reddit](https://www.reddit.com/r/JavaFX/comments/1xr2dpy/a_full_pdf_suite_in_100_javafx_on_my_own_engine/) Mergi, a Windows desktop PDF suite built in 100% JavaFX, without WebView, AWT or Swing, on top of the author's own pure Java PDF engine jux-pdf. It offers a viewer, full editing, comments, forms, signatures, real redaction, a small word processor that writes PDFs, conversion to Word and Excel, and everyday tools such as merge, split, compress and protect. Mergi will be released with a free and a paid version, and the viewer and editor will also be published as a JavaFX control.
* [**Guilty_Village_958** showed on Reddit](https://www.reddit.com/r/JavaFX/comments/1xnunnr/multiuser_javafx_client_for_whatsapp_with_gated/) a multi-user WhatsApp client built with JavaFX and Whatsmeow, made with Claude. Up to 8 numbers can be logged in at once, each with its own color throughout the UI, and the app uses less than half the RAM of the regular WhatsApp desktop app. A CLI lets tools such as Claude Code automate WhatsApp, gated by approvals given in the UI per process tree, limited in time and scope. It is an unofficial hobby project, with [source code and a pre-release download on GitHub](https://github.com/chatsgate/whatsappfx/).
* [**Serendipity** announced on Bluesky](https://bsky.app/profile/serendigityinfo.bsky.social/post/3mzvjvjnmqk2q) the release of [SmartFinder 3.0.0](https://serendigity.it/products/smartfinder/smartfinder-en/), a search tool positioned as an alternative to Windows Search and macOS Finder/Spotlight. The new version for Windows and macOS has an updated UI and adds macOS tags search.
* [**Michiel** announced on Bluesky](https://bsky.app/profile/dotjava.bsky.social/post/3mzss2iexdc2k) that his second 100% Java and JavaFX app is available in the Google Play Store: [HoliCur](https://play.google.com/store/apps/details?id=nl.dotjava.javafx.holidayconverter), a holiday currency converter supporting 21 currencies. It is based on his open-source IceCo app, but HoliCur itself is not open source. It has no ads or data analysis, and more details are on [the author's website](https://www.dotjava.nl/holicur/).

## Components, Libraries, Tools

* [**Shai Almog** published "Flutter Better in Java"](https://foojay.io/today/flutter-better-in-java/) on Foojay. A large part of Swing and JavaFX now builds for iOS, Android and the web with Codename One: a JavaFX 2048 game runs on an Android phone, and FXML and CSS forms run on an iPhone. On Linux, the native build is 4 to 6 times smaller zipped than the same app on the JVM and starts faster in all 8 tested apps, even against a JVM tuned with AppCDS.
* [**Tomarka** published Workbench Themes on Reddit](https://www.reddit.com/r/JavaFX/comments/1xpih6m/workbench_themes_two_intellijinspired_atlantafx/), two AtlantaFX themes, Workbench Dark and Workbench Light, based on the color palettes of the IntelliJ themes from Familiar Java Themes. They implement the AtlantaFX `Theme` interface, so they work with `ThemeManager`, and plain CSS files are available too. The library is available on Maven Central and MIT licensed, and the author notes it was built with Claude Code. [Source on GitHub](https://github.com/tomarkar/workbench-themes).

## Podcasts, Videos, Books

* [**Chad Preisler** shared on Bluesky](https://bsky.app/profile/chadpreisler.bsky.social/post/3mzpvgzv4l22x) episode 2 of his series "Why'd they do it that way?", [JavaFX animation](https://youtu.be/j4HZeNwAWTk), explaining why he used JavaFX for an animation, with the story and the code.

## Tutorials

* [**Libán Bande González** published "Building a GlassPaneBase Control in JavaFX"](https://foojay.io/today/building-a-glasspanebase-control-in-javafx/) on Foojay Today, showing how to build a reusable control for frosted-glass surfaces. Using the Control and Skin architecture, the skin stacks a blurred backdrop snapshot, a tint, a border and a content layer. The same approach is used in the Vitra UI library, created by the author.

## Miscellaneous

* [**Wolfgang Weigend** announced on Bluesky](https://bsky.app/profile/wolfgangweigend.bsky.social/post/3n24fepjpec2o) that his article about JavaFX, written in German, is published in the new Javamagazin 12.2026. The article covers the JavaFX UI technology.
* [**neopointer** asked on Reddit](https://www.reddit.com/r/JavaFX/comments/1xk68hx/javafx_architecture/) which architecture to use for a JavaFX application. Commenters suggested MVC with FXML and Scene Builder, MVVM as used by [JabRef](https://devdocs.jabref.org/code-howtos/javafx.html#architecture-model---view---controller---viewmodel-mvcvm) with data binding between View and ViewModel, and the MVCI pattern. Some also pointed out that JavaFX lacks a definitive guide on architecture and packaging.
* [**V_Bifi** asked on Reddit](https://www.reddit.com/r/JavaFX/comments/1xknh3j/javafx_environment/) how others set up their JavaFX environment, from IDE and embedded database to deployment and distribution, as it feels harder than the old Eclipse with e(fx)clipse days. Commenters recommended a JDK with JavaFX included such as Liberica JDK Full or Azul JDKFX, IntelliJ IDEA with JavaFX as a Maven dependency and jpackage for distribution, or Gradle with the JavaFX plugin and jlink, as in [this build.gradle](https://github.com/CodeDead/opal/blob/master/build.gradle). The author ran into problems combining jlink with the multi-module H2 database.
* [**hamsterrage1** announced on Reddit](https://www.reddit.com/r/JavaFX/comments/1xqeo4k/website_rebuild/) a complete rebuild of [pragmaticcoding.ca](https://www.pragmaticcoding.ca), moving from Jekyll and GitHub Pages to Hugo with the Blowfish theme. The JavaFX content is reorganized: the "Elements" section explains how JavaFX components work, while the new "Techniques" section covers how to use them, including custom Nodes and the MVCI pattern. The articles aim to explain first principles, and most later code samples are in Kotlin.

## JFX Central

* Content update on JFX Central: 
  * Library: the commercial library autumo UI got replaced by the open-source (free) library [autumo UI FX](https://www.jfx-central.com/libraries/autumouifx), "_a rapid Java FX development framework_".
* New content on JFX Central:
  * Book: [Skinning JavaFX Applications](https://www.jfx-central.com/books/skinning-javafx-applications), a code-first guide to the Control–Skin architecture. 
  * Libraries:
    * [Vitra](https://www.jfx-central.com/libraries/vitra), rich controls customizable from CSS.
    * [Celestia](https://www.jfx-central.com/libraries/celestia), a collection of micro-interactions.
  * People: [Libán Bande González](https://www.jfx-central.com/people/l.bande-gonzalez), author of this book and the libraries.
