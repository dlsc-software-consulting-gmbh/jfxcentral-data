## JavaFX WebView Debugger

JavaFX WebView Debugger enables full Google Chrome DevTools debugging for content running inside a JavaFX WebView. By bridging the WebView's internal JavaScript debugger protocol to Chrome's remote debugging protocol, it allows developers to inspect DOM elements, debug JavaScript, monitor network requests, and profile performance using the familiar Chrome DevTools interface.

Setting it up involves starting a debug server from Java code and connecting a Chrome browser to the provided URL. From that point, the full DevTools inspector is available for the WebView content, including breakpoints, the console, and the element inspector.

This library is invaluable when building hybrid JavaFX/web applications, Markdown editors, documentation viewers, or any application that embeds significant web content in a JavaFX WebView.
