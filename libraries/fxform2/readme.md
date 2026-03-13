# FXForm2

Automatic JavaFX form generation from bean properties.

![Screen 1](fxform2.png)

## Features

- Automatic form generation and binding to bean properties
- CSS support and custom FXML skins
- Bean Validation (JSR 303)
- Fields reordering, filtering, tooltips, and localization

## Usage

```java
MyBean myBean = new MyBean();
Node fxForm = new FXForm(myBean);
root.getChildren().add(fxForm);
```