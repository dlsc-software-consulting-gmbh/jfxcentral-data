Java libraries to handle and play [Lottie](https://lottiefiles.com/what-is-lottie) files — a JSON-based animation format created by Airbnb, now the industry standard for vector animations on mobile and web. Further information: [lottie4j.com](https://lottie4j.com).

## Showing a Lottie Animation in JavaFX

Add the Maven dependency:

```xml
<dependency>
    <groupId>com.lottie4j</groupId>
    <artifactId>fxplayer</artifactId>
    <version>${lottie4j.version}</version>
</dependency>
```

Minimal code to display an animation:

```java
File lottieFile = new File("animation.json");
Animation animation = LottieFileLoader.load(lottieFile);

stage.setScene(new Scene(new LottiePlayer(animation), 1200, 800));
stage.show();

animation.play();
```

## Module Structure

| Module | Description |
|--------|-------------|
| `core` | Lottie data model; reads and writes Lottie JSON files |
| `fxplayer` | JavaFX component to play Lottie animations; depends on `core` |
| `fxfileviewer` | Demo app: shows animation and visualizes file structure; depends on `core` + `fxplayer` |
