TiwulFX-Dock provides enhanced JavaFX TabPane that supports tab reordering, detaching and docking.

## Usage

Wrap `DetachableTabPane` inside a `SplitPane` _(required)_:

```xml
<SplitPane VBox.vgrow="ALWAYS">
    <items>
        <DetachableTabPane fx:id="myTabPane"/>
    </items>
</SplitPane>
```

If you hold a reference to a `DetachableTabPane` and need to keep it current when it closes (due to drag-drop), auto-update it to the adjacent pane:

```java
myTabPane.setOnClosedPassSibling(sibling -> myTabPane = sibling);
myTabPane.setOnRemove(self -> { ... });
```

Control creation of new tab panes from drag-drop via `DetachableTabPane#setDetachableTabPaneFactory(DetachableTabPaneFactory)`.

## Customization

**Custom scene for detached stages:**
```java
myTabPane.setSceneFactory(tabPane -> {
    SplitPane sp = new SplitPane(tabPane);
    VBox.setVgrow(sp, Priority.ALWAYS);
    FrmScope1 contentWrapper = new FrmScope1();
    contentWrapper.getChildren().add(sp);
    return new Scene(contentWrapper);
});
```

**Custom stage factory:**
```java
myTabPane.setStageFactory((priorParent, tab) -> new TabStage(priorParent, tab));
```

**Custom drop indicator:** Provide your own [tiwulfx-dock.css](https://github.com/panemu/tiwulfx-dock/blob/main/src/main/resources/com/panemu/tiwulfx/control/dock/tiwulfx-dock.css), or extend [`TabDropHint`](https://github.com/panemu/tiwulfx-dock/blob/main/src/main/java/com/panemu/tiwulfx/control/dock/TabDropHint.java):

```java
myTabPane.setDropHint(new CustomDropHint());
```

All stylesheets from the originating scene are automatically copied to new detached stages.