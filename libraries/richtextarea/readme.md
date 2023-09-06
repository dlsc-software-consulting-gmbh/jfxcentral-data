# RichTextArea

Gluon presents a new JavaFX control, created with Java and JavaFX standard APIs, called the RichTextArea control. RichTextArea is a text input control which provides rich text features along with emoji, and non-text objects like images, tables and hyperlinks.

## Usage

To use the RichTextArea control in your project add the following dependency:

```xml
<dependency>
    <groupId>com.gluonhq</groupId>
    <artifactId>rich-text-area</artifactId>
    <version>1.1.1</version>
</dependency>
 ```

and then simply create an instance and add it to your JavaFX application:

```java
@Override
public void start(Stage stage) {
    RichTextArea editor = new RichTextArea();
    BorderPane root = new BorderPane(editor);
    Scene scene = new Scene(root, 800, 600);
    stage.setScene(scene);
    stage.show();
}
```