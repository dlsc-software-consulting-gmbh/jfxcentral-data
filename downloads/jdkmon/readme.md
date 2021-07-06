JDKMon is a little tool written in JavaFX that tries to detect all JDKs installed
on your machine. Depending on the operating system it will try to find the JDKs
in the following folders:

MacOS: `/System/Volumes/Data/Library/Java/JavaVirtualMachines/`

Windows: `C:\Program Files\Java\`

Linux: `/usr/lib/jvm`(FXTrayIcon only supports a few linux distros) 

You can change the folder JDKMon is scanning for installed JDKs by selecting
the `"Add search path"` menu entry. The selected path will be added to the list of 
folders stored in the jdkmon.properties file in your user home folder.
JDKMon will scan all given folders for installed JDKs. 

If you would like to reset the folders that should be scanned to the default, simply
select `"Default search path"` in the menu.

The application will stay in the system tray with an icon. If you click the icon
a menu will appear where you can select


`JDK Main: The main window`

`Rescan: Will rescan for installed JDKs and check for updates`

`Search path: Will open the directory chooser to select the search path`

`Exit: Exit the application`
`

The main window will show you all JDKs found by JDKMon and if there is an
update available it will show you the archive types of the available updates.
In the image below you can see that there is an update for GraalVM available
and that you can download it as a tar.gz file.
To download an update just click on the archive type tag and choose a folder
where the download should be stored.

How it looks on MacOS and Linux:
![Screenshot](https://github.com/HanSolo/jfxcentral-data/raw/JDKMon/downloads/jdkmon/banner.jpg)


How it looks on Windows:

![Screenshot](https://github.com/HanSolo/jfxcentral-data/raw/JDKMon/downloads/jdkmon/screenshot_win.png)