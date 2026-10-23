Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* **Johan Vos** [announced](https://mastodon.social/@johanvos/117474447449778114) the CPU releases JavaFX 17.0.21, 21.0.13, 25.0.5 and 27.0.1. All builds are available for free, with [commercial support available from Gluon](https://gluonhq.com/products/javafx). "_This release is packed with security fixes that I had to inspect line by line. To me, it was the hardest one ever, but I'm happy with the result._"
* [**OpenJFX team** integrated RichTextArea image import support](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30549.html): JDK-8388276 lands the ability to import images into the `RichTextArea` control.
* [**OpenJFX team** fixed stale TableView cells after refresh()](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30556.html): JDK-8394617 resolves a bug where `TableView.refresh()` left stale cells in the VirtualFlow pile, showing old content when scrolled back into view.
* [OpenJFX integrates D3D9 lighting cache optimization (JDK-8392736)](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30595.html): A change was integrated into the OpenJFX mainline that caches lights instead of reuploading them to the GPU on every call, improving Direct3D9 rendering performance.

## Applications

* **Frank Delporte** [released MelodyMatrix 2.0.0](https://webtechie.be/post/melodymatrix-2.0.0-learn-section-fly-over-3d-sheet-music-and-audio-to-midi/) with a new Learn section with lesson cards tracking level, duration and progress, a Fly-Over view showing the sheet music in 3D with notes highlighted during playback, and a Convert feature that turns mp3, wav and other audio files into MIDI locally.
* **AsharPeshimam** [published a Java PDF app on the Microsoft Store](https://www.reddit.com/r/JavaFX/comments/1xahf70/i_just_published_my_java_pdf_app_on_the_microsoft/), shared the story on r/JavaFX, and asks for feedback.
* [Klunk, a JavaFX log viewing application](https://www.reddit.com/r/JavaFX/comments/1x9emky/i_made_klunk_a_javafx_log_viewing_application/) was shared on r/JavaFX by **XenFive**. "_It’s currently beta, but I use it daily without issues and figured others might find it useful too. Happy to hear feedback, bug reports, or feature ideas._"

## Components, Libraries, Tools

* **Frank Delporte** announced version [0.0.4](https://sheetmusic4j.com/status/2026/20261016-release-0.0.4/) and [0.0.5](https://sheetmusic4j.com/status/2026/20261021-release-0.0.5/) of Sheetmusic4J, the library to parse and render sheet music natively in Java/JavaFX. These releases include new Node-based and 3D fly-over views, and add support for the LilyPond format: "_load such a file and render it natively in JavaFX, without running LilyPond._" It also brings engraving improvements like better tuplet placement and octave clef support. A video with a demo of the new views is [available on YouTube](https://www.youtube.com/watch?v=8n7WONyX0yk). 
