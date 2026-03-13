# FXGL

JavaFX Game Development Framework. No installation or setup required — works out of the box on Java 8-15, Win/Mac/Linux/Android 8+/iOS 11.0+/Web. Games package into a single executable `.jar` or native image.

Key features: Entity-Component system, interpolated animations, particles, superset of JavaFX UI API. See the [full feature list](https://github.com/AlmasB/FXGL/wiki/Core-Features).

Good for: 2D games (platformer/arcade/RPG), complex UI applications, experimental 3D, hobby/academic/commercial projects, and fast prototyping.

![promo](https://raw.githubusercontent.com/AlmasB/git-server/master/storage/images/fxgl_promo.jpg)

## Getting Started

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

Resources: [Showcase Trailer](https://youtu.be/fuDQg7W0v4g) · [Wiki & tutorials](https://github.com/AlmasB/FXGL/wiki) · [YouTube tutorials](https://www.youtube.com/playlist?list=PL4h6ypqTi3RTiTuAQFKE6xwflnPKyFuPp) · [Basic examples](https://github.com/AlmasB/FXGL/tree/dev/fxgl-samples/src/main/java/basics) · [FXGL games with source](https://github.com/AlmasB/FXGLGames) · [Published demos](https://fxgl.itch.io/)

## Community

Tutorials: [Space Ranger](https://www.journaldev.com/40219/space-rangers-game-java-fxgl) · [Geometry Wars](https://webtechie.be/post/2020-05-07-getting-started-with-fxgl/) · [Mazela-Man](https://dykstrom.github.io/mazela-man-web/home/)

Projects: [SOFTKNK.IO](https://github.com/softknk/softknk.io) · [Consume](https://ergoscrit.itch.io/consume) · [FXGL Sliding Puzzle](https://github.com/beryx/fxgl-sliding-puzzle)