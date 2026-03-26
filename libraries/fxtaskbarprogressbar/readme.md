## FXTaskbarProgressBar

FXTaskbarProgressBar is a JavaFX library that enables displaying progress indicators on the Windows taskbar button, just like built-in Windows applications such as Microsoft Edge or Visual Studio. It uses the Windows 7+ Taskbar API via JNA to show determinate, indeterminate, paused, or error states on the application's taskbar button.

The API is straightforward: obtain a `TaskbarProgressBar` for the stage and set the progress value and type. The library handles the native interop transparently and degrades gracefully on non-Windows platforms.

FXTaskbarProgressBar is a polished detail that significantly improves the feel of long-running operations in Windows JavaFX applications.
