Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* The [Release Notes for JavaFX 27](https://github.com/openjdk/jfx/blob/master/doc-files/release-notes-27.md) are available in the OpenJFX repository and list everything that went into this release: 7 new features, 20 other enhancements, 77 bug fixes and 6 security fixes.
  * JavaFX 27 requires JDK 25 or later: "*JavaFX 27 is compiled with `--release 25` and thus requires JDK 25 or later in order to run.*" JDK 27 is recommended.
  * "*Metal is now the default rendering pipeline on macOS, replacing the OpenGL-based ES2 pipeline*" (JDK-8373091).
  * The other new features are conditional stylesheet imports (JDK-8364149), ConditionalFeature and Platform media queries (JDK-8374804, JDK-8374822), JavaFX controls in the title bar (JDK-8386617), and two additions to RichTextArea: tab stops attributes (JDK-8356042) and embedded image, text background and wavy underline attributes (JDK-8366198).

## SceneBuilder

* 

## Applications

* 

## Games

* 

## Components, Libraries, Tools

*

## Podcasts, Videos, Books

*

## Conferences, Presentations

*

## Tutorials

*

## Miscellaneous

* [**Nirvex1** is looking for advice on Reddit](https://www.reddit.com/r/javahelp/comments/1wckpbm/building_a_javafx_hibernate_talent_acquisition/) while building a desktop Talent Acquisition System with Java 11, JavaFX, Maven, Hibernate and MariaDB: "_One of the more challenging parts so far has been managing around 17 different FXML views and connecting all of them properly with their controllers. I also had to spend quite a bit of time understanding `module-info.java`, resource paths, and how everything fits together in a modular JavaFX application. Thankfully, that part is finally stable._" The questions are about Hibernate session management and `LazyInitializationException`, how to run database queries without freezing the UI, and which threading patterns and architectural choices keep a large multi-view JavaFX application maintainable. The first answers mostly argue for putting a backend service between the desktop client and the database instead of connecting to it directly.

## JFX Central

* New content on JFX Central:
  * People: [Josh Long](https://www.jfx-central.com/people/j.long)
  * Videos by Josh:
    * [The Ultimate Desktop Client Stack: Building High-Performance Apps with Spring Boot & JavaFX](https://www.jfx-central.com/videos/Od2NDwHED58)
    * [Build Secure Desktop Apps with JavaFX, Spring Boot & PKCE OAuth 2.0](https://www.jfx-central.com/videos/gB7FIbxMEos)
  * Tutorials based on these videos:
    * [JavaFX and Spring Boot](https://www.jfx-central.com/learn-javafx/spring-boot)
    * [Securing a JavaFX desktop application with Spring Security and OAuth 2.0](https://www.jfx-central.com/learn-javafx/spring-boot-oauth)
