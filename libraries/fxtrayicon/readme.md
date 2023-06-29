# FXTrayIcon

Library for use in JavaFX applications that makes adding a System Tray icon easier.
The FXTrayIcon class handles all the messy AWT and Swing parts of constructing an icon,
displaying notifications, creating a context menu, etc. This means that users of FXTrayIcon can
work solely with its public API and JavaFX classes that they are already familiar with.

## Usage

From within your JavaFX application, adding a tray icon is as simple as two lines of code.
Yes, really, that's it!

```java
// Pass in the app's main stage, and path to the icon image
FXTrayIcon icon=new FXTrayIcon(stage,getClass().getResource("someImageFile.png"));
        icon.show();
```

### Builder Style

```java
FXTrayIcon icon=new FXTrayIcon.Builder(stage,iconURL).menuItem("Menu 1",e->myMethod()).addExitItem().show().build();
```