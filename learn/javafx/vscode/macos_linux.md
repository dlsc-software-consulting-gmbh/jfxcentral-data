# Setting up VSCode for JavaFX on macOS or Linux

[Back to main page](readme.md)

# Step 1. Check your Java version

First, check if you have Java installed.

Open a terminal and enter the following command:
```
java -version
```

If an error is displayed, Java is not installed on your PC.
Otherwise, the installed version of Java will be displayed.

If the version is less than 21, please uninstall Java.
The minor version numbers (the x.x part of 21.x.x) can be anything.
If Java 21 or higher is already installed, no installation is necessary; proceed to **Step 4**.

# Step 2. Install SDKMAN!

[SDKMAN!](https://sdkman.io/) is a command-line tool for managing versions of Java-related SDKs and tools.

To install SDKMAN!, copy the line below, paste it into the terminal, and press the Enter key to execute it.

```bash
curl -s "https://get.sdkman.io" | bash
```
After that, close the terminal and open a new one to enable SDKMAN!

# Step 3. Install Java (OpenJDK 21)

For those who are just starting to learn Java, OpenJDK is recommended as it is a free Java Development Kit (JDK).

There are many distributions of OpenJDK. Any distribution will work, but this guide uses the official OpenJDK distribution.

To install Java, type the following command in your terminal:

```bash
sdk install java 21.0.2-open
```

# Step 4. Install Maven 3

Next, we will install **Maven**, a build tool for Java.

A build tool manages the libraries needed for development projects and enables automatic building.
In this guide, we will use Maven to set up an environment for developing and running JavaFX in your projects.

If you have already installed SDKMAN! in Step 2, installing Maven is straightforward.
Type the following command in your terminal:
```bash
sdk install maven
```
You don't need to configure anything further.

If you don't use SDKMAN!, the installation method differs for each OS. If you are a macOS user, you can use Homebrew:
```bash
brew install maven
```
SDKMAN! automatically sets the valid JAVA_HOME and MAVEN_HOME environment variables. If you don't use SDKMAN!, you need to set them yourself.


# Step 5. Install Visual Studio Code

Download and install Visual Studio Code from the official page:

https://code.visualstudio.com

# Step 6. Install extensions

Open VSCode and install two extensions for developing Java and JavaFX.

It is recommended not to install any JavaFX plugins that are not included in the packs below, as their functions may overlap.

## Extension Pack for Java (Microsoft)
![Extension Pack for Java](extension_java.png)

## JavaFX Essentials Pack
![JavaFX Essentials Pack](extension_javafx.png)

## Note: VSCode-compatible IDEs (such as VSCodium)

You might need to use a compatible IDE instead of VSCode on your OS. In that case, you will need to install the vscjava extension pack instead of the one from Microsoft.

**Extension Pack for Java (vscjava)**

![Extension Pack for Java (vscjava)](vscodium.jpg)


# Step 7. Install Scene Builder

This is a graphical UI editor.

Download and install Scene Builder from the official page:

https://gluonhq.com/products/scene-builder/#download

That's all for the installation.

[Back to main page](readme.md)