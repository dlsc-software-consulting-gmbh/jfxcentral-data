The following code is the demo code that is included in the library. It should give you a good idea on 
how to create UOM input fields.

```java
package com.dlsc.unitfx.demo;

import com.dlsc.unitfx.QuantityInputField;
import com.dlsc.unitfx.util.Units;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Temperature;

public class DemoApp extends Application {

    @Override
    public void start(Stage stage) {
        QuantityInputField<Length> lengthField = new QuantityInputField<>();
        lengthField.getAvailableUnits().addAll(Units.getInstance().getUnits(Length.class));
        lengthField.setBaseUnit(Units.getInstance().getUnit(Length.class));
        lengthField.setAutoFixValue(true);

        QuantityInputField<Angle> angleField = new QuantityInputField<>();
        angleField.getAvailableUnits().addAll(Units.getInstance().getUnits(Angle.class));
        angleField.setBaseUnit(Units.getInstance().getUnit(Angle.class));
        angleField.setAutoFixValue(true);

        QuantityInputField<Speed> speedField = new QuantityInputField<>();
        speedField.getAvailableUnits().addAll(Units.getInstance().getUnits(Speed.class));
        speedField.setBaseUnit(Units.getInstance().getUnit(Speed.class));
        speedField.setAutoFixValue(true);

        QuantityInputField<Temperature> temperatureField = new QuantityInputField<>();
        temperatureField.getAvailableUnits().addAll(Units.getInstance().getUnits(Temperature.class));
        temperatureField.setBaseUnit(Units.getInstance().getUnit(Temperature.class));
        temperatureField.setAutoFixValue(true);

        QuantityInputField<Mass> massField = new QuantityInputField<>();
        massField.getAvailableUnits().addAll(Units.getInstance().getUnits(Mass.class));
        massField.setBaseUnit(Units.getInstance().getUnit(Mass.class));
        massField.setAutoFixValue(true);


        VBox vbox = new VBox();
        vbox.setPrefWidth(250);
        vbox.setSpacing(20);
        vbox.setFillWidth(true);
        vbox.setStyle("-fx-padding: 20px;");

        vbox.getChildren().add(wrap("Length Field", lengthField));
        vbox.getChildren().add(wrap("Angle Field", angleField));
        vbox.getChildren().add(wrap("Speed Field", speedField));
        vbox.getChildren().add(wrap("Temperature Field", temperatureField));
        vbox.getChildren().add(wrap("Mass Field", massField));

        Scene scene = new Scene(vbox);
        stage.setTitle("UnitFX Demo");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();
    }

    private Node wrap(String title, Node node) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px;");
        VBox box = new VBox(titleLabel, node);
        box.setStyle("-fx-background-color: black, white; -fx-background-insets: 0, 1; -fx-padding: 10px; -fx-spacing: 20;");
        return box;
    }

    public static void main(String[] args) {
        launch();
    }
}
```