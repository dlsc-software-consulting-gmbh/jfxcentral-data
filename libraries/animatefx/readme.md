AnimateFX is a library of 70+ ready-to-use animations for JavaFX, inspired by [Animate.css](https://github.com/daneden/animate.css). Supports custom animations and interpolators, play/stop control, and chaining animations.

## Animations

| Category | Variants |
|----------|----------|
| Attention | Bounce, Flash, Pulse, RubberBand, Shake, Swing, Tada, Wobble, Jello |
| Bounce | In, InDown, InLeft, InRight, InUp, Out, OutDown, … |
| Fade | In, Out, InDown, InDownBig, InLeftBig, … |
| Flip | InX, InY, OutX, OutY |
| LightSpeed | In, Out |
| Rotate | In, InDownLeft, … |
| Slide | InUp, InDown, InLeft, InRight, OutUp, OutDown, … |
| Zoom | In, Out, OutDown, OutUp, … |
| Special | Hinge, JackInTheBox, RollIn, RollOut, TextGlow, BackgroundGlow |

## Quick Start

```java
// Play a single animation
new Bounce(text).play();

// Chain animations
new Bounce(text).setPlayOnFinished(new BounceIn(text)).play();
```
