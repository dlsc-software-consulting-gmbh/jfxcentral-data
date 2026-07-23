Sheetmusic4J is an open-source Java library that parses MusicXML and MIDI files into Java objects and renders them as native JavaFX music sheet views: no WebView, no browser engine, no JavaScript bridge. Further information: [sheetmusic4j.com](https://sheetmusic4j.com).

## Rendering a Score with JavaFX

Add the Maven dependency:

```xml
<dependency>
    <groupId>com.sheetmusic4j</groupId>
    <artifactId>fxviewer</artifactId>
    <version>${sheetmusic4j.version}</version>
</dependency>
```

Minimal code to load and display a score:

```java
Score score = ScoreFile.load(Path.of("song.musicxml"));

SheetView sheetView = new SheetView();
sheetView.setScore(score);

stage.setScene(new Scene(new ScrollPane(sheetView), 900, 600));
stage.show();
```

## Module Structure

| Module | Description |
|--------|-------------|
| `core` | Domain model for `Score`, `Part`, `Measure`, `Note` and more; reads and writes MusicXML and MIDI files |
| `engraving` | Framework-agnostic layout engine that positions staves, measures, clefs, and notes |
| `fxviewer` | JavaFX `SheetView` and `StripSheetView` components to render and play along with a score; depends on `core` + `engraving` |
| `fxdemo` | Demo app: loads MusicXML/MIDI files and shows the rendered score alongside an inspection pane; depends on `core` + `engraving` + `fxviewer` |
