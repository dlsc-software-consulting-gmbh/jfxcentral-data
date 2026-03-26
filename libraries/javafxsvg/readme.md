## JavaFXSvg

JavaFXSvg adds SVG rendering support to JavaFX by integrating the Batik SVG toolkit as an image loader. Once added to the classpath, SVG files can be loaded just like any other image format using `new Image("file.svg")`, with no code changes required.

The library supports resolution-independent rendering — SVGs are rasterized at the requested size, ensuring sharp images on high-DPI displays. It handles a broad subset of the SVG specification through Batik's mature rendering engine.

JavaFXSvg is the simplest way to add SVG support to a JavaFX application and is particularly useful for icon-heavy applications that want to use scalable vector icons without manual conversion to PNG.
