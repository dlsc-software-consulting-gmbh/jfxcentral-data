Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* Integrated by **Alexander Matveev**:
  * [8331458: Replace qtdemux with MP4 demux based on Media Foundation](https://marc.info/?l=openjdk-openjfx-dev&m=179004515064455&w=2) in OpenJFX: "*Replaced `qtdemux` with a Media Foundation-based MP4 demuxer on Windows. H.264 video from MP4 and fMP4 (HLS) is now decoded using Media Foundation instead of DirectShow. This change avoids several DirectShow issues encountered with the new MP4 demuxer and reduces implementation complexity.*" The changeset also fixes 8305842 (video sometimes does not start when reinitializing on Windows 11) and touches 4731 lines across 37 files. See [PR #2206](https://git.openjdk.org/jfx/pull/2206) and [commit 67f70684](https://git.openjdk.org/jfx/commit/67f70684baa3f6168c9c0183e588c6d294d9f65d).
  *  [A new MP4 demuxer for Windows](https://marc.info/?l=openjdk-openjfx-dev&m=179004515064455&w=2) that uses Media Foundation and replaces `qtdemux`: "*H.264 video from MP4 and fMP4 (HLS) is now decoded using Media Foundation instead of DirectShow.*" The change also fixes video that sometimes did not start when reinitializing on Windows 11. See [PR #2206](https://git.openjdk.org/jfx/pull/2206) and the [commit](https://git.openjdk.org/jfx/commit/67f70684baa3f6168c9c0183e588c6d294d9f65d).
* [**Michael Strauß** integrated a fix for rows disappearing in a TableView with a horizontal scrollbar](https://mail-archive.com/openjfx-dev@openjdk.org/msg29755.html): "*`VirtualFlow` stores the scroll location in two values ... These two values can become inconsistent ... The patch is quite easy: it sets `absoluteOffset` to zero when all cells fit.*" See [PR #2317](https://git.openjdk.org/jfx/pull/2317) and the [commit](https://git.openjdk.org/jfx/commit/7f321a7015e001a2822b5b10e27701503acf0828).

## SceneBuilder

* 

## Applications

* The **JabRef Team** released [JabRef 6.0-beta.1](https://github.com/JabRef/jabref/releases/tag/v6.0-beta.1), the first beta of the next major version of the JavaFX-based BibTeX/BibLaTeX reference manager. Highlights: almost everything is now navigable by keyboard with visual indicators, a fetcher for Software Heritage identifiers (SWHID), the community themes from [themes.jabref.org](https://themes.jabref.org/) (Everforest, Nord, Papers, Chocolate Honey and more) selectable from the preferences with preview images, per-tab icons, and automatic reconnection to shared databases. [Project on GitHub](https://github.com/JabRef/jabref).
* **NURI** demoed [StarLore, a gamified astronomy RPG desktop application](https://www.youtube.com/watch?v=Cpjn4AeTFa0): "*StarLore is a JavaFX-based desktop application that combines astronomy, mythology, exploration, and interactive gaming.*"

## Games

* 

## Components, Libraries, Tools

* **Florian Enner** published the article behind the video we linked [last week](https://www.jfx-central.com/links/2026-09-18): [JavaFX 27 as a GraalVM Native Image on a Raspberry Pi 5](https://ennerf.github.io/2026/09/18/JavaFX-27-Native-Image-on-a-Raspberry-Pi-5.html). It compares Gluon Substrate (still on GraalVM 23 CE and JavaFX 21), BellSoft's Liberica NIK, and his own [StaticFX](https://github.com/HebiRobotics/jfx-static-feature), which "_runs as a regular GraalVM Feature on the stock toolchain_" and keeps all JavaFX modules working, including Media and Web. For the AtlantaFX Sampler on a Raspberry Pi 5, the time to the first window drops from 3.6s with jlink to 0.5s, and memory use after visiting all pages from 1,424 MB to 634 MB, in a binary of about 33 MB. The missing reachability metadata remains the biggest hurdle, "_but IMO the payoff is worth pushing through the initial barrier._"
* **Dirk Lemmermann** released [GemsFX 4.5.0](https://github.com/dlsc-software-consulting-gmbh/GemsFX/releases/tag/v4.5.0): `DialogPane` gains a cancellable dialog feature so dialogs can be closed with the ESC key or a dedicated cancel button, the busy button was removed from `PowerPaneApp` to streamline the dialog options, and `SVGUtil` now uses a default loader context with jsvg updated to 2.2.0. [Project on GitHub](https://github.com/dlsc-software-consulting-gmbh/GemsFX).
* **mkpaz** released [AtlantaFX 3.0.0](https://github.com/mkpaz/atlantafx/releases/tag/v3.0.0), a major update to the JavaFX theme collection: adds window decorations, a Scene Builder plugin, a theme manager, and a new Sidebar component. See the [release and changelog](https://github.com/mkpaz/atlantafx/releases/tag/v3.0.0).

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
