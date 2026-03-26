## SynchronizeFX

SynchronizeFX enables real-time data binding between JavaFX applications running on different JVMs, potentially on different machines connected via a network. Changes to JavaFX properties on one JVM are automatically propagated to all connected peers, keeping the data model synchronized across instances.

The library supports both client-server and peer-to-peer topologies and uses a changeset-based protocol to minimize network traffic. It integrates directly with JavaFX's `Property` and `ObservableList` types, requiring minimal changes to existing model code.

SynchronizeFX is useful for collaborative applications, multi-screen setups, distributed monitoring dashboards, or any scenario where multiple JavaFX clients need to share a live data model.
