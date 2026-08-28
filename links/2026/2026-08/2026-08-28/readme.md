We took a summerbreak, to this Link Of The Week acutally covers all of August! But as almost everyone seemed to be on vacation, there is not much news to report. We hope you had a great summer and are ready for more JavaFX news in the coming weeks!

Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* From the OpenJFX mailing list: 
  * **Johan Vos** integrated a large batch of third-party dependency updates into the LTS update branches on August 19, including [WebKit 625.1 in jfx17u](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28519.html), [GStreamer 1.28.3](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28537.html), [GLib 2.84.3](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28526.html), [libxml2 2.15.3](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28527.html) and [ICU4C 77.1](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28470.html), landed across the jfx17u, jfx21u and jfx25u branches. This looks like groundwork for the next Critical Patch Update.
  * **Michael Strauß** is running a broad pixel-snapping correctness push. He integrated [8390440: Region.layoutInArea measures content-biased children against an unsnapped value](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28510.html) and followed up with PRs implementing correct snapping for [StackPane](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28560.html), [AnchorPane](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28514.html), [SplitPane](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28513.html) and [BorderPane](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28512.html), plus new [pixel-snapping documentation](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28396.html) that drew review comments from **Nir Lisker**, **John Hendrikx**, **Andy Goryachev** and **Marius Hanl**.
  * **Nir Lisker** integrated [8250802: Refactor StringConverter and its subclasses](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg28480.html), an API cleanup that had been open since 2020, after 14 review rounds.

## Games

* [**Digital Brain** on Bluesky](https://bsky.app/profile/yourdigitalbrain.bsky.social/post/3mrzkqny5jf2t): HMCL, the cross-platform Minecraft launcher that has nearly 9,800 stars on GitHub. "_The tool is built with JavaFX and works on Windows, Linux, macOS, and FreeBSD, in addition to supporting uncommon architectures like ARM, RISC-V, MIPS, and LoongArch, something that most commercial launchers do not offer._" Sources are [on GitHub](https://github.com/HMCL-dev/HMCL).

## Components, Libraries, Tools

* [**mstr_2** on Reddit]([https://www.reddit.com/r/JavaFX/comments/1v7x0qj/java](https://www.reddit.com/r/JavaFX/comments/1vwi754/fxml2_for_javafx/)): "_FXML/2 is a compiled, type-safe, declarative markup language for JavaFX that I've been working on for the past several years. It borrows from classic FXML and adds lots of useful features. Depending on your specific use case, you'll also notice a substantial performance increase: FXML/2 documents don't need to be parsed at runtime (since they are compiled classfiles), which removes the FXMLLoader bottleneck._"
  * [Sources on GitHub](https://github.com/jfxcore/fxml-compiler)
  * [Documentation](https://jfxcore.github.io/fxml-compiler/)
  * [Tutorial: MVVM pattern with FXML/2](https://jfxcore.github.io/fxml-compiler/getting-started/mvvm.html)
* [**Dirk Lemmermann** announced](https://www.reddit.com/r/JavaFX/comments/1vtizzj/flexganttfx_is_now_open_source_the_professional/) that FlexGanttFX, the professional JavaFX Gantt chart framework, is now open source (AGPLv3). "_After more than a decade as a commercial product, I'm happy to announce that FlexGanttFX is now an open source project. FlexGanttFX is a professional Gantt chart and scheduling framework for JavaFX, built by DLSC Software & Consulting. It has been used in aviation, logistics, manufacturing, healthcare, and resource planning applications for years - and it's now available to everyone._"
  * Source code: [github.com/dlsc-software-consulting-gmbh/FlexGanttFX](https://github.com/dlsc-software-consulting-gmbh/FlexGanttFX)
  * Website and documentation: [flexganttfx.com](https://www.flexganttfx.com)
  * Online demo: [FlexGanttFX Showcase on JPro](https://demos.jpro.one/flexganttfx-showcase.html)
* **Dirk** also [created a new showcase application for GemsFX](https://bsky.app/profile/dlemmermann.bsky.social/post/3mtylko7q6s2v). "_You can [download it here](https://www.jdeploy.com/~gemsfxdemo). GemsFX contains 60+ widgets / controls for JavaFX. I picked a new approach in this one. I bundled the documentation PDF for each widget right into the app. To see the widget in action you have to press the play button._"

## Podcasts, Videos, Books

* [**Pedro Duque Vieira** shared a video on LinkedIn](https://www.linkedin.com/feed/update/urn:li:ugcPost:7492911053333385216/): "_Quick update: A fresh look for the windows in IKE (Integrated Knowledge Exchange): the knowledge management windows — the 'cards' that hold concepts, patterns and semantics — have been redesigned. The video shows the previous design first, followed by the new one._"

## Conferences, Presentations

* [**Sven Reimers** will present at Devoxx Belgium](https://bsky.app/profile/sreimers.bsky.social/post/3mszs2zlaks2x): "Java is for Data Science, Too: Building an End-to-End ML Pipeline Without Leaving the JVM. #JavaFX"

## Miscellaneous

* [**Johan Vos** asks for feedback](https://bsky.app/profile/johanvos.bsky.social/post/3msquc3hunc2f): "_Looking for input/feedback from Java developers on 2 different topics: Is there interest in a JavaFX workshop/conference/training in Belgium? Is there interest in a gathering with fellow Java developers working on scientific topics (writing java code, not only using 3th party code)?_"
* [**Geordanys Martinez** is selling](https://www.linkedin.com/posts/xssit_java-softwareengineering-cloudarchitecture-share-7498537339640512512-AQjU/) a "Complete Pure Java 17 PDF Engine (Full IP, Source Code, & Copyright)" "_I have taken this engine to production-grade completion and am selling the entire asset, including full source code ownership, copyrights, and intellectual property distribution rights. I am ready to clear my plate and move on to a brand-new engineering challenge. jux-pdf is a massive, clean-room, file-by-file port of Google Chrome’s native pdfium engine and the FreeType font rasterizer, completely rewritten into pure Java 17._"

## JFX Central

* The content on [Guide for JavaFX Development on Visual Studio Code](https://www.jfx-central.com/learn-javafx/vscode) has been updated by [**Hidekazu Kubota**](https://github.com/sosuisen) for Java 25 and Windows on Arm.
