# FXTrayIcon

[![Maven Central](https://img.shields.io/maven-central/v/com.dustinredmond.fxtrayicon/FXTrayIcon.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.dustinredmond.fxtrayicon%22%20AND%20a:%22FXTrayIcon%22)
[![Travis CI Build](https://travis-ci.com/dustinkredmond/FXTrayIcon.svg?branch=main)](https://travis-ci.com/dustinkredmond/FXTrayIcon)

Library for use in JavaFX applications that makes adding a System Tray icon easier.
The FXTrayIcon class handles all the messy AWT and Swing parts of constructing an icon, 
displaying notifications, creating a context menu, etc. This means that users of FXTrayIcon can
work solely with its public API and JavaFX classes that they are already familiar with.

Check out the [runnable test application](./src/test/java/com/dustinredmond/fxtrayicon/RunnableTest.java) in the test directory for an example of how this works. 


## Usage

From within your JavaFX application, adding a tray icon is as simple as two lines of code.
Yes, really, that's it!

```java
// Pass in the app's main stage, and path to the icon image
FXTrayIcon icon = new FXTrayIcon(stage, getClass().getResource("someImageFile.png"));
icon.show();
```

## Or use Builder Style
```java
FXTrayIcon icon = new FXTrayIcon.Builder(stage, iconURL).menuItem("Menu 1", e-> myMethod()).addExitItem().show().build();
```

## How do I add to my project 

The project is available as a Maven dependency on Central. Add the following to POM.xml

```xml
<dependency>
  <groupId>com.dustinredmond.fxtrayicon</groupId>
  <artifactId>FXTrayIcon</artifactId>
  <version><!--See Below --></version>
</dependency>
```

Or, if using Gradle to build, add the below to your Gradle build file

```groovy
compile group: 'com.dustinredmond.fxtrayicon', name: 'FXTrayIcon', version: '<see below>'
```

You can even use it from a Groovy script!

```groovy
@Grapes(
  @Grab(group='com.dustinredmond.fxtrayicon', module='FXTrayIcon', version='<see below>')
)
```

*Note, for the current stable version number, use the following:*
[![Maven Central](https://img.shields.io/maven-central/v/com.dustinredmond.fxtrayicon/FXTrayIcon.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.dustinredmond.fxtrayicon%22%20AND%20a:%22FXTrayIcon%22)
