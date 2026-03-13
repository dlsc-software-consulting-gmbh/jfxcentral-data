# LibHeifFX

Integration of [Libheif](https://github.com/strukturag/libheif) for JavaFX on Linux, Windows, and macOS. Load any HEIF format via JavaFX's `Image` class and manipulate via `PixelWriter`/`PixelReader`. 16-bit images are automatically converted to 8-bit.

**Requires JDK 17** (uses Foreign Linker API / Project Panama).

## Status

Beta — tested on macOS under various threading scenarios.

| OS | Notes |
|----|-------|
| Linux | glibc ≥ 2.27 (Ubuntu 18+) |
| macOS | Up to 10.15; no M1 build |
| Windows | 10/11 |

**Known limitations:** no image resizing before decode, no metadata extraction, no AVIF support.

## Usage

**Maven:**
```xml
<dependency>
    <groupId>org.libheiffx</groupId>
    <artifactId>LibHeifFX</artifactId>
    <version>1.0.0</version>
</dependency>
```

In your `start()` method, install the image loader:

```java
HEIFImageLoaderFactory.install();
```

Add to your JVM config (module name: `org.libheiffx`):

```text
--add-modules jdk.incubator.foreign
--enable-native-access=org.libheiffx
--add-exports=javafx.graphics/com.sun.javafx.iio=org.libheiffx
--add-exports=javafx.graphics/com.sun.javafx.iio.common=org.libheiffx
```

Load a HEIF image like any standard JavaFX image:

```java
Image img = new Image(file.toURI().toURL().toString(), false);
ImageView view = new ImageView(img);
view.setFitWidth(200);
view.setFitHeight(200);
view.setPreserveRatio(true);
```

The `install()` call is optional — you can also load images directly without registering the handler (see `TestApp.java`).
