JDKMon is a little tool written in JavaFX that tries to detect all JDKs installed
on your machine. Depending on the operating system it will try to find the JDKs
in the following folders:

- MacOS: `/System/Volumes/Data/Library/Java/JavaVirtualMachines/`
- Windows: `C:\Program Files\Java\`
- Linux: `/usr/lib/jvm`(FXTrayIcon only supports a few linux distros) 

You can modify the locations JDKMon is scanning for installed JDKs by selecting
the `"Add JDK search path"` menu entry. The selected path will be added to the list 
of folders stored in the `jdkmon.properties` file in your user home folder. JDKMon 
will scan all given folders for installed JDKs. 

If you would like to reset the folders that should be scanned to the default, simply
select `Default JDK search path` in the menu.

In case you are a user of JavaFX and work with separate OpenJFX builds, JDKMon can also 
keep track of new JavaFX SDK builds. The procedure is the same as for the JDKs. So you
will find a menu `"Add JavaFX search path"`. And there is the same mechanism to reset
the JavaFX search path to the default one by selecing `"Default JavaFX search path"`. 

JDKMon can not only keep track of your installed OpenJDK distributions but can also
check for known vulnerabilities. Meaning to say it will check if there are know vulnerabilities
found in the NVD based on the version number of your installed OpenJDK distributions.
There is NO guarantee that your installed OpenJDK distribution is vulnerable but it might
be a hint to check if the known vulnerability will affect your installed JDK.
For this you can click on the yellow circle with the exclamation mark that appears behind
a distribution where a vulnerability was found. This means if you have for example Zulu 16.0.1
installed and also Liberica 16.0.1, JDKMon will find the vulnerabilities that are known for
OpenJDK 16.0.1!!! Not for each separate distribution.

In addition to that one can also search for available distributions and download them via
JDKMon by using the `"Download a JDK"` menu entry. This will open a separate window which
will give you the ability to search through available distributions and versions from 
different vendors.

The application will stay in the system tray with an icon. When you click the icon
a menu will appear where you can select

- JDK Main: The main window
- Rescan: Will rescan for installed JDKs and check for updates
- Search path: Will open the directory chooser to select the search path
- Exit: Exit the application

The main window will show you all JDKs found by JDKMon and if there is an
update available it will show you the archive types of the available updates.
In the image below you can see that there is an update for GraalVM available
and that you can download it as a tar.gz file. To download an update just 
click on the archive type tag and choose a folder where the download should be 
stored.