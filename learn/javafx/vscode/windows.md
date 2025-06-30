# Setting up VSCode for JavaFX on Windows

[Back to main page](readme.md)

# Step 1. Check your Java version

First, check if Java is installed on your system.
Search your Windows OS for the **Terminal** app. 
You can do this by clicking the Start menu and typing “Terminal” in the search bar.

Open the "Terminal" (Windows PowerShell) and enter the following command:
```
java -version
```
![Check your Java version](java_version.png)

If an error is displayed, Java is not installed on your PC.
Otherwise, the installed version of Java will be displayed.
In the example in the photo above, openjdk version "21.0.6" is shown.

If the version is less than 21, please uninstall Java.
The minor version numbers (the x.x part of 21.x.x) can be anything.
If Java 21 or higher is already installed, no installation is necessary; proceed to **Step 4**.

# Step 2. Install Scoop

If you are unfamiliar with setting up development environments, we recommend using **Scoop**. [Scoop](https://scoop.sh) is a package manager for Windows that automatically installs the necessary software for development and configures it for use.

To install Scoop, copy the two lines below, paste them into Terminal (PowerShell), and press the Enter key to execute them.

```
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression
```

![Install Scoop](scoop.png)


# Step 3. Install Java (OpenJDK 21)

For those just starting to learn Java, OpenJDK is recommended as it is a free Java Development Kit (JDK).

There are many distributions of OpenJDK. Any distribution will work, but this guide uses Microsoft's distribution.

Installing Java involves running the following three commands in your Terminal.

```
scoop install git
scoop bucket add java
scoop install microsoft21-jdk
```
If you know that **git** is already installed on your PC, you can skip the first `scoop install git` command.

Scoop automatically adds the **JAVA_HOME** value and the **Path** to the Java executables into the "User Environment Variables" of your Windows OS. If you don't use Scoop, you need to add them manually.


# Step 4. Install Maven 3

Next, we will install **Maven**, a build tool for Java.

A build tool manages the libraries needed for development projects and enables automatic building.
In this guide, we will use Maven to set up an environment for developing and running JavaFX in your projects.

If you have already installed Scoop in Step 2, installing Maven is straightforward.
Please run the following two commands in your terminal.
```
scoop install maven
[Environment]::SetEnvironmentVariable("MAVEN_HOME", "$env:USERPROFILE\scoop\apps\maven\current", "User")
```
You don't need to configure anything further.

If you don't use Scoop, you need to download a zip file from the [Maven official site](https://maven.apache.org), extract it somewhere on your PC, and add **MAVEN_HOME** to "User Environment Variables". This process is straightforward for those familiar with development but may be a bit troublesome for beginners.

# Step 5. Install Visual Studio Code

Download and install Visual Studio Code from the official page:

https://code.visualstudio.com

You can also install VS Code using Scoop; however, it is recommended to limit Scoop installations to command-line development tools (such as OpenJDK and Maven) that do not have graphical interfaces. GUI applications like VS Code include built-in automatic update features, which can conflict with Scoop's software update mechanism. For beginners, it is advisable to use standard installers instead of Scoop for applications with graphical interfaces.

# Step 6. Install extensions

Open VSCode and install two extensions for developing Java and JavaFX.

It is recommended not to install any JavaFX plugins that are not included in the packs below, as their functions may overlap.

## Extension Pack for Java (Microsoft)
![Extension Pack for Java](extension_java.png)

## JavaFX Essentials Pack
![JavaFX Essentials Pack](extension_javafx.png)

# Step 7. Install Scene Builder

This is a graphical UI editor.

Download and install Scene Builder from the official page:

https://gluonhq.com/products/scene-builder/#download

That's all for the installation.

[Back to main page](readme.md)