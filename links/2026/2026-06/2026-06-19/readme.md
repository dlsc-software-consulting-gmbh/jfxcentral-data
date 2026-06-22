Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* From the dev mailinglist: [**Michael Strauß** integrated JDK-8385459](https://github.com/openjdk/jfx/commit/738be0f10f8dbbbe182aa3d06ee86313fa181bb1) in [PR #2177](https://github.com/openjdk/jfx/pull/2177): Animations should respect reducedMotion preference: "_Several JavaFX controls use animations to convey state changes, but none respected the reducedMotion accessibility preference. This change makes TableRowSkinBase, TitledPaneSkin, TabPaneSkin, PaginationSkin, and Charts honor Scene.Preferences.reducedMotion._"

## Applications

* [Found on Reddit, by **xdsswar**](https://www.reddit.com/r/JavaFX/comments/1u9hfn1/dropping_a_small_pdf_viewer_for_you_guys_javafx/): "_Dropping a small PDF viewer for you guys (JavaFX + native PDFium). It's done on native PDFium via Java's FFM API (no PDFBox, no AWT). Has zoom, text selection, search, thumbnails, the usual, would love some feedback. [Repo link](https://github.com/xdsswar/ultimate-pdf-viewer)._"
* And [by **RGiskard7**](https://www.reddit.com/r/JavaFX/comments/1u5zjpo/i_built_jylos_a_localfirst_opensource_knowledge/): "_I built Jylos, a local-first open-source knowledge management app using Java and JavaFX. It started as a personal project to explore desktop application architecture, JavaFX, Markdown processing and software design. Over time it evolved into a complete application with: Markdown notes with live preview, Wiki-links and backlinks, Interactive knowledge graph,... Everything is stored locally. No accounts, no cloud backend and no telemetry. The [project is open source](https://github.com/RGiskard7/jylos) under the MIT license and binaries are available for Windows, Linux and macOS. I’d really appreciate any feedback, especially from JavaFX developers._"

## Components, Libraries, Tools

* **Frank Delporte** released [Lottie4J 1.2.4](https://lottie4j.com/releases/index.html): *"Fixed Lottie arc rendering: constrained the easing solver to prevent bezier curve divergence. Added bisection fallback for flat-point curves. Fixed full-circle trim path flickering caused by floating-point precision loss in offset wrapping. Fixed border rendering error. Added Pi4J test file."* Available on [Maven Central](https://central.sonatype.com/search?q=g:com.lottie4j).

## Conferences, Presentations

* ZEISS Meditec hosted the [JFX Adopters Meeting 2026](https://www.zeiss.com/meditec/en/news-events/events/jfx-adopters-meeting-2026.html) on June 16 in Munich. The full-day agenda included: "Low-Latency JavaFX: Robotics and Native Bindings" (**Florian Enner**), "JavaFX – Status and Beyond" (**Wolfgang Weigend**), "JPRO – the Future of Unified JavaFX Application Development across Desktop and Web" (**Florian Kirmaier**), "FXML 2.0: Write Markup, Ship Bytecode" (**Michael Strauß**), "Beyond MVC: A Practical Guide to MVVM in JavaFX" (**Tibor Malanik**), "Diagnosis in the Context of eBike Systems with JavaFX" (**Frido Fechner**), "JavaFX for Electronics Control" (**Thorsten Stüker**), and "Spreadsheet Calculation and Document Processing in JavaFX: Lessons from SCell and bk.text" (**Vasily Smeltsov**). Pictures shared by **Wolfgang Weigend**:
  * "_The [official opening of the JFX Adopters Meeting 2026](https://bsky.app/profile/wolfgangweigend.bsky.social/post/3mofuegyfvc2n) by **Christian Heilmann** and **Lisa**, both work with JavaFX at Zeiss Meditec AG._"
  * ”_The [robotics session](https://bsky.app/profile/wolfgangweigend.bsky.social/post/3mofusejv5k2l) 'Low-Latency JavaFX: Robotics and Native Bindings' by Florian Enner._"
  * "_[Great welcome in the morning](https://bsky.app/profile/wolfgangweigend.bsky.social/post/3mofuzfnzes2l) with **Tom Schindl**, **Stefano Negri** and **Dirk Lemmermann** — all about JavaFX applications_"
* [**Wolfgang Weigend**](https://bsky.app/profile/wolfgangweigend.bsky.social/post/3mocwnoogjc2n): "_Please find the session '[JavaFX UI technology as a central component of the Java ecosystem](https://jugf.github.io/posts/javafx-ui-technologie-als-zentraler-baustein-im-java-okosystem-24-06-2026/)' on Wednesday 24th of June 2026 at the Java User Group Frankfurt am Main in the National Library._"
