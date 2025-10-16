Cljfx is a declarative, functional and extensible wrapper of JavaFX
inspired by better parts of react and re-frame.

## Rationale

Cljfx wants to provide an elegant, declarative and composable UI
library for JVM. It's inspired by react,
reagent, re-frame and fn-fx.

Like react, it allows to specify only desired layout, and handles
all actual changes underneath. Unlike react (and web in general) it does
not impose xml-like structure of everything possibly having multiple
children, thus it uses maps instead of hiccup for describing layout.

Like reagent, it allows to specify component descriptions using simple
constructs such as data and functions. Unlike reagent, it rejects using
multiple stateful reactive atoms for state and instead prefers composing
ui in more pure manner.

Like re-frame, it provides an approach to building large applications
using subscriptions and events to separate view from logic. Unlike
re-frame, it has no hard-coded global state, and subscriptions work on
referentially transparent values instead of ever-changing atoms.

Like fn-fx, it wraps underlying JavaFX library so developer can describe
everything with clojure data. Unlike fn-fx, it is more dynamic, allowing
users to use maps and functions instead of macros and deftypes, and has
more explicit and extensible lifecycle for components.

## Installation and requirements

Cljfx uses `tools.deps`, so you can add this repo with latest sha as a
dependency:
```edn
 cljfx {:git/url "https://github.com/cljfx/cljfx" :sha "<insert-sha-here>"}
```
Cljfx is also published on Clojars, so you can add `cljfx` as a maven
dependency, current version is on this badge:

[![Cljfx on Clojars](https://clojars.org/cljfx/cljfx/latest-version.svg)](https://clojars.org/cljfx/cljfx)

Minimum required version of clojure is 1.10.

When depending on git coordinates, minimum required Java version is 11. When using maven
dependency, both Java 8 (assumes it has JavaFX provided in JRE) and Java 11 (via openjfx
dependency) are supported. You don't need to configure anything in this regard: correct
classifiers are picked up automatically.

Please note that JavaFX 8 is outdated and has problems some people consider severe: it
does not support HiDPI scaling on Linux, and sometimes crashes JVM on macOS Mojave. You
should prefer JDK 11.

More extended instructions are provided on the [GitHub README.md](https://github.com/cljfx/cljfx).