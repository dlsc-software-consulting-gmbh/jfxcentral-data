I recently had a lot of icons sent to me by a graphics / UX designer. It was my job to add them to 
a JavaFX application. Each icon was shipped in three sizes (16×16, 32×32, 64×64). The naming convention 
for them was like this:

- icon-name.png
- icon-name@2x.png
- icon-name@3x.png

At first I thought that this was some kind of standard naming used by graphic designers for their files 
but in the end it turns out to be a convention that was initially introduced by Apple for their Retina 
displays and adapted by the JavaFX team. The idea is that JavaFX will automatically load the appropriate 
image file for the current screen resolution. So when the application wants to load a 16×16 icon and JavaFX 
determines that the screen is a Retina / HiRes display it will then load the @2x file. The result of this 
are crisp graphics that take full advantage of these beautiful screens.

To enable this automatic image loading one can use CSS or code. In CSS you can define a style like this
(assuming image file is in the same directory as CSS file):

```css
.my-icon {
    -fx-graphic: url("icon-name.png");
}
```

Then you can apply the style to your button and it will show the @2x icon if applicable:
        
```java
button.getStyleClass().add("my-icon");
```

When you want to load images in code you have to make sure not to use input streams but a URL. Example:

```java
Image image =
    new Image(MyApp.class.getResource("icon-name.png").toExternalForm());
```

Using hires icons is really worth your time as your UI will look even more beautiful on HiRes displays.