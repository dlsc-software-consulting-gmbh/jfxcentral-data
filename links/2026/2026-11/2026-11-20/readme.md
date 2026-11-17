Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [JavaFX 28 Early-Access Build 17 published by **OpenJFX / Oracle**](https://jdk.java.net/javafx28/): standalone SDKs and jmods for Linux, macOS (x86_64 and AArch64) and Windows, for testing against early-access JDK 28 builds.
* Integrations from the mailing list:
  * [8394318: Weird characters appear in TextArea with surrogates](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30962.html): the fix for garbled characters in `TextArea` when surrogate pairs are involved has been integrated into OpenJFX.
  * [8395867: Update ICU4C to 79.1](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30984.html): OpenJFX updated its bundled ICU4C text-handling library to version 79.1. The completed change was announced on the openjfx-dev list at https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30991.html.
  * [8396583: WebSocket throws UnsatisfiedLinkError on Linux](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg30990.html): A fix for the `UnsatisfiedLinkError` thrown by WebSocket on Linux has been integrated. The [backport to jfx27u](https://www.mail-archive.com/openjfx-dev@openjdk.org/msg31016.html) has been integrated too.

## SceneBuilder

* 

## Applications

* 

## Games

* 

## Components, Libraries, Tools

*

## Podcasts, Videos, Books

*

## Conferences, Presentations

*

## Tutorials

* [**Fuzzy-System8568** shared on Reddit](https://www.reddit.com/r/JavaFX/comments/1xx1u3k/javafx_packaging_a_developers_guide_demo_guide/) "JavaFX Packaging: A Developer's Guide", a demo and guide repository that teaches how to package JavaFX applications via Maven. It is a default HelloApplication project with a `pom.xml` set up to build an OS-specific, self-contained distributable that is independent of the installed JDK/JRE. Several markdown documents explain the workflow and each step, such as the plugins and the `pom.xml` properties. The author developed it on CachyOS and is looking for contributors with details on other distros and operating systems, such as the WiX toolset requirements for Windows.

## Miscellaneous

*

## JFX Central

* 
