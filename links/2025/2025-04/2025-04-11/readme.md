Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* The release notes for JavaFX 24.0.1 are [being prepared in this pull request](https://github.com/openjdk/jfx24u/pull/19/files).
* [**Chad Preisler** shared some tips to distribute JavaFX apps](https://bsky.app/profile/chadpreisler.bsky.social): 
    * "_One excellent way to distribute JavaFX applications is to use jlink to build custom images. You can build for Linux, Windows, and Mac all on one machine without having all the host operating systems. The package is small, and you don't need to install the JDK on each client._3
    * "_My JavaFX application bundle, built for MacOS X with jlink is just 41MB. I built the and bundled the app on Linux and ran it on OS X. No need to install Java on every client with a jlink image. I used JDK 24 to build the application._"
* [A tip by **Wolfgang Weigend**](https://bsky.app/profile/wolfgangweigend.bsky.social/post/3llya7y6bfs23) related to [JEP 493](https://openjdk.org/jeps/493): "_Add JavaFX 24 to your jdk-24.jdk image with cmd `jlink --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,jfx.incubator.input,jfx.incubator.richtext  --output image` and verify file release and the directory `image/lib`._"

## SceneBuilder

* [SceneBuilder 24.0.0](https://gluonhq.com/scene-builder-24-0-0-ga-is-here/) is available. Highlight:
  * Scene Builder Kit is now published to Maven Central, making it easier for developers to integrate it into their software.
  * The Gluon controls, that are part of Scene Builder, were tightly coupled with Scene Builder Kit. Now those has been abstracted away into a pluggable component called Gluon Plugin. 
  * Scene Builder has been updated for full compatibility with the Java Platform Module System (JPMS). 
  * Logging to console is now enabled by default to make it easier to debug issues in Scene Builder. 
  * Multiple fixes went into Accordion, DialogPane, TextField etc. to make Scene Builder more robust.

## Applications

* [**Patrik Karlström** released a new version of nbRsync](https://bsky.app/profile/trixon.se/post/3lmcattkucc2w): "_A GUI for rsync, written in Java and JavaFX atop the netbeans platform. Available as [appimage, snap, platform zips with and without a bundled JDK 24](https://github.com/trixon/nbRsync/releases/tag/v25.04)._"
* [**Serendipity** released version 2 of SmartFinder](https://bsky.app/profile/serendigityinfo.bsky.social/post/3lma43kntls27): "_Now with advanced filter building to create powerful, custom queries on file attributes and metadata. Stay tuned & level up your file search!_" A [video demo is available on YouTube](https://www.youtube.com/watch?v=Vsm3W5zeP2A).

## Components, Libraries, Tools

* [Here you can find a list of all the Visual Studio Code extensions](https://open-vsx.org/?search=sosuisha&sortBy=relevance&sortOrder=desc) created by [**Hidekazu Kubota**](https://github.com/sosuisen/): "_I have registered all the VSCode extensions I created for JavaFX in the Open VSX repository. Now, it's possible to develop with JavaFX in VSCode-compatible editors like TheiaIDE._"

## Podcasts, Videos, Books

* **Frank Delporte** [interviewed **Gerrit Grunwald (aka hansolo)** for the JavaFX In Action series](): "_Gerrit created many JavaFX libraries and blog posts. I wanted to talk with him about his work with JavaFX, but I also learned more about SVGs and how the garbage collectors in the JVM are working, thanks to the amazing visualizations he creates with ... JavaFX of course._" 
* Some feedback on this interview:
  * [By **Matt Coley**](https://bsky.app/profile/mattcoley.bsky.social/post/3lmh3rnswp22n): "_JavaFX has a perception problem. People will say 'Why not Compose?' before 'Why not FX?' even though Compose doesn't have a functional context-menu component for desktop yet. Compose just has a much better PR department even though for desktop its a much lesser offering. FX needs a PR boost. To elaborate on the Compose remark, you can have a WindowScope MenuBar, but it's only a wrapper that creates an un-styled Swing MenuBar. Additionally, generic compose context menus only allow text+onAction properties. For anything more the official docs tell you to use Swing's JPopupMenu. The point being, an unfinished product for desktop application is given much more public praise over JavaFX due to the perceived 'FX is dead' problem. That's the sorry state of things at the moment. I love FX and this makes my soul hurt._"
  * [By **Catherine Edelveis**](https://bsky.app/profile/cat-edelveis.bsky.social/post/3lmjapuqrdk2d): "_Gerrit proves that Java is by no means a stranger in the world of UIs. I mean, all the cool libraries he's built make me even more enthusiastic about my aspirations with #JavaFX. And I can see that I've only touched a tip of the iceberg by now 😁._"
  * YouTube comment by **Michel Antony Barros Barrios**: "_Amazing, I remember have used several components of Gerrit's libraries to show data analytics about billing behavior in a project I work some years ago. His libraries are wonderful._"

## Tutorials

* **Frank Delporte** always forgets "_how to create a JavaFX ComboBox and configure it to show a specific field of an object in the opened and closed state of the ComboBox_" and [wrote a tutorial](https://webtechie.be/post/2025-04-09-javafx-combobox-with-objects/) "_hoping I remember that I blogged about it, the next time I need this functionality_".

## Miscellaneous

* [**Catherine Edelveis** asks your advise](https://bsky.app/profile/cat-edelveis.bsky.social/post/3lmc6dschus2f): "_Friends, does anyone know what is the best way of adding image urls to JavaFX CSS file?_"

## JFX Central

* New content:
  * Video: [JavaFX In Action with Gerrit Grunwald: Creator of Many Amazing JavaFX Libraries](https://www.jfx-central.com/videos/6pgHlHLrX8c)

