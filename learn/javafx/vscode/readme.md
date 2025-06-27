# Guide for JavaFX Development on Visual Studio Code

This document serves as a development guide for beginners in Java and JavaFX using Visual Studio Code (VSCode).

There are still relatively few guides available for JavaFX development using VSCode because it previously lacked a well-established development environment for JavaFX. In 2025, extensions for JavaFX were added and organized, making it one of the most accessible IDEs for beginners to start with JavaFX. 

This document contains the following content:

1. How to set up a VSCode environment for JavaFX
2. How to create your first JavaFX project

# Setting up the VSCode environment for JavaFX

This guide will install the following software:

- OpenJDK 21
- Maven 3
- Visual Studio Code and the necessary extensions
- Scene Builder

Installation methods differ depending on the operating system:

- [Windows](windows.md)
- [macOS or Linux](macos_linux.md)

## About versions

This guide assumes Java 21 and JavaFX 23. This is a relatively new environment as of spring 2025. 

Java 25 and JavaFX 25 will be released in fall 2025. You can continue to use Java 21 and JavaFX 23 even after fall. If you choose to use Java 25 and JavaFX 25, you will need slightly different procedures from those outlined in this document.


# Create your first JavaFX project

To create a JavaFX project, use a Maven Archetype.

## 1. Start generating

Type the command below in your terminal:
```
mvn archetype:generate
```
![type mvn archetype:generate](archetype01.png)

## 2. Select jfx-sss-fxml archetype

Since many options will be shown, type `jfx-sss-fxml` and press the Enter key to narrow the list.

![narrow candidates](archetype02.png)

Enter 1 to choose jfx-sss-fxml.

![jfx-sss-fxml](archetype02b.png)

## 3. Select Archetype Version

Press the Enter key to select the latest version.

![Select the latest](archetype03.png)

## 4. About your project

The following are the details of your project. 

### 4.1 groupId

groupId is the reverse domain name notation for your organization. For a simple practice app, you can use `com.example` as groupId.

![groupId](archetype04.png)

### 4.2 artifactId

The artifactId represents your project's name.

Allowed characters include:

- lowercase letters
- numbers
- hyphens (-)

![articactId](archetype05.png)

### 4.3 version and package

For the version and package, you can simply press the Enter key to select the default value.

![version and package](archetype06.png)

### 4.4 Confirmation

Press the Enter key.

![Confirmation](archetype07.png)

## 5 Project created

A folder with the same name as the artifactId will be created. 

Launch VSCode and open this folder. 

![VSCode](vscode.png)

You can also open the folder from your terminal by typing: 
```
code path-to-your-project-folder
```

![Success](success.png)

`code` is a terminal command to launch VSCode.

## 6. Launch Scene Builder

Right-click the main.fxml file and select "Open in Scene Builder" to begin developing with Scene Builder.

This is a graphical UI editor.

![Launch Scene Builder](launch_scenebuilder.png)

## 7. Launch your app

![Launch](launch.png)

Select `Launcher.java` and click Run or Debug to launch your app.

As a result, a blank window appears.

## 8. Build executable

To create an executable (.exe, .dmg), open a terminal in VSCode and run the following command:

```
mvn clean package
```
The generated executables are located under `target\jpackage\`.


## 9. About archetypes

![Archetypes](archetypes.png)

These archetypes do not utilize Java's module system (JPMS). While many JavaFX samples adopt JPMS, it may be too complex for beginners looking to create desktop applications with JavaFX.

If you want to explore an architecture similar to MVC, the javafx-sss-fxml-mvc archetype will be a helpful reference.

# Conclusion

This is just the starting point. 

If you use libraries for development, you need to search in Maven Central (https://central.sonatype.com) and add the dependency to your pom.xml.

If you want to customize the build, you will need to learn more about Maven.

Enjoy your journey with JavaFX!
