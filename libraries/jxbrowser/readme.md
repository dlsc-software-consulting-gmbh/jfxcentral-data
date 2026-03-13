# JxBrowser

Embed Chromium in Java apps — display web pages, PDFs, and work with DOM, JS, networking, printing, and downloads. Supports JavaFX, Swing, and SWT.

Runs on Windows x86/x64, macOS x64/ARM, Linux x64/ARM, Java 8+.

See the [Quick Start](https://jxbrowser-support.teamdev.com/docs/quickstart/#quick-start), [guides](https://jxbrowser-support.teamdev.com/docs/guides/engine.html), and [comparison with JavaFX WebView](https://jxbrowser-support.teamdev.com/2021/12/02/jxbrowser-and-javafx-webview.html).

## Setup

```groovy
repositories {
    maven { url = 'https://europe-maven.pkg.dev/jxbrowser/releases' }
}

dependencies {
    implementation "com.teamdev.jxbrowser:jxbrowser-cross-platform:7.21.1"
    implementation "com.teamdev.jxbrowser:jxbrowser-javafx:7.21.1"  // JavaFX UI only
}
```

## Usage

```java
@Override
public void start(Stage primaryStage) {
    Engine engine = Engine.newInstance(HARDWARE_ACCELERATED);
    Browser browser = engine.newBrowser();
    browser.navigation().loadUrl("https://html5test.com");

    BrowserView view = BrowserView.newInstance(browser);
    Scene scene = new Scene(new BorderPane(view), 1280, 800);
    primaryStage.setTitle("JxBrowser JavaFX");
    primaryStage.setScene(scene);
    primaryStage.show();

    primaryStage.setOnCloseRequest(event -> engine.close());
}
```
