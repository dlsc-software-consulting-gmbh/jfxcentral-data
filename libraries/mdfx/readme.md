# MDFX

MDFX is a simple markdown-renderer for JavaFX. It's based on flexmark-java. It is used to render the documentation for
jpro at jpro.one.

## Usage

### Add the library to your project:

For Gradle, add the following to your `build.gradle`:

Add the following repository:

```
repositories {
    maven {
        url "https://sandec.jfrog.io/artifactory/repo"
    }
}
```

Add The following dependency:

```
dependencies {
    compile "com.sandec:mdfx:0.2.4"
}
```

Usage:

```
import com.sandec.mdfx.MarkdownView;

MarkdownView mdfx = new MarkdownView("your-markdown");
content.getStylesheets().add("/com/sandec/mdfx/mdfx-default.css");
```

Simple Application:
[Source Code](https://github.com/jpro-one/markdown-javafx-renderer/blob/master/example/src/main/java/com/sandec/mdfx/ExampleMDFX.java)

Feature Overview:
[Reference-Markdown-File](https://github.com/jpro-one/markdown-javafx-renderer/blob/master/example/src/main/resources/com/sandec/mdfx/sample.md)

You can personalize the looking of your markdown via css.
[Minimal default-file](https://github.com/jpro-one/markdown-javafx-renderer/blob/master/src/main/resources/com/sandec/mdfx/mdfx-default.css)
Instead of using `/com/sandec/mdfx/mdfx-default.css` you can create your own css-file, to personalize the looking of
your markdown-code.

### Development

Run the sample:

```
./gradlew example:run
./gradlew example:jproRun
```

Deploy new release:

```
./gradlew :publish
```

{"mode":"full","isActive":false}