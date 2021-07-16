
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
Instead of using `/com/sandec/mdfx/mdfx-default.css` you can create your own css-file, to personalize the looking of your markdown-code.

## Changelog

### 0.2.4
* Fixed an layouting issue with lists. In some situations "..." was shown for left label counting the entries.
### 0.2.3
* The blockquote is now configured with the css variables `-mdfx-bq-color-border` and `-mdfx-bq-color-border`. Users might want to add these variables to their css.
* Ordered lists start now with 1 instead of 0.
### 0.2.2
skip this version
### 0.2.1
* Added support for BlockQuotes
* Added support for TaskLists
* Renamed MDFXNode to MarkdownView
* Moved internal classes to an impl package
### 0.2.0
* Added support for ordered lists.
* We now us the Java11 Module System. At least Java11 is now required.
### 0.1.9
* Added support for strikethrough
* It's now possible to change the link color in the css with `-mdfx-link-color`
* Updated flexmark to 0.62.2
### 0.1.8
* The property mdString of MDFXNode is now public

## Development
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