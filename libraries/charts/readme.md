# Charts

A library for scientific charts in JavaFX.

The library mainly contains a collection of different charts which you either won't find in the standard JavaFX charts
or comes in a different flavour. Most of the charts are implemented by making use of the JavaFX Canvas node, this means
there is NO support for CSS using this charts and mouse interaction is more tricky as when using the SceneGraph. You can
define 2 X-Axis and 2 Y-Axis with a different scaling. In addition you can overlay a grid which sometimes can be handy.

## Supported Chart Types

* ScatterChart
* LineChart (Normal and Smoothed)
* AreaChart (Normal and Smoothed)
* BarChart
* BubbleChart
* DonutChart
* RadarChart (Polygon, SmoothPolygon and Sector)
* HorizonChart (Normal and Smoothed)
* MatrixHeatmap (A heatmap based on a simple XY Matrix)
* LineDelta (A chart with 2 line charts where the delta between them is visualized, Normal and Smoothed)
* NestedBarChart (A combination of a grouped and stacked bar chart)
* Heatmap (A pixel based heatmap that can be used as an image overlay)
* CircularPlot
* CoxcombChart
* SankeyPlot
* SunburstChart
