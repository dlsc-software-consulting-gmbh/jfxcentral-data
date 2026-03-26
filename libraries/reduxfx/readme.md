## ReduxFX

ReduxFX brings the Redux architectural pattern to JavaFX. The application state is stored in a single immutable data structure, UI interactions dispatch actions, and a pure reducer function computes the next state. A virtual scene graph then efficiently reconciles the new state to the actual JavaFX scene graph.

This unidirectional data flow makes application behavior predictable and easy to reason about. The entire application state can be logged, time-traveled for debugging, and replayed for testing purposes, since all transformations are pure functions.

ReduxFX is an exploration of functional reactive programming ideas in the JavaFX context and is well suited for complex UIs where conventional mutable state management becomes difficult to maintain.
