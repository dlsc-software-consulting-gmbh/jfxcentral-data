# LibRawFX

Integration of the [LibRaw](https://www.libraw.org) library for JavaFX for all major operating systems (Linux, Windows,
OSX). All raw formats can be loaded with the Image class and manipulated by Pixelwriter/Pixelreader. Limitation is that
the image class only supports 8-bit color deph but converts all 16bit image format to 8bit automatically.

## Status

The lib is in production ready status. That means it is tested on all operating systems (OSX, Linux, Win10) and under
different threading scenarios.

Supported OS:

- Linux (min. glibc 2.27 which means Ubuntu 18 or later)
- OSX up to 10.15 (actually no M1 build)
- Windows 10

Integrated LibRaw version is 0.20.2 (https://www.libraw.org/download#changelog)

Only the following raw formats are enabled (see class `RAWDescriptor.java`):

- Adobe DNG
- Nikon NEF
- Canon CRW/CR2
- SIMGA Merrill/Quattro X3F
- Fuji X-Trans RAF
- Sony
- Leica

The lib now resizes the image before sending it to memory (the same as the Javafx is doing for PNG/JPG).

## Usage

- One of the first lines needed is the following code to install the file handler (in the class where the _start_ method
  is).

`RAWImageLoaderFactory.install();`

- and add the following lines to your java config:

```
--add-modules jdk.incubator.foreign --enable-native-access=org.librawfx  
--add-exports=javafx.graphics/com.sun.javafx.iio=org.librawfx 
--add-exports=javafx.graphics/com.sun.javafx.iio.common=org.librawfx
```

- **Metadata**
  Just create an instance of class LibrawImage with the file to get the metadata and print the return values

```
HashMap<String, String> metaData = new LibrawImage(initialFile.getAbsolutePath()).getMetaData();
VBox vb = new VBox();
metaData.entrySet().forEach((entry) -> {
   Label l = new Label(entry.getKey() + " " + entry.getValue());
   vb.getChildren().add(l);
});
```

- **Module name: org.librawfx**

Just create an Image with the URL/stream and add it to the image view:

```
  Image img=new Image(initialFile.toURI().toURL().toString(), false);  
  ImageView view = new ImageView();  
  view.setFitHeight(200);  
  view.setFitWidth(200);  
  view.setPreserveRatio(true);  
  stack.getChildren().add(view);  
  view.setImage(img);
```  

You can also use the lib without adding the file handler. This means you can also forget the "...install" line and just
load a file URL with the lib.