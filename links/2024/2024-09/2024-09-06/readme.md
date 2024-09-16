## Core

* [**Pedro Duke** shared the following](https://x.com/P_Duke/status/1830644652287926277): "Noteworthy features coming in the next release of JavaFX (23) coming this month (September 17):"
  * CSS transitions: This introduces basic animation support in CSS. Won't be able to do it on Background and Borders for now. Work is already underway to also support that in a next release.
  * Support "@3x" and greater high-density image naming convention. Currently JavaFX supports `img.png` and `img@2x.png`, soon also `img@3x.png`.
  * Horizontal scroll support with the keyboard on controls like ListView, TreeView
  * TextTruncated property to know when text is being truncated
  * Add support for EXT-X-MEDIA tag in HTTP Live Streaming
* And [**Pedro** also highlights](https://x.com/P_Duke/status/1831373478806667774) a new "Public Focus Traversal API for JavaFX" proposal:
  * [Draft description on GitHub](https://github.com/andy-goryachev-oracle/Test/blob/main/doc/FocusTraversal/FocusTraversal.md).
  * And the [pull request in the OpenJDK JFX repository](https://github.com/openjdk/jfx/pull/1555).
    
## Applications

*  [PDFsam announced version 5.2.5 of PDFsam Basic](https://x.com/PDFsamOSS/status/1826188220570226707): "Most notably, you can use the keyword 'last' in the page selection of the extract pages tool, allowing you to extract the last page from multiple PDF documents." You can find the [Release Notes here](https://t.co/R3sf3sVdRh).

## Components, Libraries, Tools

* [**Johan Vos** shared on Mastodon](https://mastodon.social/@johanvos/113085258956232434): "We're getting closer to a new version of Gluon Substrate, enabling Java 23 and JavaFX 23 on mobile (ios/android). Also, more focus on creating static libs  (containing compiled versions of your Java code) that can be plugged in new/existing ios/android projects. Since we don't have devrel/marketing, the website updates are way behind what we do. But we'll update it this time. Keep an eye on [gluonhq.com](https://gluonhq.com)."
* Updates by [**Pedro Duke**](https://x.com/P_Duke):
  * [FXComponents version is 1.6.2 released](https://x.com/P_Duke/status/1828096262228648294): "Includes tweaks to NavigationPane."
  * Work in progress on the next release of Transit Theme: "[Created a new sampler app](https://x.com/P_Duke/status/1819356682457137601) (using FXComponents NavigationPane), and changed Button appearance along with extra Button styles. New LIGHT and DARK styles for [ToggleButton and Checkbox](https://x.com/P_Duke/status/1829148999825404359) are added. Same for [RadioButton and ComboBox](https://x.com/P_Duke/status/1830998729123311908)."

## Podcasts, Videos, Books

* [Video by **Sean Phillips**](https://www.youtube.com/watch?v=ccvhOEXtqJ4): "JSON RPC control of JavaFX visualization from Python/Jupyter. In this example a simple Python script, runnable from either CLI or Jupyter notebook uses httpx to post JSON formatted data (225 mbs of AI feature vectors) and commands to a receiving JavaFX application called Trinity."

## Miscellaneous

* [**Rumble Tumble Kid** shared a GitHub project](https://x.com/rumbletumblekid/status/1829538211846357065): "Here's a small template I created a while ago that shows you how to either package your Scala GUI application using jlink and jpackage or compile it ahead-of-time via Graal Native": [package-scalafx](https://github.com/RumbleTumbleKid/package-scalafx). 
* Check [this thread by **Sankalp**](https://twitter.com/Sankalp0704/status/1831374170195947685). He found an old book and is comparing old Java that didn’t have resource files or design-time layout tools, versus current FXML and SceneBuilder.
* [**Tim Pote** spent the weekend working with JavaFX](https://twitter.com/potetm/status/1830966979332620442): "I gotta say having access to the JVM and real threads while writing a UI is very, very nice. Big shoutout to [@v1aaad](https://twitter.com/v1aaad) for his work on [cljfx](https://github.com/cljfx/cljfx)!". 
  * cljfx = "Declarative, functional, and extensible wrapper of JavaFX inspired by better parts of react and re-frame."

## JFX Central

* The Links of August got also [published on Foojay.io](https://foojay.io/today/javafx-links-of-august-2024/).
* [**Dirk Lemmermann** "is having way too much fun styling the intro page for a freshly installed JFX Central
    mobile app :-)"](https://x.com/dlemmermann/status/1831367446131044553).
  * With a video preview [in this tweet](https://x.com/dlemmermann/status/1831649178440630603).
