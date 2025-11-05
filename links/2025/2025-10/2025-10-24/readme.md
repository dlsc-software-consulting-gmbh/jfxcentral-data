Did we miss anything? Is there anything you want to have included in one of the next overviews? Let us know via links@jfx-central.com.

## Core

* [Message from Gluon](https://bsky.app/profile/gluonhq.com/post/3m3puhsuhrk2a): "_The CPU releases for JavaFX are available. [Download the latest JavaFX SDKs with security fixes](https://gluonhq.com/products/javafx/) or get them from Maven Central. You'll find the latest released, JavaFX 25.0.1 and the Gluon JavaFX LTS releases 17.0.17 and 21.0.9._"
* Is the "OpenSource Model" broken for OpenJFX (and all other projects)? And are those who are making money from it not interested in fixing it? [Interesting discussion on Bluesky](https://bsky.app/profile/johanvos.bsky.social/post/3m3mejmxq5k2p)...

## Applications

* [Message by **Mirko Sertic**](https://bsky.app/profile/mirkosertic.de/post/3m3sgoql3k22o): "_I found some time to update JavaFX DesktopSearch to the latest Java 25, Lucene and#Tika releases. Maybe I will also add some LLM or MCP features. We'll find out. [Checkout on GitHub](https://github.com/mirkosertic/FXDesktopSearch) for more to come :-)_"

## Games

* No JavaFX, but still very impressive Java-based game development: Nostr Game Engine on [GitHub](https://github.com/NostrGameEngine/ngengine) and [here is the website](https://ngengine.org/). "_Based on jMonkeyEngine. A game engine and framework for building games and applications integrated with the Nostr ecosystem and p2p networking._"

## Components, Libraries, Tools

* [Message by Dirk Lemmermann](https://bsky.app/profile/dlemmermann.bsky.social/post/3m3mrf4f2ps2u): "_Chasing memory leaks in my JavaFX based application is a breeze when using [JMemoryBuddy](https://github.com/Sandec/JMemoryBuddy) from [**Florian Kirmaier**](https://bsky.app/profile/did:plc:mszvyrtwuphvznao54kwrqat) I add it to my prod code, not just test classes. At any time I can see whether UI views got garbage collected or not. When I see that a view did to get garbage collected I launch [VisualVM](https://visualvm.github.io/), search for the JMemoryBuddyLive instance (in the heapdump) with the uncollected view, click on the 'referent' and open the 'GC Root' view. That tells me what is still holding a reference to the view._"
* And [**Dirk** shared screenshots](https://bsky.app/profile/dlemmermann.bsky.social/post/3m3mk7r2usk2z): "_I have added an updated and improved version of the SegmentedBar control to GemsFX. Will be released today in version 3.6.0. This is a real-world example of the SegmentedBar control being used. Our app is using it as part of the 'debt collection' user interface. It shows how many bills haven't been paid, how many have started the collection process, etc..._"
  * [Followed by screenshots of version 3.6.1](https://bsky.app/profile/dlemmermann.bsky.social/post/3m3ufrkafyc27): "_I have added a new 'StretchingTilePane' container so that I can finally create a nice responsive tile-based layout for the module selection view of our CRM solution. The default JavaFX TilePane does not fill the available width. It also came in handy for the launch pad section of our "market data portal". Each card is a "tile" and depending on available width we want to either have two or three of them in a row._"

## Miscellaneous

* [**Gerrit Grunwald** is using JavaFX as an animation tool](https://bsky.app/profile/hansolo.eu/post/3m3jqu2uans27): "_Was looking for a tool to animate text to create some stuff for my PixelMug ... well the easiest thing to so was creating the animations with JavaFX , export as png‘s and create an animated gif…done 😁 I ❤️ it._"
