# PickerFX

PickerFX is a lightweight framework for creating picker (touch) controls. 

![alt](youtube:hGG4I7QIzx0)

## Usage
Currently the only documentation is the source code itself. Please take a look at the DemoApp.java to find out how to use the framework.
```java
import com.dlsc.pickerfx.DurationPicker;
import com.dlsc.pickerfx.IntegerPicker;
import com.dlsc.pickerfx.ItemPicker;
import com.dlsc.pickerfx.LocalDatePicker;
import com.dlsc.pickerfx.LocalDateTimePicker;
import com.dlsc.pickerfx.LocalTimePicker;
import com.dlsc.pickerfx.TimeFormat;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DemoApp extends Application {

    @Override
    public void start(Stage stage) {
        IntegerPicker integerPicker = new IntegerPicker();
        ItemPicker itemPicker = new ItemPicker("A", "B", "C", "D", "E");
        LocalDatePicker localDatePicker = new LocalDatePicker();
        LocalDateTimePicker localDateTimePicker = new LocalDateTimePicker();
        LocalTimePicker localTimePicker = new LocalTimePicker();
        DurationPicker durationPicker = new DurationPicker();

        durationPicker.getFields().setAll(ChronoUnit.DAYS, ChronoUnit.HOURS, ChronoUnit.MINUTES, ChronoUnit.SECONDS, ChronoUnit.MILLIS);
        durationPicker.setMaximumDuration(Duration.ofDays(7));

        localTimePicker.setTimeFormat(TimeFormat.TWELVE_HOURS);

        FlowPane flowPane = new FlowPane();
        flowPane.setPrefWidth(1200);
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setStyle("-fx-padding: 20px;");
        flowPane.getChildren().add(wrap("Integer Picker", integerPicker));
        flowPane.getChildren().add(wrap("Item Picker", itemPicker));
        flowPane.getChildren().add(wrap("Date Picker", localDatePicker));
        flowPane.getChildren().add(wrap("Time Picker", localTimePicker));
        flowPane.getChildren().add(wrap("Date & Time Picker", localDateTimePicker));
        flowPane.getChildren().add(wrap("Duration Picker", durationPicker));

        Scene scene = new Scene(flowPane);
        stage.setTitle("PickerFX Demo");
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