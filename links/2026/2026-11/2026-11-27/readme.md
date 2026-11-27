Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* From the mailing list:
  * [[jfx25u] Integrated: 8375466: Metal rendering pipeline crashes on virtualized OS](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg31111.html): Fix for a Metal rendering pipeline crash on virtualized operating systems, integrated into the JavaFX 25 update release line (jfx25u).
  * [Integrated: 8392117: VirtualFlow may end up creating 1-2 unnecessary cells](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg31116.html): Fix integrated so VirtualFlow, which backs ListView, TableView and TreeView, no longer creates 1-2 unnecessary cells.

## Applications

* [**XenFive** shared on Reddit](https://www.reddit.com/r/JavaFX/comments/1y2x0oz/klunk_1004_released_a_log_viewer_built_with_javafx/) the release of [Klunk 1.0.04](https://www.kookaklunka.com/klunk), a free, cross-platform log viewer built with Java 25 and JavaFX 26. It loads logs from disk, SFTP, Kubernetes pods, URLs and databases, handles millions of rows, and offers fast filtering and markdown analysis reports with redaction. This release adds searching through folders and archives, plus performance improvements.
* [**Other-Spring-6497** shared on Reddit](https://www.reddit.com/r/SpringBoot/comments/1y2x60x/we_built_a_15app_desktop_pim_on_spring_boot_4_and/) how their team built [PIM](https://knoden.com/get-pim/), an open-source personal information manager on Spring Boot 4.1, Java 25 and JavaFX 25, with a local AI assistant. Each of its 15 apps is a plain jar loaded before Spring scans and verified by SHA-256 or a signed catalog, with its own Flyway migrations. The same core runs a desktop edition and a multi-user server edition. The [code is on GitLab](https://gitlab.com/knoden).
* **Andrey Naumov** submitted [VisioTheca](https://rembase.org/photo/visiotheca), a free Windows photo organizer built with JavaFX. It grew out of an interest in historical photographs of Kyiv, where a street can sometimes narrow a photo down to a decade even if the exact year is unknown. Collectors can record a date range per photo, group related photos into series, and search their collections by date or tag. The project page has screenshots, a [video manual](https://www.youtube.com/watch?v=z1QnpM2EVoc&t=57s) and a link to the free download on the [Microsoft Store](https://apps.microsoft.com/detail/9n503j7rqwt3).

## Components, Libraries, Tools

* [**john16384** shared on Reddit](https://www.reddit.com/r/JavaFX/comments/1y65vur/fx_flow_071_released_declarative_ui_building_for/) the release of [FX Flow 0.7.1](https://github.com/int4-org/FX/releases/tag/0.7.1), a JavaFX utility library for declarative UI construction and model-driven state, without a full reactive framework. This release adds a Flex row/column layout with an aligned mode for form-like layouts, a single extensible `FX` entry point, editable list-backed domains (including a `ListViewBuilder`) and decorators to wrap nodes, for example in a scroll pane, within the fluent chain.

## Podcasts, Videos, Books

* [**Frank Delporte** published "Interview with Libán Bande González: Going Beyond CSS in JavaFX"](https://foojay.io/today/interview-with-liban-bande-gonzalez-skinning-javafx-applications/) on Foojay. [**Libán Bande González**](https://www.jfx-central.com/people/l.bande-gonzalez) builds business-management desktop apps in Cuba and talks about his book [Skinning JavaFX Applications](https://www.jfx-central.com/books/skinning-javafx-applications), which teaches the Control-Skin architecture to restyle existing controls and create new ones beyond what CSS can do. He also discusses his libraries [Vitra](https://www.jfx-central.com/libraries/vitra), with Fluent-based controls such as a calendar, date range picker, Gantt chart and Kanban board, and [Celestia](https://www.jfx-central.com/libraries/celestia), with micro-interactions and animations for JavaFX nodes.
* [**Anahata TV** published the video "Pure-JavaFX 3D Planetary Engine - Continuous Horizon LOD & 3D Topographic Wireframe Relief"](https://www.youtube.com/watch?v=s2ITSa-RKR8), a real-time demo of the Anahata Universe, a 3D planetary rendering engine built with only JavaFX, featuring quadtree LOD, AWS Terrarium heightmaps, a neon wireframe overlay and 6DOF camera controls.

## Tutorials

* [Advanced Accessibility in JavaFX](https://dev.java/learn/javafx/a11y/advanced/) got an update: A dev.java guide on building fully accessible custom JavaFX components, using a theater seat map as the example. It covers overriding accessibility methods on Node, notifying assistive technology of state changes, and screen reader navigation.

## Miscellaneous

* [**Dirk Lemmermann** shared on Bluesky](https://bsky.app/profile/dlemmermann.bsky.social/post/3n37qtsgnkk2u) a Thanksgiving card made with AnimatorFX and AI, including animations.

## JFX Central

* [**Shai Almog** published "Debug Your Dart in a Java Debugger, and JFX Central on a Phone"](https://www.codenameone.com/blog/debug-dart-in-a-java-debugger/) on the Codename One blog, including a demo video of JFX Central running on an iPhone. He is working on a [merge request](https://github.com/dlsc-software-consulting-gmbh/jfxcentral2/pull/712) to bring the latest version of JFX Central to iOS and Android with a new `codenameone` module, without a rewrite.
* New content on JFX Central:
  * Showcase: [VisioTheca](https://www.jfx-central.com/showcases/visiotheca): A free Windows photo organizer for collectors of historical photographs.
