Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [Message by **Johan Vos**](https://bsky.app/profile/did:plc:tysr26jaqf3moymuf7jc2uyr/post/3m5ymv2fcw22o) highlighting the fact JavaFX is a full open-source project: "_Keep in mind that the Gluon LTS releases of JavaFX 17u and JavaFX 21u are free to download and use. With Gluon, we do [offer an LTS service](https://gluonhq.com/services/javafx-support/#pricing) but the releases themselves are really free. We don't want to charge people for creating JavaFX applications. No reason to pay unless you need support._"
  * And he is [looking for feedback about a core JavaFX feature](https://bsky.app/profile/johanvos.bsky.social/post/3m5yjlouems2o): "_The JavaFX Properties/listeners approach is really powerful to bind UI components to changing values. But it is often over-used and the main source of performance issues. It's very easy to kill performance by adding a listener to a property that is modified during layout._" What do you think? Did you experience such performance issues? 

## SceneBuilder

* 

## Applications

* [**codedead** announced](https://bsky.app/profile/codedead.com/post/3m5oxetgrec2x) Opal v1.5.1. [Here are the release notes](https://codedead.com/blog/2025/11/15/opal-1.5.1/). "_Opal is a simple app that includes different sound groupings to suit anyone, from office to fantasy. All have volume controls so you can keep faint in the background or bring them forward. You can also set up a delay timer from the settings tab to remind you to take a break. You also have the option to combine multiple sounds at varying volumes to produce the perfect background noise._"
  https://www.youtube.com/watch?v=Mc3fUTxoKIg
* https://webtechie.be/post/2025-11-20-jfxinaction-craig-raw-sparrow-bitcoin-wallet/

## Games

* 

## Components, Libraries, Tools

*

## Podcasts, Videos, Books

* New ["JavaFX In Action" interview published by **Frank Delporte** with **Craig Raw** about the Sparrow Bitcoin Wallet](https://www.youtube.com/watch?v=Mc3fUTxoKIg): "_I don't have any bitcoin myself, but still find the idea of the blockchain and 'public shared money' fascinating. And as it turns out, there is a free and open-source bitcoin wallet, Sparrow, created with JavaFX, that wants to help people understand how the Bitcoin system works and make transactions easy to understand. And while he explains the app itself, we also learn a lot about the Bitcoin ecosystem, reproducible builds, security, hardware wallets, and more!_"

## Conferences, Presentations

*

## Tutorials

* **Troels Mortensen** continues publishing tutorials on YouTube:
  * [ViewManager v3](https://www.youtube.com/watch?v=ylkQyqZxiIg)

## Miscellaneous

* Interesting read: [Solving the Java 24/JavaFX 24 Compatibility Issue: Unsafe Access Flag](https://iifx.dev/en/articles/457273426/solving-the-java-24-javafx-24-compatibility-issue-unsafe-access-flag). "_The warnings you're seeing, especially those related to sun.misc.Unsafe and WARNING: package sun.misc not in java.base, are a direct result of JEP 471 and JEP 498 in the newer Java versions (starting around JDK 23/24). ... Future JavaFX Versions (JavaFX 25 and beyond) are expected to have this internal usage removed or replaced with modern alternatives (like the Foreign Function & Memory API introduced in Java). Until then, using the --sun-misc-unsafe-memory-access=allow flag is the correct way to handle this transition period. You should keep an eye on the JavaFX release notes for updates on when this internal dependency is completely phased out!_"

## JFX Central

* New content on JFX Central:
  * Showcase: [Sparrow Bitcoin Wallet](https://www.jfx-central.com/real_world/sparrow)
  * People: [Craig Raw](https://www.jfx-central.com/people/c.raw)
