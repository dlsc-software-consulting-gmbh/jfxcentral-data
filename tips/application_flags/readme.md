JavaFX has various flags to either add debug logs or switch configuration.

## General

| Flag | Default | Options | Details |
| :--- | :------ | :------ | :------ |
| glass.platform | platform specific | macosx, windows, linux, gtk, ios, Monocle, swt | Sets the platform for JavaFX application |
| jdk.gtk.verbose | false | Boolean | Verbose logging related to GTK on Linux |
| jdk.gtk.version | 3 (since JFX 11) | 2, 3 | Toggle between GTK versions. Default is 3 since OpenJFX 11 |
| javafx.verbose | false | boolean | Verbose logging for JavaFX |
| monocle.platform | MX6, OMAP, Dispman, Android, X11, Linux, Headless | MX6, OMAP, Dispman, Android, X11, Linux, Headless | Series of cascading fallbacks for what NativePlatform types |

## Accessibility

| Flag | Default | Options | Details |
| :--- | :------ | :------ | :------ |
| glass.accessible.force | false  | boolean | Force enable a11y on older platforms |

## Animation

| Flag | Default | Options | Details |
| :--- | :------ | :------ | :------ |
| javafx.animation.pulse | 60 | number in hz | override the default pulse rate |
| javafx.animation.framerate | 60 | number in hz | override the default frame rate |
| javafx.animation.fullspeed | false | boolean |   |
| com.sun.scenario.animation.adaptivepulse | false | boolean |   |
| com.sun.scenario.animation.fixed.pulse.length | false | boolean | increment each animation by a fixed length of time for each pulse instead of using system time |
| com.sun.scenario.animation.AnimationMBean.enabled | false | boolean | |

## Quantum

| Flag | Default | Options | Details                                              |
| :--- | :------ | :------ |:-----------------------------------------------------|
| quantum.debug | false | boolean | Force enable a11y on older platforms                 |
| quantum.multithreaded | true | boolean | Disables multi-threaded toolkit                      |
| quantum.norenderjobs | false | boolean | Quantum will stop submitting render jobs             |
| quantum.singlethreaded | false | boolean | Enable single GUI threading. Can cause a drop in FPS |
| quantum.verbose | false | boolean | Verbose logging in Quantum toolkit                   |

## Prism

| Flag | Default | Options | Details |
| :--- | :------ | :------ | :------ |
| prism.allowhidpi | true | boolean | Disables hi-dpi scaling |
| prism.cacheshapes | complex | complex, all | Shape caching optimizations |
| prism.debug | false | boolean | Debug output in prism |
| prism.debugfonts | false | boolean | Debug output related to fonts |
| prism.dirtyopts | true | boolean | Disables dirty region optimizations |
| prism.dirtyregioncount | 6 | number between 0 and 15 | Sets the number of dirty regions to use |
| prism.disableBadDriverWarning | false | boolean | Disable bad driver check warning |
| prism.forceGPU | false | boolean | Force prism to run in HW accelerated mode |
| prism.forcepaint | false | boolean | Force scene repaint on every frame |
| prism.newiio | true | boolean | Disable use of new javafx-iio image loader |
| prism.noFallback | false | boolean | disable fallback to another toolkit if prism couldn’t be initialized |
| prism.order | platform specific | j2d, d3d, es2, sw | Graphics engines |
| prism.primtextures | 0 | true, false, | Sets texture mask size |
| prism.printallocs | false | boolean | Print texture allocation data |
| prism.printrendergraph | false | boolean | Prints out the render graph, annotated with dirty opts information |
| prism.printStats | 0 | true or number (<=0 means do not print) | Prism statistics print frequency |
| prism.rasterizerorder | marlin | nativepisces, javapisces, marlin, doublemarlin | Select an alternative to default rasterizer |
| prism.scrollcacheopt | false | boolean | Scrolling cache optimization |
| prism.showdirty | false | boolean | Draws overlay rectangles showing where the dirty regions were |
| prism.showoverdraw | false | boolean | Draws overlay rectangles showing not only the dirty regions, but the count each area within that dirty region was drawn (covered by bounds of a drawn object) |
| prism.trace | false | boolean | Trace output in prism |
| prism.verbose | false | boolean | Verbose output in prism |

## Scaling

| Flag               | Default | Options | Details |
|:-------------------| :------ | :------ | :------ |
|  glass.gtk.uiScale | | decimal e.g. "1.25" | Force UI scaling factor |
| glass.win.forceIntegerRenderScale | true | boolean | Disables force integer rendering scale |
| glass.win.minHiDPI | | percentage e.g. "125%" |  |
| glass.win.renderScale |  | percentage e.g. "125%" | | 
| glass.win.uiScale |  | percentage e.g. "125%" |  |
| sun.java2d.uiScale |  | percentage e.g. "125%" |  |
| sun.java2d.uiScale.enabled |  | true |  |
| swt.autoScale |  | number e.g. "125" |  |



