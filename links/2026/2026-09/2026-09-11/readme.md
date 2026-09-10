Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [**Jose Pereda** integrated 8391779: JVM crashes after copy key event on focused WebView that has no selection](http://www.mail-archive.com/openjfx-dev@openjdk.org/msg29287.html): "*This PR adds a guard to WebKit/EditorJava.cpp, to prevent passing an empty selection to the pasteboard, that would cause an immediate JVM termination, when firing a copy key event over a focused WebView that has no selection.*" Reviewed and integrated on 8 September. ([PR on GitHub](https://git.openjdk.org/jfx/pull/2241))
* Release cycle note: the cutoff for backports into **JavaFX 27.0.1** was 7 September, and [jfx27u is now open for 27.0.2 fixes](http://www.mail-archive.com/openjfx-dev@openjdk.org/msg28928.html) — announced by **Kevin Rushforth**, version bump [integrated by **Ambarish Rapte**](http://www.mail-archive.com/openjfx-dev@openjdk.org/msg29269.html) on 8 September.

## SceneBuilder

* 

## Applications

* [**Helal Anwar** shared a video of Passport Studio FX](https://www.linkedin.com/posts/helal-anwar-94571016b_java-javafx-opencv-ugcPost-7502853372006432768-VXi5/), a desktop application to simplify passport and ID photo preparation for local photo studios and print shops. It provides AI-powered background removal with MODNet and ONNX Runtime, manual cropping with passport-size aspect ratios, custom background colors, zoom controls, standard photo size presets, and A4 print-sheet generation with cutting borders. The tech stack is Java, JavaFX, FXML, OpenCV, ONNX Runtime, MODNet, Apache PDFBox, Maven, and jpackage for the Windows build. All images are processed locally, so nothing gets uploaded to the cloud.
* [**LooseCartographer989** announced NumeHub on Reddit](https://www.reddit.com/r/JavaFX/comments/1wal3zv/4_years_developing_im_proud_to_announce_numehub/), claiming it is *"the largest JavaFX application ever built"*: "*Four years ago I opened a JavaFX project to see how far the platform would go before it broke. It never broke. So I kept going.*" NumeHub is a single desktop app that bundles projects, CRM, a point-of-sale system, WhatsApp and email campaigns, a file explorer, a browser and system monitoring, with an AI agent (built on MCP tools) on top that answers questions by voice or text. "*Millions of nodes. Under half a gig of RAM. A new release every week.*" Runs on Windows and macOS (Apple Silicon and Intel) in English, Portuguese, Chinese and German; Linux is planned if there is demand. It's not open source and [downloads are here](https://nume.solutions/download). The thread turned into a fun "who has the biggest JavaFX app" contest, with **PartOfTheBotnet** (9 years, 5,440 commits), **_DystopianSnowman** (13 years, ~90k LOC) and **Siedlerchr** weighing in for JabRef.

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

*

## JFX Central

* 
