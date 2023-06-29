# AnimateFX

AnimateFX is a library of ready-to-use animations for JavaFX.

## Features

- Custom animations
- Custom interpolators
- Play/Stop animation
- Play an animation after another
- More to come

## Animations

- Bounce
- Flash
- Pulse
- RubberBand
- Shake
- Swing
- Tada
- Wobble
- Jello
- Bounce (In, InDown, InLeft, InRight, InUp, Out, OutDown, ...)
- Fade (In, Out, InDown, InDownBig, InLeftBig, ....)
- Flip (InX, InY, OutX, OutY)
- LightSpeed (In, Out)
- Rotate (In, InDownLeft, ...)
- Slide (InUp, InDown, InLeft, InRight, OutUp, OutDown, ...)
- Zoom (In, Out, OutDown, OutUp, ...)
- Hinge
- JackInTheBox
- RollIn
- RollOut
- TextGlow
- BackgroundGlow

## Quick start

### Basic

```
Text text = new Text("AnimateFX");
new Bounce(text).play();
```

### Play an animation after another

```
Text text = new Text("AnimateFX");

public void HandleAnimation(ActionEvent actionEvent) {
   new Bounce(text).setPlayOnFinished(new BounceIn(text)).play();
}
```

## Contributing

Please see [CONTRIBUTING.md](https://github.com/Typhon0/AnimateFX/blob/master/CONTRIBUTING.md) for more information.

## Credits

Animations are inspired from the awesome project [Animate.css](https://github.com/daneden/animate.css)