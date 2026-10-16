Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [**John Hendrikx**](https://www.linkedin.com/in/john-hendrikx-0baa8819/) integrated [JDK-8290310: ChangeListener events are incorrect](https://marc.info/?l=openjdk-openjfx-dev&m=179205301698593&w=2) into OpenJFX. The related review thread ([RFR: 8290310](https://marc.info/?l=openjdk-openjfx-dev&m=179207446523885&w=2)) ran to over 50 messages.
  * Remarkable comment by **mstr2** in the [pull request](https://github.com/openjdk/jfx/pull/1081): "_Through every release and across time, this PR has always stood firm. Like a solid rock in a sea of change. Contributors came and went. JDKs grew old. But this PR remained. At some point, we stopped thinking of it as pending work, and started seeing it as a precious landmark. And now, an era has come to an end. We had prepared for another review round, but we had not prepared for closure._"

## Applications

* Following up on last week's [internal JavaFX tool for automated test recording of temporary power equipment](https://bsky.app/profile/afparks.bsky.social/post/3mxcv2dkork2h), **@afparks** shared [two new videos](https://bsky.app/profile/afparks.bsky.social/post/3mxp74qb5422m). The latest shows the new users and roles pages, with a permissions table that has sticky headers staying in place while scrolling, and an overlapping scroll bar that floats over the rows instead of taking up space on the right.

## Components, Libraries, Tools

* **Frank Delporte** announced a new version of [Lottie4J](https://lottie4j.com/), the Java and JavaFX library to render Lottie animations. "_Release 1.2.7 doesn’t bring new features, but focuses fully on performance. Improvements: image assets are decoded once, and optimized track mattes._" Read more [on this blog post](https://webtechie.be/post/lottie4j-1.2.7-a-performance-release/).
* [**Mohd Dilshad Khan** shared a tip on switching themes while a Java app is running](https://lnkd.in/p/ejR5-GFK), without a restart or losing state. In JavaFX it comes down to replacing the stylesheet on the scene with `scene.getStylesheets().setAll(...)`, so themes live in CSS files a designer can tweak. For Swing, FlatLaf offers the same with `FlatDarkLaf.setup()` and `FlatLaf.updateUI()`. You can find his [project on GitHub](https://github.com/mohddilshadkhan2003/theme-hot-swap).
