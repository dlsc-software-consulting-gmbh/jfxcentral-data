## Java and Maven

To be able to execute Java and JavaFX applications, you need a Java runtime. There are many distributions available, and in this tutorials we will use a build of OpenJDK (the [source project where Java](https://github.com/openjdk/jdk) is developed) that has OpenJFX (the [source project where JavaFX](https://github.com/openjdk/jfx/) is developed) bundled inside it.

### Windows

* Download and run the [OpenJDK installer from Azul](https://www.azul.com/downloads/?version=java-21-lts&os=windows&package=jdk-fx#zulu).
* Download the latest version from the [Apache Maven website](https://maven.apache.org/download.cgi), select the "Binary zip archive". 
  * Unzip the download to the folder of your choice. 
  * Add `M2_HOME` and `MAVEN_HOME` variables to the Windows environment using system properties and point them the directory where you unzipped Maven.
  * Update the PATH variable by appending the Maven bin folder `%M2_HOME%\bin`.

### Linux and macOS

The preferred way to manage Java runtimes on Linux and macOS is [SDKMAN!](https://sdkman.io/) which helps you to install various systems, including Java.

```bash
# Install SDKMAN!
$ curl -s "https://get.sdkman.io" | bash

# Close the terminal and open a new one, or run the following command
$ source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install an OpenJDK runtime which has OpenJFX included
$ sdk install java 21.0.1.fx-zulu  

# Install Maven
$ sdk install maven
```

### Validate Java and Maven installation

In a terminal, check the output of the following commands which should return similar output, depending on your system:

```bash
$ java -version
openjdk version "21.0.1" 2023-10-17 LTS
OpenJDK Runtime Environment Zulu21.30+15-CA (build 21.0.1+12-LTS)
OpenJDK 64-Bit Server VM Zulu21.30+15-CA (build 21.0.1+12-LTS, mixed mode, sharing)

$ mvn -version
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: /Users/user/.sdkman/candidates/maven/current
Java version: 21.0.1, vendor: Azul Systems, Inc., runtime: /Users/user/.sdkman/candidates/java/21.0.1.fx-zulu/zulu-21.jdk/Contents/Home
Default locale: en_BE, platform encoding: UTF-8
OS name: "mac os x", version: "14.2", arch: "aarch64", family: "mac"
```

## Integrated Development Environment

The easiest way to work on a Java project, is using an Integrated Development Environment (IDE). And you have many options, with most being free!

Let's take a look at how you can create a JavaFX project with some of these IDEs.

### IntelliJIDEA

This [IDE by JetBrains](https://www.jetbrains.com/idea/) is available as a free Community and paid Ultimate edition. It's the most popular IDE amongst Java developers.

Creating a new JavaFX project has never been easier with the project wizard in Jetbrains IntelliJ IDEA. Creating a new project is just a matter of clicking a few buttons, and you have your new user interface application up and running in a matter of minutes...!

![alt](youtube:v9rAR3waDJs)

### Visual Studio Code

This [free IDE by Microsoft](https://code.visualstudio.com/) is a universal IDE that can be used for many programming languages and other use-cases. By using extensions, you can tailor it to your specific needs. For Java development, you should at least install this extension:

* "Language Support for Java" by Red Hat

Now start Visual Studio Code, open a terminal and execute the following command

```bash
# Copy this line
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple -DarchetypeVersion=0.0.3 -DgroupId=com.yourname -DartifactId=yourproject -Dversion=1.0.0 -Djavafx-version=21

# Explanation
mvn archetype:generate \ 
        -DarchetypeGroupId=org.openjfx \
        -DarchetypeArtifactId=javafx-archetype-simple \
        -DarchetypeVersion=0.0.3 \
        -DgroupId=com.yourname \
        -DartifactId=yourproject \
        -Dversion=1.0.0 \
        -Djavafx-version=21
```

Open the generated directory, open the file `App.java` and click on `Run` on top of the `main` method. 