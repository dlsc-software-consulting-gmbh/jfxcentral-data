Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* The [Release Notes for JavaFX 27](https://github.com/openjdk/jfx/blob/master/doc-files/release-notes-27.md) are available in the OpenJFX repository and list everything that went into this release: 7 new features, 20 other enhancements, 77 bug fixes and 6 security fixes.
  * JavaFX 27 requires JDK 25 or later: "*JavaFX 27 is compiled with `--release 25` and thus requires JDK 25 or later in order to run.*" JDK 27 is recommended.
  * "*Metal is now the default rendering pipeline on macOS, replacing the OpenGL-based ES2 pipeline*" (JDK-8373091).
  * The other new features are conditional stylesheet imports (JDK-8364149), ConditionalFeature and Platform media queries (JDK-8374804, JDK-8374822), JavaFX controls in the title bar (JDK-8386617), and two additions to RichTextArea: tab stops attributes (JDK-8356042) and embedded image, text background and wavy underline attributes (JDK-8366198).
* The **Gluon Team** published [JavaFX 27 is Now Available](https://gluonhq.com/news/2026-09-15-javafx-27-is-now-available/) with an overview of the release: the Metal rendering pipeline as the new default on macOS, conditional stylesheets and media queries in CSS, JavaFX controls in the window title bar, the RichTextArea additions, and over 70 bug fixes and control performance improvements. Two things to check before upgrading: JavaFX 27 is compiled with `--release 25`, so an older JDK fails at startup, and on macOS you can fall back to the previous pipeline with the `-Dprism.order=es2` launch parameter.
* **Andy Goryachev** integrated [8390913: RichTextArea: Down arrow stuck on paragraph if space above and below are set](https://mail-archive.com/openjfx-dev@openjdk.org/msg29554.html): "_Fixes broken navigation introduced in JDK-8370902 with non-zero paragraph spacing (above, below, line spacing)._" The Rich Editor Demo was also updated to allow setting paragraph line spacing in addition to space above/below (right click → Paragraph). A sizeable change at 539 lines across 11 files, reviewed by **Jose Pereda** and **Lukasz Kostyra**, integrated on 15 September. ([PR on GitHub](https://github.com/openjdk/jfx/pull/2280), [changeset](https://github.com/openjdk/jfx/commit/23c3580cc2d25b13dbf4cb8142afb51691c2cf5f))
* **Marius Hanl** integrated [8392149: Mark deprecated snap methods for removal in SkinBase](https://mail-archive.com/openjfx-dev@openjdk.org/msg29563.html), a heads-up for anyone writing custom skins: "_Since we will remove the snapping methods in `Region` at one point, we should also do that in `SkinBase`._" A continuation of JDK-8390773, reviewed by **Kevin Rushforth** and **Andy Goryachev**, integrated on 15 September. ([PR on GitHub](https://github.com/openjdk/jfx/pull/2311), [changeset](https://github.com/openjdk/jfx/commit/bb1789c15faadc866656a5ee2520e1ee4cabd95a))

## SceneBuilder

* 

## Applications

* 

## Games

* 

## Components, Libraries, Tools

*

## Podcasts, Videos, Books

* [**Artistic_Solution117** shared a video on Reddit](https://www.reddit.com/r/JavaFX/comments/1wg2whq/pure_javafx_google_earth_nasa_worldwind_like/) of a "_Pure JavaFX Google Earth / Nasa Worldwind like viewer in a single shot. On-the-fly on the JVM were the agent runs._" The 11-minute screen recording, [Second test of Java-EARTH-GLOBE-1](https://youtu.be/76pWS0WFu_U) on the **Anahata TV - Java AI, AGI, ASI** channel, shows the result, built with "_gemini 3.8 flash with 24-core raytracing and local disk caching_".

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
