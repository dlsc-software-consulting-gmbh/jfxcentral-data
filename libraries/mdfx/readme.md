A simple Markdown renderer for JavaFX, based on flexmark-java. Used to render documentation at jpro.one.

## Setup (Gradle)

```groovy
repositories {
    maven { url "https://sandec.jfrog.io/artifactory/repo" }
}

dependencies {
    compile "com.sandec:mdfx:0.2.4"
}
```

## Usage

```java
MarkdownView mdfx = new MarkdownView("your-markdown");
content.getStylesheets().add("/com/sandec/mdfx/mdfx-default.css");
```

Styling is fully customizable via CSS — replace the default stylesheet with your own to control the appearance.

See also: [Example app](https://github.com/jpro-one/markdown-javafx-renderer/blob/master/example/src/main/java/com/sandec/mdfx/ExampleMDFX.java) · [Feature reference](https://github.com/jpro-one/markdown-javafx-renderer/blob/master/example/src/main/resources/com/sandec/mdfx/sample.md) · [Default CSS](https://github.com/jpro-one/markdown-javafx-renderer/blob/master/src/main/resources/com/sandec/mdfx/mdfx-default.css)