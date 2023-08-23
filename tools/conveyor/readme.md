# Conveyor

[Conveyor](https://www.hydraulic.software) is a replacement for the `jpackage` tool which makes packaging and updating apps as easy as it would be for a web app. It adds the following useful features:

* **Online updates.** `jpackage` creates packages that users must update manually by reinstalling. Conveyor generates packages that can silently update themselves in the background on Windows/macOS, or which use apt on Linux.
* **Cross-building.** Creation, signing and notarization of packages for all supported targets from any OS (e.g. your developer laptop, a CI machine). The built-in tasks must be run from each OS you target.
* **Self-signed packages.** These don't require you to purchase signing certificates, but require the user to copy/paste terminal commands.
* **Download pages.** Generated static HTML that detects the user's platform ([example](https://downloads.hydraulic.dev/atlantafx/sampler/download.html)).
* **Icon conversion.** PNG images are converted to native formats automatically.
* **Size optimization.** `jdeps` is used to shrink the download by stripping unused JDK modules.
* **Happy IT departments.** Conveyor uses MSIX, Microsoft's current-gen Windows 10/11 packaging system, which is deeply integrated with Windows network admin tools.
* **CLI support.** Add supplementary command line tools included with your package.
* **Commercial support.**

The OpenJFX Gradle plugin is fully supported, and configuration can be read out of your build system.

Packaging and releasing an app can be as simple as running a single command and uploading the resulting directory to GitHub Releases.

The [documentation website](https://conveyor.hydraulic.dev) provides an example based on AtlantaFX, an open source theme for JavaFX that implements a modern design language using the GitHub Primer color system. The [sampler app](https://conveyor.hydraulic.dev/9.2/sample-apps/#jvm-atlantafx-sampler) provides a gallery of the available controls and stylings.