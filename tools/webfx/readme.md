A new way to develop modern web applications ... in Java and JavaFX. WebFX is a JavaFX to Javascript transpiler powered by GWT. You write your web application in JavaFX, and GWT will transpile it to pure Javascript (no plugin, no server). WebFX patches the higher layer of OpenJFX to make it GWT compatible, and replaces the lower layer with a "JavaFX scene graph to browser" DOM mapper. You don't need to transpile each code change, you can use the OpenJFX runtime to develop, test and debug your application as usual in your Java IDE, and transpile it only from time to time to check your web version is working as expected. WebFX is new and doesn't yet cover all JavaFX features, but it has a lot of potential and may quickly receive support from the JavaFX & GWT communities. WebFX is now ready for testing. We hope you will love it.

![WebFX Website](website.png)

WebFX Website

## Full Stack

WebFX makes 100% Java full-stack development possible, with JavaFX as the client-side technology. Web development is transitioning to client-side technologies, and this often introduces heterogeneity in Java-based environments. Standardizing your client stack with a JS front & back-office may be an option for sharing common client code, but your stack is still heterogeneous. Switching to a pure JS stack may be a solution, but you would prefer to stay with Java, right? With WebFX you can use JavaFX for your client-side web technology, and stick with Java throughout your stack. The same language for all your stack. The same UI toolkit for your front & back-office. The same IDE for all your code. The ideal solution for Java-based environments. Simply share common code between your front-office, back-office and server using the Java module system.

## Cross Platform
Your WebFX applications will also run natively on desktops, mobiles & embeds. As with any JavaFX application, the JDK toolchain will generate your WebFX application desktop executables (powered by an optimized JVM). In addition, the Gluon toolchain will generate native executables for the desktop (no JVM - your application compiled into native by GraalVM), and also for Android & iOS. Ideal for your front-end development. Gluon can even build your application for Raspberry Pi, with its JavaFX runtime for embeds (more devices to come). And finally, WebFX compiles your application for the Web platform. Perfect for your front-end development 7 platforms from a single code base! (check-out the demos for a Github workflow example)


## Magical

WebFX generates fast and lightweight web apps despite the heavy JavaFX footprint. How is this possible? The resultant JS code only needs to create a DOM rendered by the browser, and doesn't include the big JavaFX rendering layer, as opposed to the desktop version. And GWT removes all dead code! This makes your JS code much thinner, incorporating only the JavaFX classes required by your application. GWT is known to produce compact and highly optimized JS code, that potentially runs faster than hand-rolled JS. And JavaFX itself is fast, because it's not a framework but &ldquo;just&rdquo; a UI toolkit. No unwanted processing slowing things down. Here is the Lighthouse performance score for this website. Isn't WebFX magical?

## Long Term

WebFX provides your applications with longevity. The lifespan of your web application is affected by the lifespan of its UI toolkit. As the Java desktop ecosystem is hyper stable, we can safely assume that JavaFX is here to stay. In the divided and highly competitive Web ecosystem, the lifespan of a web framework is very uncertain. WebFX injects a measure of certainty by inheriting the long lifespan characteristics of JavaFX. Another requirement for your web application to flourish is a good refactoring support. And Java, being a strictly-typed language, excels in refactoring. A lasting UI toolkit and good refactoring support are important requirements for application longevity, and are comprehensively fulfilled by Java & JavaFX. This is also what has motivated us to build WebFX: to make these sustainable, long-term technologies available for the Web.";

## Responsive

The JavaFX layout system makes responsive web design easy and powerful. JavaFX can callback your Java code during the layout pass, giving you full control to position nodes. Your layout code has access to the application context, giving you full visibility whilst writing your responsive design. While the number of CSS rules and functions are limited, you are empowered with all the possibilities of a programmatic language in your layout code. Your layout code is not limited to top level nodes, but will work at all levels in the scene graph. We made this website responsive entirely through this JavaFX feature, using it at many different levels of the scene graph. Easy and powerful.

## Demos


| Live Demo                                             |          Category           |                   Java file                    |                  Repository                   |                                           WebFX library                                            |                                                        Credits                                                        |
|-------------------------------------------------------|:---------------------------:|:----------------------------------------------:|:---------------------------------------------:|:--------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------:|
| Website                                               |                             |                                                |                                               |                                                                                                    |                                                                                                                       |
| [Colorful Circles](https://colorfulcircles.webfx.dev) |            Basic            | [Java file][webfx-colorfulcircles-code-link] ⭐ | [Repository][webfx-colorfulcircles-repo-link] |                                                                                                    |                   [Oracle](https://docs.oracle.com/javafx/2/get_started/ColorfulCircles.java.html)                    |
| [Tally Counter][webfx-tallycounter-demo-link]         |       Custom controls       |  [Java file][webfx-tallycounter-code-link] ⭐   |  [Repository][webfx-tallycounter-repo-link]   |                    [Odometer](https://github.com/webfx-libs/webfx-lib-odometer)                    |                                    [HanSolo](https://github.com/HanSolo/odometer)                                     |
| [Modern Gauge][webfx-moderngauge-demo-link]           |       Custom controls       |   [Java file][webfx-moderngauge-code-link] ⭐   |   [Repository][webfx-moderngauge-repo-link]   |                                     [Medusa][webfx-lib-medusa]                                     |                                     [HanSolo](https://github.com/HanSolo/Medusa)                                      |
| [Enzo Clocks][webfx-enzoclocks-demo-link]             |       Custom controls       |    [Java file][webfx-enzoclocks-code-link]     |   [Repository][webfx-enzoclocks-repo-link]    | [Enzo](https://github.com/webfx-libs/webfx-lib-enzo)<br/>[Circle Packing][webfx-lib-circlepacking] |                               [HanSolo](https://bitbucket.org/hansolo/enzo/src/master/)                               |
| [FX2048][webfx-fx2048-demo-link]                      |            Games            |      [Java file][webfx-fx2048-code-link]       |     [Repository][webfx-fx2048-repo-link]      |                                                                                                    |                                 [Bruno Borges](https://github.com/brunoborges/fx2048)                                 |                                                                           |
| [SpaceFX][webfx-spacefx-demo-link] ⓒ ♪                |            Games            |      [Java file][webfx-spacefx-code-link]      |     [Repository][webfx-spacefx-repo-link]     |                                                                                                    |                                     [HanSolo](https://github.com/HanSolo/SpaceFX)                                     |
| [DemoFX][webfx-demofx-demo-link] ⓒ ♪                  |          Animation          |     [Java file][webfx-demofx-code-link] ⭐      |     [Repository][webfx-demofx-repo-link]      |                                     [DemoFX][webfx-lib-demofx]                                     |                                             [Chris Newland][demofx-repo]                                              |
| [Ray Tracer][webfx-raytracer-demo-link] ⊕             |         Web workers         |     [Java file][webfx-raytracer-code-link]     |    [Repository][webfx-raytracer-repo-link]    |                           [Tracer framework][webfx-lib-tracerframework]                            |                       [Steven T. Rowland](https://github.com/steventrowland/JavaFX-Ray-Tracer)                        | 
| [Mandelbrot][webfx-mandelbrot-demo-link] ⊕            |         WebAssembly         |    [Java file][webfx-mandelbrot-code-link]     |   [Repository][webfx-mandelbrot-repo-link]    |                           [Tracer framework][webfx-lib-tracerframework]                            |                  [David J. Eck](https://math.hws.edu/eck/js/mandelbrot/java/xMandelbrotSource-1-2/)                   |
| Additional demos                                      |                             |                                                |                                               |                                                                                                    |                                                                                                                       |
| [Tetris][webfx-tetris-demo-link] ⓒ ♪                  |            Games            |      [Java file][webfx-tetris-code-link]       |     [Repository][webfx-tetris-repo-link]      |                                                                                                    |                                     [HanSolo](https://github.com/HanSolo/tetris)                                      |
| [JArkanoid][webfx-jarkanoid-demo-link] ⓒ ♪            |            Games            |     [Java file][webfx-jarkanoid-code-link]     |    [Repository][webfx-jarkanoid-repo-link]    |                                                                                                    |                                    [HanSolo](https://github.com/HanSolo/jArkanoid)                                    |
| [Pac-Man][webfx-pacman-demo-link] ⓒ ♪                 |            Games            |      [Java file][webfx-pacman-code-link]       |     [Repository][webfx-pacman-repo-link]      |                                                                                                    |                           [Armin Reichert](https://github.com/armin-reichert/pacman-javafx)                           |
| [Ms. Pac-Man][webfx-mspacman-demo-link] ⓒ ♪           |            Games            |      [Java file][webfx-pacman-code-link]       |     [Repository][webfx-pacman-repo-link]      |                                                                                                    |                           [Armin Reichert](https://github.com/armin-reichert/pacman-javafx)                           |
| [Particles][webfx-particles-demo-link] ⓒ              |            Basic            |   [Java file️][webfx-particles-code-link] ⭐    |    [Repository][webfx-particles-repo-link]    |                                                                                                    |                       [Sketch.js](https://soulwire.github.io/sketch.js/examples/particles.html)                       |
| [Mostly Fluid][webfx-mostlyfluid-demo-link]           | Basic<br/>Responsive design |   [Java file][webfx-mostlyfluid-code-link] ⭐   |   [Repository][webfx-mostlyfluid-repo-link]   |                                                                                                    |  [*Anonymous*](http://underpop.online.fr/w/web-fundamentals/fundamentals/design-and-ux/responsive/mostly-fluid.html)  |
| [Medusa Clock][webfx-medusaclock-demo-link]           |       Custom controls       |   [Java file][webfx-medusaclock-code-link] ⭐   |   [Repository][webfx-medusaclock-repo-link]   |                                     [Medusa][webfx-lib-medusa]                                     |                                     [HanSolo](https://github.com/HanSolo/Medusa)                                      |                                                   
| WebFX Extras                                          |                             |                                                |                                               |                                                                                                    |                                                                                                                       |
| [Files][webfx-files-demo-link] ⊕ ♪                    |         Local files         |       [Java file][webfx-files-code-link]       |      [Repository][webfx-files-repo-link]      |             [FilePicker][webfx-extras-filepicker-link]<br/>[DemoFX][webfx-lib-demofx]              |                                                                                                                       | |
| [FlexBox][webfx-flexbox-demo-link]                    |      Responsive design      |     [Java file][webfx-flexbox-code-link] ⭐     |     [Repository][webfx-flexbox-repo-link]     |                                [FlexBox][webfx-extras-flexbox-link]                                |                                                                                                                       | |
| [Led Clock][webfx-ledclock-demo-link] ⓒ               |       Custom controls       |    [Java file][webfx-ledclock-code-link] ⭐     |    [Repository][webfx-ledclock-repo-link]     |                    [Led][webfx-extras-led-link]<br/>[Medusa][webfx-lib-medusa]                     | [HanSolo](https://github.com/HanSolo/medusa/blob/master/src/main/java/eu/hansolo/medusa/skins/MorphingClockSkin.java) |
| [Led Packing][webfx-ledpacking-demo-link]             |      Responsive design      |   [Java file][webfx-ledpacking-code-link] ⭐    |   [Repository][webfx-ledpacking-repo-link]    |             [Led][webfx-extras-led-link]<br/>[Circle Packing][webfx-lib-circlepacking]       


[webfx-repo]: https://github.com/webfx-project/webfx
[webfx-website]: https://webfx.dev
[webfx-contact]: mailto:info@webfx.dev
[webfx-colorfulcircles-demo-link]: https://colorfulcircles.webfx.dev
[webfx-colorfulcircles-repo-link]: https://github.com/webfx-project/webfx-demo-colorfulcircles
[webfx-colorfulcircles-code-link]: https://github.com/webfx-demos/webfx-demo-colorfulcircles/blob/main/webfx-demo-colorfulcircles-application/src/main/java/dev/webfx/demo/colorfulcircles/ColorfulCircles.java
[webfx-particles-demo-link]: https://particles.webfx.dev
[webfx-particles-repo-link]: https://github.com/webfx-project/webfx-demo-particles
[webfx-particles-code-link]: https://github.com/webfx-demos/webfx-demo-particles/blob/main/webfx-demo-particles-application/src/main/java/dev/webfx/demo/particles/ParticlesApplication.java
[webfx-tallycounter-demo-link]: https://tallycounter.webfx.dev
[webfx-tallycounter-repo-link]: https://github.com/webfx-project/webfx-demo-tallycounter
[webfx-tallycounter-code-link]: https://github.com/webfx-demos/webfx-demo-tallycounter/blob/main/webfx-demo-tallycounter-application/src/main/java/dev/webfx/demo/tallycounter/TallyCounterApplication.java
[webfx-moderngauge-demo-link]: https://moderngauge.webfx.dev
[webfx-moderngauge-repo-link]: https://github.com/webfx-project/webfx-demo-moderngauge
[webfx-moderngauge-code-link]: https://github.com/webfx-demos/webfx-demo-moderngauge/blob/main/webfx-demo-moderngauge-application/src/main/java/dev/webfx/demo/moderngauge/ModernGaugeApplication.java
[webfx-enzoclocks-demo-link]: https://enzoclocks.webfx.dev
[webfx-enzoclocks-code-link]: https://github.com/webfx-demos/webfx-demo-enzoclocks/blob/main/webfx-demo-enzoclocks-application/src/main/java/dev/webfx/demo/enzoclocks/EnzoClocksApplication.java
[webfx-enzoclocks-repo-link]: https://github.com/webfx-project/webfx-demo-enzoclocks
[webfx-fx2048-demo-link]: https://fx2048.webfx.dev
[webfx-fx2048-repo-link]: https://github.com/webfx-project/webfx-demo-fx2048
[webfx-fx2048-code-link]: https://github.com/webfx-demos/webfx-demo-fx2048/blob/main/webfx-demo-fx2048-application/src/main/java/io/fxgame/game2048/Game2048.java
[webfx-fx2048-release-link]: https://github.com/webfx-demos/webfx-demo-fx2048/releases
[webfx-spacefx-demo-link]: https://spacefx.webfx.dev
[webfx-spacefx-code-link]: https://github.com/webfx-demos/webfx-demo-spacefx/blob/main/webfx-demo-spacefx-application/src/main/java/eu/hansolo/spacefx/SpaceFX.java
[webfx-spacefx-repo-link]: https://github.com/webfx-demos/webfx-demo-spacefx
[webfx-tetris-demo-link]: https://tetris.webfx.dev
[webfx-tetris-code-link]: https://github.com/webfx-demos/webfx-demo-tetris/blob/webfx/webfx-demo-tetris-application/src/main/java/eu/hansolo/fx/tetris/Main.java
[webfx-tetris-repo-link]: https://github.com/webfx-demos/webfx-demo-tetris/tree/webfx
[webfx-jarkanoid-demo-link]: https://jarkanoid.webfx.dev
[webfx-jarkanoid-code-link]: https://github.com/webfx-demos/webfx-demo-jarkanoid/blob/webfx/webfx-demo-jarkanoid-application/src/main/java/eu/hansolo/fx/jarkanoid/Main.java
[webfx-jarkanoid-repo-link]: https://github.com/webfx-demos/webfx-demo-jarkanoid/tree/webfx
[webfx-pacman-demo-link]: https://pacman.webfx.dev
[webfx-pacman-code-link]: https://github.com/webfx-demos/webfx-demo-pacman/blob/webfx/pacman-ui-fx/src/main/java/de/amr/games/pacman/ui/fx/app/GameApp.java
[webfx-pacman-repo-link]: https://github.com/webfx-demos/webfx-demo-pacman/tree/webfx
[webfx-mspacman-demo-link]: https://mspacman.webfx.dev
[webfx-demofx-demo-link]: https://demofx.webfx.dev
[webfx-demofx-repo-link]: https://github.com/webfx-demos/webfx-demo-demofx
[webfx-demofx-code-link]: https://github.com/webfx-demos/webfx-demo-demofx/blob/main/webfx-demo-demofx-application/src/main/java/dev/webfx/demo/demofx/DemoFXApplication.java
[webfx-raytracer-demo-link]: https://raytracer.webfx.dev
[webfx-raytracer-code-link]: https://github.com/webfx-demos/webfx-demo-raytracer/blob/main/webfx-demo-raytracer-application/src/main/java/dev/webfx/demo/raytracer/RayTracerApplication.java
[webfx-raytracer-repo-link]: https://github.com/webfx-project/webfx-demo-raytracer
[webfx-mandelbrot-demo-link]: https://mandelbrot.webfx.dev
[webfx-mandelbrot-code-link]: https://github.com/webfx-demos/webfx-demo-mandelbrot/blob/main/webfx-demo-mandelbrot-application/src/main/java/dev/webfx/demo/mandelbrot/MandelbrotApplication.java
[webfx-mandelbrot-repo-link]: https://github.com/webfx-project/webfx-demo-mandelbrot
[webfx-medusaclock-demo-link]: https://medusaclock.webfx.dev
[webfx-medusaclock-repo-link]: https://github.com/webfx-demos/webfx-demo-medusaclock
[webfx-medusaclock-code-link]: https://github.com/webfx-demos/webfx-demo-medusaclock/blob/main/webfx-demo-medusaclock-application/src/main/java/dev/webfx/demo/medusaclock/MedusaClockApplication.java
[webfx-boundgauge-demo-link]: https://boundgauge.webfx.dev
[webfx-boundgauge-repo-link]: https://github.com/webfx-demos/webfx-demo-boundgauge
[webfx-boundgauge-code-link]: https://github.com/webfx-demos/webfx-demo-boundgauge/blob/main/webfx-demo-boundgauge-application/src/main/java/dev/webfx/demo/boundgauge/BoundGaugeApplication.java
[webfx-mostlyfluid-demo-link]: https://mostlyfluid.webfx.dev
[webfx-mostlyfluid-repo-link]: https://github.com/webfx-demos/webfx-demo-mostlyfluid
[webfx-mostlyfluid-code-link]: https://github.com/webfx-demos/webfx-demo-mostlyfluid/blob/main/webfx-demo-mostlyfluid-application/src/main/java/dev/webfx/demo/mostlyfluid/MostlyFluidApplication.java
[webfx-files-code-link]: https://github.com/webfx-demos/webfx-demo-files/blob/main/webfx-demo-files-application/src/main/java/dev/webfx/demo/files/FilesApplication.java
[webfx-files-demo-link]: https://files.webfx.dev
[webfx-files-repo-link]: https://github.com/webfx-demos/webfx-demo-files
[webfx-extras-filepicker-link]: https://github.com/webfx-project/webfx-extras
[webfx-flexbox-code-link]: https://github.com/webfx-demos/webfx-demo-flexbox/blob/main/webfx-demo-flexbox-application/src/main/java/dev/webfx/demo/flexbox/FlexBoxApplication.java
[webfx-flexbox-demo-link]: https://flexbox.webfx.dev
[webfx-flexbox-repo-link]: https://github.com/webfx-demos/webfx-demo-flexbox
[webfx-extras-flexbox-link]: https://github.com/webfx-project/webfx-extras
[webfx-ledclock-demo-link]: https://ledclock.webfx.dev
[webfx-ledclock-repo-link]: https://github.com/webfx-demos/webfx-demo-ledclock
[webfx-ledclock-code-link]: https://github.com/webfx-demos/webfx-demo-ledclock/blob/main/webfx-demo-ledclock-application/src/main/java/dev/webfx/demo/ledclock/LedClockApplication.java
[webfx-ledpacking-demo-link]: https://ledpacking.webfx.dev
[webfx-ledpacking-repo-link]: https://github.com/webfx-demos/webfx-demo-ledpacking
[webfx-ledpacking-code-link]: https://github.com/webfx-demos/webfx-demo-ledpacking/blob/main/webfx-demo-ledpacking-application/src/main/java/dev/webfx/demo/ledpacking/LedPackingApplication.java
[webfx-extras-led-link]: https://github.com/webfx-project/webfx-extras
[webfx-lib-medusa]: https://github.com/webfx-libs/webfx-lib-medusa
[webfx-lib-circlepacking]: https://github.com/webfx-libs/webfx-lib-circlepacking
[webfx-lib-demofx]: https://github.com/webfx-libs/webfx-lib-demofx
[webfx-lib-tracerframework]: https://github.com/webfx-libs/webfx-lib-tracerframework
[demofx-repo]: https://github.com/chriswhocodes/DemoFX