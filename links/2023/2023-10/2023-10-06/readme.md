## Core

* Right after last weeks' LOTW, [**Johan Vos** already had an update](https://mastodon.social/@johanvos/111147510047965780): "JavaFX 22-ea+11 has just been released to maven central (and on [gluonhq.com/products/javafx](https://gluonhq.com/products/javafx)). This contains the much anticipated memory improvements :)"
  * "This contains a major change in some CSS processing, see [this commit by **John Hendrikx**](https://github.com/openjdk/jfx/commit/5e145cc06ef68c50a4ffc95574fdafd44e054100), that leads to improved performance (less GC). Please test and evaluate."
* **Kevin Rushfort** and [**Johan Vos**](https://mastodon.social/@johanvos) had a BOF at Devoxx in Antwerp. 
  * During this BOF, some of the new features that could be included in JavaFX 22 were presented:
    * Platform APIs
    * (unlikely) CSS theming
    * InputMap/Behavior
  * Feedback was asked from the users regarding blocking issues in JavaFX itself to be able to build more amazing stuff:
    * Removal of remaining AWT dependencies.
    * Full integration with the desktop (alerts, dark/light,...).
    * Missing 3D point and line drawing methods.

## SceneBuilder

* Version 21 is [now available](https://github.com/gluonhq/scenebuilder/releases/tag/21.0.0)!
  * Refactored: Clean up DocumentWatchingController 
  * Uses JDK and JavaFX 21 
  * Contributors:
      * Abhinay Agarwal
      * Almas Baim
      * José Pereda

## Applications

* At Devoxx we got blown away by the demos given by Florian Enner to visualize robot arms and interact with them. He wasn't a speaker at the conference, but we sure hope he will be next year or on any other conference. Make sure to check out of few of his videos...
  * [JavaFX w/ GraalVM native image (Windows)](https://www.youtube.com/watch?v=XxVoG1ft7w8): a desktop application with charts and controls, but a robot simulated in 3D.
  * [JavaFX: migrating to AtlantaFX themes](https://www.youtube.com/watch?v=vjl5tz8bE90):  migrating from a custom JavaFX design to AtlantaFX themes. And even more 3D robots and charts!!!
  * [JavaFX 3D: Dynamic CubeWorld](https://www.youtube.com/watch?v=Xac03kLqKrA): 3D cubes, a loooooot of cubes...
  * And these are [all his YouTube videos](https://www.youtube.com/@florianenner7435/videos?view=0&sort=dd&shelf_id=0).
* [The Hero app by **Pedro Duque Vieira** has an alert/error system where the button will show as filled whenever there's an error/alert](https://twitter.com/p_duke/status/1707409002026463457): "This immediately warns u in a subtle way that u may have things to fix. After you've fixed everything the alert button shows up empty."
* [RNArtist has been updated](https://github.com/fjossinet/RNArtist/tags), mainly to run on Windows. Releases 1.0.8 and 1.0.9 are available (just restart RNArtist). 1.0.9 will just increase the maximum memory to be used (needed for Windows).
* [**Clemens Lanthaler** release V1.3.3 of Photoslide](https://github.com/lanthale/PhotoSlide/releases/tag/v1.3.3), a simple photo management application with a modern and reactive user interface. This version brings updates to JavaFX 21/JDK21 and fixes to the filter module: "Thanks to FXGL examples the filters are now realtime and therefore I can now start implementing more filters and the edit module".

## Components, Libraries, Tools

* [A new library announcement by **Pedro Duque Vieira**, FXThemes](https://pixelduke.com/2023/10/02/fxthemes-java-javafx-library-released/): "It is a Java library that contains classes to help in advanced JavaFX theme development. Right now, it contains helper classes to change the appearance of a native window frame as well as the backdrop of JavaFX native Windows." The announcement page also contains more info about the reason of providing this functionality in a new library.
* [GemsFX 1.82.0 by **Dirk Lemmermann**](https://twitter.com/dlemmermann/status/1707043072956113368) with:
  * Early access version of a TreeNodeView with many configuration options (added by [**LeeWyatt**](https://twitter.com/LeeWyatt_7788))
  * Gives you controls / pickers for: choosing a date, choosing a date range, choosing a month, choosing a year, choosing a time, choosing a duration.
  * See the [DateRangePicker control in action here](https://www.youtube.com/watch?v=n7HesjJZ7K4).
  
## Podcast, Video, Books

* Presentations at Devoxx this week in Antwerpen, Belgium:
  * **Kevin Rushforth**: 
    * [Building and Deploying Java Client Desktop Applications With JDK 21 and Beyond](https://www.youtube.com/watch?v=Afehjldx4yM) 
    * [JavaFX Notebook](https://www.youtube.com/watch?v=R9yhbaN5Xxs)
  * **Johan Vos**:
    * [Quantum Computing in Java: an exceptionential opportunity](https://www.youtube.com/watch?v=eylmTHUGcks)
  * [**Sean Phillips**](https://jvm.social/@Birdasaur): 
    * [Explainable AI Analysis Visualization: Applications from Brain Computer Interfaces to ChatGPT](https://www.youtube.com/watch?v=LYtZRWo4t4E).
    * In between sessions, he [updated the dev branch for of his XAI tool Trinity](https://twitter.com/SeanMiPhillips/status/1709184231916573012) to support audio file processing, playback and spectrum analysis in 3D.
  * [**Cédric Champeau**](https://mastodon.xyz/@melix): 
    * [JSol'Ex : solar image processing written in Java](https://www.youtube.com/watch?v=j6KMOXhldEs).
  * **Paul and Gail Anderson**: 
    * [Say the Words: Modern Java with JavaFX and GraalVM for Rich Client UIs](https://www.youtube.com/watch?v=3nT8vurpmqc).

## JFX Central

* New libraries: 
  * [Transit Theme](https://www.jfx-central.com/libraries/transit): builds upon the lessons and my work with JMetro.
  * [FXThemes](https://www.jfx-central.com/libraries/fxthemes): classes to help in advanced theme development.
* The summary with all the JFX Central links of September got [published on Foojay.io](https://foojay.io/today/javafx-links-of-september-2023/).
