Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* Next week Tuesday, March 18th, Java and JavaFX 24 get released!
  * Already want to check the release notes of JavaFX 24? You can find them in [this pull request](https://github.com/openjdk/jfx/pull/1712/files).
  * Please note: "_JavaFX 24 is compiled with `--release 22` and thus requires JDK 22 or later in order to run. If you attempt to run with an older JDK, the Java launcher will exit with an error message indicating that the `javafx.base` module cannot be read._"
* [JavaFX performance tip by **Johan Vos**](https://mastodon.social/@johanvos/114097968879188574): "_In a flow where data is changed, avoid using `Platform.runLater()` until as close as possible to code that does a UI update. The FX App Thread is single-threaded and also needs to render the SG. Do not use it for things that can run on other threads. ... This becomes increasingly important. 10 years ago, my PC had 4 cores. Hence, FX App Thread could use 25% of my resources. Today, I have 20 cores. Hence, only 5% of the CPU power is available to the FX AppThread. Use it wisely!_"
* And a [call for help by **Johan**](https://mastodon.social/@johanvos/114143923702905662): "_One of the reasons I wrote the blog post "[Building OpenJFX using JDK](https://johanvos.wordpress.com/2025/02/27/building-openjfx-using-jdk/)" is to make it easier to create JavaFX SDKs for embedded systems. That is, Java SDKs including JavaFX. First class. Stay tuned. How I wish someone helped us with doing the business for this (JavaFX on embedded). We (Gluon) did this before, had many downloads, but almost no revenue. Spending lots of time to make it even better/faster and more maintainable now. But it takes lots of time and energy. I'm doing this because I believe it is the right thing to do. But sometimes I'm getting tired._"
  * And he [shared a screenshot about this approach](https://bsky.app/profile/johanvos.bsky.social/post/3lk7dbuum722a): "_A small, boring screenshot, but imho an important step. I cross compiled the latest openjdk/jdk with javafx base/graphics/controls mods on Linux x86-64 to Linux aarch64 and ran it on a Raspberry Pi._"

## Applications

* [**JabRef** is excited](https://foojay.social/@jabref/114082584649148423): "_Once again, we get the chance to be part of the outstanding Google Summer of Code program! We are looking forward to some high-quality projects that benefit our large user base. You are interested in Java, JavaFX, and opensource and want to work on a project with a large user base? Check out [our application guide](https://summerofcode.withgoogle.com/programs/2025/organizations/jabref-ev)._"
* [**Carl Dea** shared a video](https://www.linkedin.com/posts/carldea_java-jpro-javafx-ugcPost-7304272653765230592-XJ2q/): "_[Integrated Knowledge Management (IKM)](https://www.ikm.dev/). We created a cross platform installed application for MacOS, Windows, Linux. Now it can run as a Web App using [JPro.one](https://www.jpro.one/)'s technology. It uses Java 23 and JavaFX 23._"


## Games

* A new release of [Randomizer-CS2](https://github.com/Metaphoriker/randomizer-cs2) by **Benjamin Sommerfeld**: "_A JavaFX application that allows you to create custom sequences of random actions to trigger them randomly in Counter-Strike 2. Make your friends in the game jump, shoot, reload, or drop their weapons at unfavorable moments – all without injecting into the game itself._"

## Components, Libraries, Tools

* [Great work by **Hidekazu Kubota**](https://x.com/sosuisen_net/status/1899918084401750466): "_I developed five VSCode extensions to assist Java beginners in creating applications with JavaFX. Additionally, I worked on several Maven archetypes. Drawing on my three years of teaching experience, I aimed to eliminate the common stumbling blocks my students faced._"

## Tutorials

* [**Catherine Edelveis**](https://bsky.app/profile/cat-edelveis.bsky.social) shared a YouTube tutorial: [_Use Scene Builder to Create User Interfaces with Java FX_](https://www.youtube.com/watch?v=PKvuXsfWe_M).
* And another one by [**Cameron McKenzie**](https://bsky.app/profile/cameronmckenzie.com): [_Introduction to JavaFX tutorial for Beginners_](https://www.youtube.com/watch?v=YGciHV_Z65Y).

## JFX Central

* The overview of the JavaFX Links Of The Week of February got [published on Foojay.io](https://foojay.io/today/javafx-links-of-february-2025/).
