## JFXAnimation

JFXAnimation is a builder library for creating JavaFX animations using a CSS keyframe animation syntax. Instead of manually constructing `Timeline` and `KeyFrame` objects, you describe animations using a fluent builder API modeled after CSS `@keyframes`, making animation definitions more readable and maintainable.

The library supports percentage-based keyframes, easing functions, delays, iteration counts, and direction (normal, reverse, alternate). It translates the builder description into a standard JavaFX `Animation` that can be played, paused, and reversed like any other.

JFXAnimation is particularly useful for developers who are familiar with CSS animations and want to bring the same expressive syntax to their JavaFX application animations.
