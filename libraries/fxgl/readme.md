# FXGL

JavaFX Game Development Framework

* No installation or setup required
* "Out of the box": Java 8-15, Win/Mac/Linux/Android 8+/iOS 11.0+/Web
* Simple and clean API, higher level than other engines
* Superset of JavaFX: no need to learn new UI API
* Real-world game development techniques: Entity-Component, interpolated animations, particles,
  and [many more](https://github.com/AlmasB/FXGL/wiki/Core-Features)
* Games are easily packaged into a single executable .jar or native images

![promo](https://raw.githubusercontent.com/AlmasB/git-server/master/storage/images/fxgl_promo.jpg)

## Good for:

* Any 2D game (side-scroller / platformer / arcade / RPG)
* Any business application with complex UI controls / animations
* Experimental 3D
* Hobby / academic / commercial projects
* Teaching / learning / improving game development skills
* Fast prototyping of app ideas

## Getting Started

For a quick dive into code, see
standalone [basic examples](https://github.com/AlmasB/FXGL/tree/dev/fxgl-samples/src/main/java/basics).
Otherwise, see:

* [Showcase Trailer](https://youtu.be/fuDQg7W0v4g)
* [Wiki & written tutorials](https://github.com/AlmasB/FXGL/wiki)
* [YouTube tutorials](https://www.youtube.com/playlist?list=PL4h6ypqTi3RTiTuAQFKE6xwflnPKyFuPp)
* [Sample code demos](https://github.com/AlmasB/FXGL/fxgl-samples)
* [FXGL games](https://github.com/AlmasB/FXGLGames) (with source)
* [Published demos](https://fxgl.itch.io/) on itch.io

### Minimal Example

```java
public class BasicGameApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Basic Game App");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### Community Tutorials

- [Space Ranger](https://www.journaldev.com/40219/space-rangers-game-java-fxgl) at journaldev
- [Geometry Wars](https://webtechie.be/post/2020-05-07-getting-started-with-fxgl/) at webtechie
- [Mazela-Man](https://dykstrom.github.io/mazela-man-web/home/) by dykstrom

Community projects (identified using `fxgl` topic):

- [SOFTKNK.IO](https://github.com/softknk/softknk.io)
- [Consume](https://ergoscrit.itch.io/consume)
- [FXGL Sliding Puzzle](https://github.com/beryx/fxgl-sliding-puzzle)