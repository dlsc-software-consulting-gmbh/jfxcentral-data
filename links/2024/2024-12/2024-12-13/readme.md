Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [**Johan Vos** shared](https://mastodon.social/@johanvos/113630372338659041): "_Progress. This is a screenshot from the XCode console, output from an iPhone. HelloWorld.java on iOS using hotspot (zero interpreter mode). Finally back to the point reached by the great **Bob Vandette** many years ago. Need to anchor this._"

## Applications

* [**Patrik Karlström** released CRIC 24.12](https://bsky.app/profile/trixon.se/post/3lcqdymorck2x): "_Custom Runtime Image Creator, a gui for the jlink
command. It produces custom runtime images, kind of Java Runtime Environments. Starting with this release, the snap supports classic confinement. Enjoy!_" You can find the [releases and source on GitHub](https://github.com/trixon/cric/releases/tag/v24.12).
* [**Alon Xiong** is sharing more Datacollie progress on Bluesky](https://bsky.app/profile/xiongchun.bsky.social): "_Today, I finished the function for executing Update | Insert | Delete statements in SQL Console of Datacollie. And now, I have to go out in the sun, I feel like I'm growing mold._"

## Podcasts, Videos, Books

* The third edition of "_The [Definitive Guide to Modern Java Clients with JavaFX](https://www.amazon.com/Definitive-Guide-Modern-Clients-JavaFX/dp/B0DFP9PY1T): Cross-Platform Mobile and Cloud Development Updated for JavaFX 21 and 23_" is now available. 644 pages of JavaFX brought to you by **Stephen Chin**, **Johan Vos**, and **James Weaver**.
* In episode 12 of the "JFX In Action" interviews with **Steve Hannah**, you'll learn about jDeploy: "_Building a JavaFX app is easy and fun, but how do you efficiently distribute it to different systems? jpackage and GraalVM can help, but jDeploy makes things even more effortless by handling all the packaging and providing an upgrading flow!_" The [video is available on YouTube](https://www.youtube.com/watch?v=Lhmf9U0KYsg) with more info [in this blog post](https://webtechie.be/post/2024-12-12-jfxinaction-steve-hannah-jdeploy/).

## Tutorials

* [**polypragmatist** shared](https://bsky.app/profile/polypragmatist.bsky.social/post/3lcxlt4izw226): "_I've just published what I believe to be the most comprehensive guide to styling the JavaFX TableView available. [This guide includes some tutorials](https://www.pragmaticcoding.ca/javafx/elements/styling-guide-tableView) plus a complete (I think) catalogue of TableView CSS selectors._"

## Miscellaneous

* A tip by **Johan Vos**: "_I believe `Scene.addPreLayoutPulseListener` is a less-known but very powerful API in JavaFX. I use it to fix performance issues in apps using ListView, where the backingList is sometimes updated very often. Sending individual ListChange events to the control kills performance, so I sync once per pulse. Interestingly, the [JBS issue, JDK-8097917](https://bugs.openjdk.org/browse/JDK-8097917), for this was created by **Stuart Marks** who is my #1 reference for (amongst other things) Java List performance!_"()
* [**René Gielen** created a small demo](https://bsky.app/profile/rgielen.bsky.social/post/3lcnbxv7wp222): "_Stripping Spring Boot + JavaFX to the core. Feedback appreciated!_" You can [find it on GitHub](https://github.com/rgielen/springboot-javafx-fxweaver-demo).
* A [pro tip by **Dirk Lemmermann**](https://bsky.app/profile/dlemmermann.bsky.social/post/3lcxfgznlbs2q): "_Make [ScenicView](https://www.jfx-central.com/tools/scenicview) an integral part of your application. Define a keyboard shortcut to bring it up whenever you want, e.g. CTRL+SHIFT+S -> ScenicView.launch(scene). So helpful!_" Thanks to [**Jonathan Giles**](https://www.jfx-central.com/people/j.giles).
* An [article in the JVM Advent by **Cay Horstmann**](https://www.javaadvent.com/2024/12/java-in-the-small.html) that also mentions the JavaFX application JTaccuino: "_Java in the Small_".

## JFX Central

* New content:
  * Tools: [jDeploy, distribute your JavaFX app as a native bundle](https://www.jfx-central.com/tools/jdeploy)
  * People: [Steve Hannah, creator of jDeploy](https://www.jfx-central.com/people/s.hannah)
  * Video: [JavaFX In Action with Steve Hannah about jDeploy](https://www.jfx-central.com/videos/Lhmf9U0KYsg)
