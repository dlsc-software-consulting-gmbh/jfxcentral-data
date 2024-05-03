# FXForm2

Automatic JavaFX form generation

FXForm2 is a library providing automatic JavaFX form generation.

## How does it work?

![Screen 1](fxform2.png)

## Features

Main features include:

- Automatic form generation and binding to bean properties
- CSS support
- Bean Validation handling (JSR 303)
- Fields reordering and filtering
- Tooltips
- Localization
- Custom FXML skins

## Generate your first form

````java
MyBean myBean = new MyBean(); // get an instance of the bean to be edited
Node fxForm = new FXForm(myBean);  // create the FXForm node for your bean
root.getChildren().add(fxForm);  // add it to your scene graph
````

That's it!