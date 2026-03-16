RichTextArea is a text input control that provides rich text features along with emoji and non-text objects like images,
 tables, and hyperlinks.

## Usage

```java
RichTextArea editor = new RichTextArea();
BorderPane root = new BorderPane(editor);
Scene scene = new Scene(root, 800, 600);
stage.setScene(scene);
stage.show();
```