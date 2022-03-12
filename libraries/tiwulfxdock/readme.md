## Usage

Wrap `DetachableTabPane` inside a `SplitPane`. _This is required._ Below is an example in FXML:
```xml
<SplitPane VBox.vgrow="ALWAYS">
	<items>
		<DetachableTabPane fx:id="myTabPane" />
	</items>
</SplitPane>
```

If you have a reference to a `DetachableTabPane` in your program and need to ensure it stays up to date in the case of a `DetachableTabPane` being closed _(Due to drag and drop or some other cause)_ you can automatically update it to the adjacent tab-pane _(which expands to consume the now empty space)_ like so:
```java
DetachableTabPane myTabPane = /* instantiation */;

// when 'myTabPane' is closed, update it to nearest sibling instance
myTabPane.setOnClosedPassSibling(sibling -> myTabPane = sibling);
```

## Customization

To customize how detached stage looks like, use custom scene factory:
```java
myTabPane.setSceneFactory((param) -> {
	FrmScope1 frm = new FrmScope1();
	SplitPane sp = new SplitPane(param);
	VBox.setVgrow(sp, Priority.ALWAYS);
	frm.getChildren().add(sp);
	Scene scene1 = new Scene(frm);
	return scene1;
});
```

The stylesheet is available at: [tiwulfx-dock.css](https://github.com/panemu/tiwulfx-dock/blob/main/src/main/resources/com/panemu/tiwulfx/control/dock/tiwulfx-dock.css).
Provide your own version of the stylesheet to override the style of drop guiding path and adjacent drop buttons. For a further customization of the drag-drop indicator, extends [`TabDropHint`](https://github.com/panemu/tiwulfx-dock/blob/main/src/main/java/com/panemu/tiwulfx/control/dock/TabDropHint.java) and supply the instance of it to the tab pane:
```java
myTabPane.setDropHint(new CustomDropHint());
```
