# TiwulFX Dock

TiwulFX-Dock provides enhanced JavaFX TabPane that supports tab reordering, detaching and docking.

## Usage

Wrap `DetachableTabPane` inside a `SplitPane`. _This is required._ Below is an example in FXML:

```xml

<SplitPane VBox.vgrow="ALWAYS">
    <items>
        <DetachableTabPane fx:id="myTabPane"/>
    </items>
</SplitPane>
```

If you have a reference to a `DetachableTabPane` in your program and need to ensure it stays up to date in the case of
a `DetachableTabPane` being closed _(Due to drag and drop or some other cause)_ you can automatically update it to the
adjacent tab-pane _(which expands to consume the now empty space)_ like so:

```java
DetachableTabPane myTabPane= /* instantiation */;

// when 'myTabPane' is closed, update it to nearest sibling instance
        myTabPane.setOnClosedPassSibling(sibling->myTabPane=sibling);
// you can also listen for tab-pane closures via
        myTabPane.setOnRemove(self->{...});
```

You can control the creation of detachable tab panes created as a result of drag-drop operations
via: `DetachableTabPane#setDetachableTabPaneFactory(DetachableTabPaneFactory)`

## Customization

To customize how detached stage looks like, use a custom scene factory:

```java
myTabPane.setSceneFactory(tabPane->{
        SplitPane sp=new SplitPane(tabPane);
        VBox.setVgrow(sp,Priority.ALWAYS);

        FrmScope1 contentWrapper=new FrmScope1();

        contentWrapper.getChildren().add(sp);
        Scene scene1=new Scene(contentWrapper);
        return scene1;
        });
```

Or for window level control, use a custom stage factory:

```java
myTabPane.setStageFactory((priorParent,tab)->new TabStage(priorParent,tab));
```

The stylesheet is available
at: [tiwulfx-dock.css](https://github.com/panemu/tiwulfx-dock/blob/main/src/main/resources/com/panemu/tiwulfx/control/dock/tiwulfx-dock.css).
Provide your own version of the stylesheet to override the style of drop guiding path and adjacent drop buttons. For a
further customization of the drag-drop indicator,
extends [`TabDropHint`](https://github.com/panemu/tiwulfx-dock/blob/main/src/main/java/com/panemu/tiwulfx/control/dock/TabDropHint.java)
and supply the instance of it to the tab pane:

```java
myTabPane.setDropHint(new CustomDropHint());
```

When new stages are created as a result from drag-drop operations all stylesheets of the originating scene are copied to
the new stage's scene.

## Demo

Small demo applications can be found
at: [`src/test/java/com/panemu/tiwulfx/control/dock/`](https://github.com/panemu/tiwulfx-dock/tree/main/src/test/java/com/panemu/tiwulfx/control/dock)