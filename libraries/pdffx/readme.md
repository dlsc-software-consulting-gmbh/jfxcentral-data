A custom control for displaying PDF files, built on Apache PDFBox.

## Usage

`PDFView` is a standard JavaFX node. Load a PDF from a file or input stream with a single call:

```java
PDFView pdfView = new PDFView();

// Load from a file
pdfView.load(new File("document.pdf"));

// Load from a stream
pdfView.load(getClass().getResourceAsStream("/document.pdf"));

// Unload the current document
pdfView.unload();

// Bind UI state to document availability
closeItem.disableProperty().bind(Bindings.isNull(pdfView.documentProperty()));
```

Add it to your layout like any other node:

```java
VBox.setVgrow(pdfView, Priority.ALWAYS);
VBox box = new VBox(menuBar, pdfView);
```