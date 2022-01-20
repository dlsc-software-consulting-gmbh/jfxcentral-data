JavaFX 17.0.2 has been released today.

## Release Notes for JavaFX 17.0.2

### Introduction

The following notes describe important changes and information about this release. In some cases, the descriptions provide links to additional detailed information about an issue or a change.

As of JDK 11 the JavaFX modules are delivered separately from the JDK. These release notes cover the standalone JavaFX 17.0.2 release. As such, they complement the [JavaFX 17 Release Notes](https://github.com/openjdk/jfx/blob/jfx17/doc-files/release-notes-17.md).

JavaFX 17.0.2 requires JDK 11 or later.

### List of Fixed Bugs

Issue key|Summary|Subcomponent
---------|-------|------------
[JDK-8274022](https://bugs.openjdk.java.net/browse/JDK-8274022)|Additional Memory Leak in ControlAcceleratorSupport|controls
[JDK-8274854](https://bugs.openjdk.java.net/browse/JDK-8274854)|Mnemonics for menu containing numeric text not working|controls
[JDK-8276490](https://bugs.openjdk.java.net/browse/JDK-8276490)|Incorrect path for duplicate x and y values, when path falls outside axis bound|graphics
[JDK-8275138](https://bugs.openjdk.java.net/browse/JDK-8275138)|WebView: UserAgent string is empty for first request|web
[JDK-8276847](https://bugs.openjdk.java.net/browse/JDK-8276847)|JSException: ReferenceError: Can't find variable: IntersectionObserver|web
[JDK-8277133](https://bugs.openjdk.java.net/browse/JDK-8277133)|Dragboard contents retrieved all over again during a DND process on WebView|web
[JDK-8160597](https://bugs.openjdk.java.net/browse/JDK-8160597)|IllegalArgumentException when we initiate drag on Image|window-toolkit
[JDK-8274929](https://bugs.openjdk.java.net/browse/JDK-8274929)|Crash while reading specific clipboard content|window-toolkit
[JDK-8275723](https://bugs.openjdk.java.net/browse/JDK-8275723)|Crash on macOS 12 in GlassRunnable::dealloc|window-toolkit

### List of Security fixes

Issue key|Summary|Subcomponent
---------|-------|------------
JDK-8272546 (not public) | Better TrueType font loading | graphics