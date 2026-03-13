A docking system for JavaFX (requires JavaFX 19+ and Java 21+).

![overview](overview.png)

## Structure

The `Node` hierarchy in BentoFX:

- `DockContainerRootBranch`
  - `DockContainerBranch` *(nesting depth depends on implementation)*
    - `DockContainerLeaf`
      - `Dockable` *(zero or more)*

All containers and `Dockable` instances are built via a `Bento` instance using `bento.dockBuilding()`.

## Containers

![containers](containers.png)

| Container | Description |
|-----------|-------------|
| `DockContainerBranch` | Holds child containers in a `SplitPane`. Orientation and child scaling work the same as `SplitPane`. |
| `DockContainerLeaf` | Displays any number of `Dockable` items rendered by a `HeaderPane`. Handles drag-and-drop. |

## Controls

![controls](controls.png)

Bento's custom controls require a stylesheet — a reference `bento.css` is included in the dependency.

| Control | Description |
|---------|-------------|
| `Header` | Visual representation of a `Dockable`. |
| `HeaderPane` | Holds multiple `Header` children and displays the selected one's content. |
| `Headers` | Child of `HeaderPane`; a `HBox`/`VBox` holding multiple `Header` instances. |
| `ButtonHBar` / `ButtonVBar` | Buttons in a `HeaderPane` for context menus and overflowing `Header` selection. |

## Dockable

`Dockable` is the model behind each `Header` (analogous to `Tab` in a `TabPane`). It defines whether the header is draggable, where it can be dropped, what text/graphic to display, and the associated JavaFX `Node` to show inside a `DockContainerLeaf`.

## Example

An IDE-style layout: project explorer on the left, editor in the center, console at the bottom.

![example](example.png)

```java
Bento bento = new Bento();
bento.placeholderBuilding().setDockablePlaceholderFactory(dockable -> new Label("Empty Dockable"));
bento.placeholderBuilding().setContainerPlaceholderFactory(container -> new Label("Empty Container"));
bento.events().addEventListener(System.out::println);

DockBuilding builder = bento.dockBuilding();
DockContainerBranch branchRoot = builder.root("root");
DockContainerBranch branchWorkspace = builder.branch("workspace");
DockContainerLeaf leafWorkspaceTools = builder.leaf("workspace-tools");
DockContainerLeaf leafWorkspaceHeaders = builder.leaf("workspace-headers");
DockContainerLeaf leafTools = builder.leaf("misc-tools");

// Fixed-size tool panels — should not auto-expand
DockContainerBranch.setResizableWithParent(leafTools, false);
DockContainerBranch.setResizableWithParent(leafWorkspaceTools, false);

// Root: workspace on top, tools on bottom
// Workspace: explorer on left, editor tabs on right
branchRoot.setOrientation(Orientation.VERTICAL);
branchWorkspace.setOrientation(Orientation.HORIZONTAL);
branchRoot.addContainers(branchWorkspace, leafTools);
branchWorkspace.addContainers(leafWorkspaceTools, leafWorkspaceHeaders);

// Align tool headers with application edges for better collapse UX
leafWorkspaceTools.setSide(Side.LEFT);
leafTools.setSide(Side.BOTTOM);

// Tools shouldn't allow splitting (mirroring IntelliJ behavior)
leafWorkspaceTools.setCanSplit(false);
leafTools.setCanSplit(false);

// Primary editor space should not prune when empty
leafWorkspaceHeaders.setPruneWhenEmpty(false);

// Set initial sizes for tool panels
branchRoot.setContainerSizePx(leafTools, 200);
branchRoot.setContainerSizePx(leafWorkspaceTools, 300);

// Collapse the bottom tools panel by default
branchRoot.setContainerCollapsed(leafTools, true);

// Populate leaves with dockable items
leafWorkspaceTools.addDockables(
    buildDockable(builder, 1, 0, "Workspace"),
    buildDockable(builder, 1, 1, "Bookmarks"),
    buildDockable(builder, 1, 2, "Modifications")
);
leafTools.addDockables(
    buildDockable(builder, 2, 0, "Logging"),
    buildDockable(builder, 2, 1, "Terminal"),
    buildDockable(builder, 2, 2, "Problems")
);
leafWorkspaceHeaders.addDockables(
    buildDockable(builder, 0, 0, "Class 1"),
    buildDockable(builder, 0, 1, "Class 2"),
    buildDockable(builder, 0, 2, "Class 3"),
    buildDockable(builder, 0, 3, "Class 4"),
    buildDockable(builder, 0, 4, "Class 5")
);

Scene scene = new Scene(branchRoot);
scene.getStylesheets().add("/bento.css");
stage.setScene(scene);
stage.setOnHidden(e -> System.exit(0));
stage.show();
```
