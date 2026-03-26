## LiveDirsFX

LiveDirsFX is a JavaFX library that provides a directory tree model backed by live filesystem watching. It presents one or more root directories as an observable tree of `Path` entries that automatically updates when files and directories are created, modified, renamed, or deleted on disk.

The tree model can be directly used as the data source for a `TreeView`, with all updates applied on the JavaFX Application Thread for safe UI binding. It uses Java's `WatchService` API for efficient, low-overhead filesystem monitoring.

LiveDirsFX is ideal for file explorer components, project browsers, and any application that needs to display and react to a live view of the filesystem inside a JavaFX tree control.
