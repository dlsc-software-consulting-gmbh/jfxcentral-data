## Effect Designer Tips

### 1. Application Scenario
Whether you're designing UI elements with CSS or Java, adding visual effects is key to enhancing depth. Our Effect Designer tool simplifies this process, providing a user-friendly interface where users can visually create multiple effects by dragging sliders and clicking buttons, seeing changes in real-time. It supports all JavaFX effects, allowing for the export of inner and outer shadows as either CSS or Java code, while other effects can be exported as Java code.

- **CSS Example:** 
```
-fx-effect: dropshadow(three-pass-box, #486fff, 30.773, 0.0, 0.0, 0.0);
```

- **Java Example:** 
```
DropShadow effect0 = new DropShadow();
effect0.setBlurType(BlurType.THREE_PASS_BOX);
effect0.setWidth(62.546);
effect0.setHeight(62.546);
effect0.setRadius(30.773);
effect0.setColor(new Color(0.2804, 0.4363, 1.0, 1.0));
effect0.setOffsetX(0);
effect0.setOffsetY(0);
effect0.setSpread(0);
```

### 2. Dual Code Output
Our tool is designed with flexibility in mind, allowing for the output of code snippets in both CSS and Java to streamline the transition from design to development. Always ensure you select the appropriate code format for your project's requirements.

### 3. Gratitude
A special thank you to [SceneBuilder](https://github.com/gluonhq/scenebuilder) for providing visual references and inspiration.