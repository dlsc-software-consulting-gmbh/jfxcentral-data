Integration of [LibRaw](https://www.libraw.org) for JavaFX on Linux, Windows, and macOS. Load raw image formats via JavaFX's `Image` class; 16-bit images are automatically converted to 8-bit.

## Status

Production-ready — tested on all platforms under various threading scenarios. Integrated LibRaw version: 0.20.2.

| OS | Notes |
|----|-------|
| Linux | glibc ≥ 2.27 (Ubuntu 18+) |
| macOS | Up to 10.15; no M1 build |
| Windows | 10 |

**Supported RAW formats:** Adobe DNG, Nikon NEF, Canon CRW/CR2, Sigma X3F, Fuji RAF, Sony, Leica.

## Usage

In your `start()` method, install the image loader:

```java
RAWImageLoaderFactory.install();
```

Add to your JVM config (module name: `org.librawfx`):

```
--add-modules jdk.incubator.foreign
--enable-native-access=org.librawfx
--add-exports=javafx.graphics/com.sun.javafx.iio=org.librawfx
--add-exports=javafx.graphics/com.sun.javafx.iio.common=org.librawfx
```

Load a RAW image like any standard JavaFX image:

```java
Image img = new Image(file.toURI().toURL().toString(), false);
ImageView view = new ImageView(img);
view.setFitWidth(200);
view.setFitHeight(200);
view.setPreserveRatio(true);
```

**Reading metadata:**

```java
HashMap<String, String> metaData = new LibrawImage(file.getAbsolutePath()).getMetaData();
```

The `install()` call is optional — you can also load images directly without registering the handler.