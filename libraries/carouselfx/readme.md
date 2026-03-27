## CarouselFX

CarouselFX is a feature-rich carousel and slideshow component for JavaFX. It ships with 75+ built-in page transition animations — slides, fades, flips, cubes, shatters, blinds, and many more — all switchable with a single line of code. The component is ready to use out of the box for image galleries, advertisement banners, app page navigation, and similar scenarios.

Key features include configurable auto-play with hover-pause and countdown progress, circular and non-circular navigation modes, built-in left/right navigation arrows with AUTO/SHOW/HIDE display modes, and a bottom navigator with dot indicators that automatically switches to a compact `[◄] 3 / 200 [►]` mode for large page counts. Page caching with distance-based eviction and a full page lifecycle event system (`CACHED → OPENING → OPENED → CLOSING → CLOSED → EVICTED`) give developers fine-grained control over resource management.

A convenience `ImagePane` container handles image display with `COVER`, `FIT`, and `STRETCH` modes. The component is fully CSS-stylable, targets Java 11+ with JavaFX 11+, and depends only on `javafx-controls`. CarouselFX was developed by the author of [RXControls](https://github.com/leewyatt/rxcontrols) as an independent, standalone extraction of its carousel component.
