## Core

* This week Java 22 and JavaFX 22 got released! Here are the [release notes of JavaFX 22](https://github.com/openjdk/jfx/blob/master/doc-files/release-notes-22.md), including:
  * An important change for animations ([JDK-8324658](https://bugs.openjdk.org/browse/JDK-8324658)): the Animation methods play, start, stop, and pause may now be called on any thread.
  * 8 enhancements
  * 80 fixed bugs
  * 3 security fixes
* You can download JavaFX 22 here:
  * As a separate SDK from the [Gluon website](https://gluonhq.com/products/javafx/).
  * Or included with a Java JDK from, e.g., the [Azul website](https://www.azul.com/downloads/?version=java-22-sts&package=jdk-fx#zulu).
* Release highlights of JavaFX 22 can [be found here](https://openjfx.io/highlights/22/).
  * [**Abhinay Agarwal** highlights this one](https://twitter.com/iAbhinay/status/1770347967536419186): "This release includes 'Platform preferences API' which allows developers to style their apps in accordance with the appearance of the OS."
* **Kevin Rushforth** published a [description of JavaFX Incubator Modules](https://github.com/kevinrushforth/jfx/blob/javafx.incubator/INCUBATOR-MODULES.md).
  * [**Pedro Duque Vieira** explains](https://twitter.com/P_Duke/status/1770840921753502170): "These new modules will exist in the JavaFX SDK. They'll be the home of features that are still under review, to possibly later be included as final stable features. One such feature will likely be the Rich Text Area."

## Applications

* JabRef immediately [bumped to version 22](https://foojay.social/@jabref/112124226121637837): "We just updated to the newest version of JavaFX 22, and it works fine so far! Great to see so many bugs fixed!"
* **Carl Dea** shared "a [glimpse of a responsive layout for a landing page](https://twitter.com/carldea/status/1768673053213347911). Clinical interface terminology system (knowledge base)."

## Games

* One of the students of **Almas Baim** built a [dungeon layout generator for FXGL](https://twitter.com/AlmasBaim/status/1770767408896110958): "Once the API is finalised, it will be available from the next release."
  * He also shared a [video of a rotating cube with rotating cubes with rotating cubes with...](https://twitter.com/AlmasBaim/status/1768709121136599320)
  * And [another one video](https://twitter.com/AlmasBaim/status/1770842307169857714): "In the next release of FXGL the pathfinding API treats all grid based data structures in the same way. This means mazes, dungeons, maps, levels, including custom types, are all easily traversable by entities using pathfinding."

## Components, Libraries, Tools

* **Dirk Lemmermann** has been very productive this week...
  * Added a [few more features to the DrawerStackPane in GemsFX](https://twitter.com/dlemmermann/status/1770757809698697560): "You can now configure the animation duration and the top and side paddings."
  * Added [field validation via ValidatorFX to the dialog framework in GemsFX](https://twitter.com/dlemmermann/status/1770415222815092963): "Will be part of next release."
  * Added a ['PowerPane' to GemsFX](https://twitter.com/dlemmermann/status/1770136937044541910): "... the mother of all panes ... combining a glass pane, a drawer pane, hidden panes, dialog pane, info center (notification) pane. Basically something that will give you a great quick-start when creating a new app in JavaFX." Check the thread on Twitter for more screenshots.
  * "Implemented my own ['Friday Fun Component'](https://twitter.com/dlemmermann/status/1768640804820488349) now that [**Gerrit Grunwald**](https://twitter.com/hansolo_) is mostly on [CRaC](https://docs.azul.com/core/crac/crac-introduction) :-) We needed an 'energy efficiency' display for our energy software at [Senapt](https://twitter.com/SenaptEaaS)."
* And and update by **Pedro Duque Vieira**: "Work in progress (continued - very close to finished): [Navigation Control](https://twitter.com/P_Duke/status/1770112311300083931). When shrunk, show popup menus when clicking items - scroll bar shows when items exceed space. And animations when shrinking and expanding."

## Tutorials

* **Frank Delporte** wrote a blog post, including two "Code Walk-Trough" videos: "[Search in Documentation with a JavaFX ChatGPT-like LangChain4j Application](https://webtechie.be/post/2024-03-18-search-documentation-javafx-chat-langchain4j/)".

## Miscellaneous

* [**Sergey** has a question about mobile JavaFX development](https://twitter.com/SwiftVideoBlog/status/1770307570911031724): "I am very happy to see JavaFX being actively developed and supported. I wonder though how many developers use it for Mobile app development? Is there any recent data available to see the adoption of this technology?"
