# ChartFx

ChartFx is a scientific charting library developed at [GSI](https://www.gsi.de) for [FAIR](https://fair-center.eu/), 
optimised for real-time data visualisation at 25 Hz update rates. It handles data sets ranging from tens of thousands 
to millions of points, commonly used in digital signal processing applications.
ChartFx is a re-engineered version of JavaFX's default [Chart](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/chart/Chart.html) implementation, designed to maintain the rich 
features and extensibility of earlier Swing-based libraries while addressing performance bottlenecks and API issues. 
The motivation for the re-design has been presented at [IPAC'19](https://ipac19.org/) ([paper](docs/THPRB028.pdf), [poster](docs/THPRB028_poster.pdf)).
For a recent presentation, watch the [JFX Days](https://www.jfx-days.com/) talk [here](https://youtu.be/NK4pgRF9XWk).

For more information and performance data please visit ChartFx's [project site](https://github.com/fair-acc/chart-fx). 


### Minimal Working Example:

<img src="docs/pics/SimpleChartSample.png" width=800 alt="simple ChartFx example"/>

The corresponding source code `ChartFxSample.java`

```Java
package com.example.chartfx;

import io.fair_acc.chartfx.XYChart;
import io.fair_acc.chartfx.axes.spi.DefaultNumericAxis;
import io.fair_acc.dataset.spi.DoubleDataSet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleChartSample extends Application {
    private static final int N_SAMPLES = 100;

    @Override
    public void start(final Stage primaryStage) {
        final StackPane root = new StackPane();

        final XYChart chart = new XYChart(new DefaultNumericAxis(), new DefaultNumericAxis());
        root.getChildren().add(chart);

        final DoubleDataSet dataSet1 = new DoubleDataSet("data set #1");
        final DoubleDataSet dataSet2 = new DoubleDataSet("data set #2");
        // lineChartPlot.getDatasets().add(dataSet1); // for single data set
        chart.getDatasets().addAll(dataSet1, dataSet2); // two data sets

        final double[] xValues = new double[N_SAMPLES];
        final double[] yValues1 = new double[N_SAMPLES];
        final double[] yValues2 = new double[N_SAMPLES];
        for (int n = 0; n < N_SAMPLES; n++) {
            xValues[n] = n;
            yValues1[n] = Math.cos(Math.toRadians(10.0 * n));
            yValues2[n] = Math.sin(Math.toRadians(10.0 * n));
        }
        dataSet1.set(xValues, yValues1);
        dataSet2.set(xValues, yValues2);

        final Scene scene = new Scene(root, 800, 600);
    }
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
```

### More Interactive Visual Examples

The chart-fx samples submodulesubmodule contains many examples illustrating the library's capabilities
(see also screenshots). To try them out, run:

```bash
gh repo clone fair-acc/chart-fx
cd chart-fx

mvn compile install
mvn exec:java
# for the math samples can be started by running:
mvn exec:java@math
# or other DataSet and accelerator-related sub-modules:
mvn exec:java@dataset
mvn exec:java@acc-ui
```

### Other Recommended JavaFX Charting Libraries

 * [Gerrit Grunwald](https://github.com/HanSolo)'s [Charts](https://github.com/HanSolo/charts)
 * [JFreeChart-FX](https://github.com/jfree/jfreechart-fx)
 * [Michael Ennen](https://github.com/brcolow)'s [CandleFX](https://github.com/brcolow/candlefx) (financial charts)