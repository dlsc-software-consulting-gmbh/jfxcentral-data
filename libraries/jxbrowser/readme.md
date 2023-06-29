# JxBrowser

Add Chromium web browser to your Java app.
Display web pages and PDFs in JavaFX, Swing, SWT.
Work with DOM, JS, Network, Printing, Downloads, etc.

Runs on Windows x86/x64, macOS x64/ARM, Linux x64/ARM, Java 8+

## Usage

You can check out the [Quick Start](https://jxbrowser-support.teamdev.com/docs/quickstart/#quick-start) for more
details,
and the [guides](https://jxbrowser-support.teamdev.com/docs/guides/engine.html) to see more code examples.

Check out [what's different](https://jxbrowser-support.teamdev.com/2021/12/02/jxbrowser-and-javafx-webview.html)
between JavaFX WebView and JxBrowser.

### Add JxBrowser to the Project

Add JxBrowser repository and a couple of dependencies to start working:

```groovy
repositories {
    maven { url = 'https://europe-maven.pkg.dev/jxbrowser/releases' }
}

dependencies {
    // This dependency includes binaries and Chromium  that you can run without having UI.
    implementation "com.teamdev.jxbrowser:jxbrowser-cross-platform:7.21.1"

    // This dependency is only necessary if you have JavaFX UI.  
    implementation "com.teamdev.jxbrowser:jxbrowser-javafx:7.21.1"
}
```

### Integrate JxBrowser into JavaFX Application

```java
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A simple JavaFX application with JxBrowser.
 */
public final class JavaFxSample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize Chromium.
        Engine engine = Engine.newInstance(HARDWARE_ACCELERATED);

        // Create a Browser instance.
        Browser browser = engine.newBrowser();

        // Load the required web page.
        browser.navigation().loadUrl("https://html5test.com");

        // Create and embed JavaFX BrowserView component to display web content.
        BrowserView view = BrowserView.newInstance(browser);

        Scene scene = new Scene(new BorderPane(view), 1280, 800);
        primaryStage.setTitle("JxBrowser JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Shutdown Chromium and release allocated resources.
        primaryStage.setOnCloseRequest(event -> engine.close());
    }
}
```
