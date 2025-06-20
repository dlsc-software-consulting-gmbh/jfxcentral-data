Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* Pull request "Bump minimum JDK version for JavaFX to JDK" mentioned on the [openjfx-dev mailinglist](https://mail.openjdk.org/mailman/listinfo/openjfx-dev): "_In order for JavaFX to be able to use more recent JDK features, we should increase the minimum version of the JDK that can run the latest JavaFX. Additionally, there is an ongoing cost to keeping JavaFX buildable and runnable on older versions of Java, and little reason to continue to do so. This continues our recent practice of setting the minimum JDK for JavaFX N to JDK N-2. JavaFX N is primarily intended for use with JDK N and we also build and test it against JDK N-1 (which is typically what we use as the boot JDK). Anything older than that, including the proposed minimum JDK N-2 (23 in this specific case), is untested. This PR targeted to JavaFX 25, and must not be backported to any earlier version. This needs a CSR and a release note._"

## Applications

* [LogoRRR shared a video](https://www.youtube.com/watch?v=Ir1tmdWEgD4): "_LogoRRR is now available on flathub! This makes it very easy to install and use it - download now!_"

## Components, Libraries, Tools

* [**Konstantin Gerry** announced Gadulka 1.7.0](https://bsky.app/profile/iamkonstantin.eu/post/3lrs2hurgw423): "_Gadulka is a minimalistic audio player library for Kotlin Multiplatform. Gadulka wraps the native player functionality from each target in "headless" mode. That is, the library does not provide any UI (this will be up to you). This version is mostly about updating dependencies like Kotlin 2.1.21, JavaFX and the minimum JDK is now 21. Gadulka links against but doesn't bundle JavaFX._" You can find the [release and sources on GitHub](https://github.com/kkostov/gadulka/releases/tag/1.7.0).

## Podcasts, Videos, Books

* [**Helal Anwar**](https://www.linkedin.com/in/helal-anwar-94571016b/) pointed us at this video by Apple: [WWDC25: Explore Swift and Java interoperability](https://www.youtube.com/watch?v=QSHO-GUGidA). It doesn't mention JavaFX, but it would be nice to see if this can be extended to allow the same kind of integration with JavaFX. The sources of the project can be found [on GitHub](https://github.com/swiftlang/swift-java).
* Scheduled for Jun 23, 2025: Live stream [Quarkus Insights #210: Extension Spotlight on JavaFX](https://www.youtube.com/watch?v=yP-8wXjB_Es): "_**Clément de Tastes** to discuss the JavaFX extension, bringing a modern, efficient, and fully featured toolkit for developing rich client applications in Quarkus._"

## Tutorials

* **Hidekazu Kubota** shared a "_[guide for beginners in Java and JavaFX to help them get started with development using VSCode](https://www.docswell.com/s/sosuisen/56VX4X-2025-06-16-234846). All 100 of my students have successfully learned JavaFX using this guide without any problems._"
* Video tutorial by **TheCodeGenerator**: [Build a Weather App That Looks INSANE in 2025 (JavaFX + REST API)](https://www.youtube.com/watch?v=sXfOEJ4FY84): "_In this full tutorial, we’ll build a real-time weather app using JavaFX, WeatherAPI.com, and modern development tools — from the user interface to the API layer, all wrapped in clean architecture. Whether you're learning JavaFX or just want a project that actually looks good in 2025, this is for you._" Sources [on GitHub](https://github.com/TheCodeGen/weatherlyFX).

