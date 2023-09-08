*A glucose status monitor for Nightscout implemented in JavaFX.*

*GlucoStatusFX* is optimized for Macos but will also run on Windows and Linux. On Macos it will look like a native app, react on dark/light mode changes and also supports the
accent color one chooses in the System settings.
The plan is to also create a version that looks like a native Windows app (but that's not started yet due to the lack of time).

On Macos you will see the current blood glucose value and the current trend, fetched from your Nightscout server in the system tray. On Windows you will only see the application
icon in the system tray and on Linux there is no guaranteed support of the system tray which leads to a not really optimal user experience (needs more love).

On the main window you will find the main value in the center (either in mg/dl or in mmol/l depending on your settings) with an arrow that indicates the current trend.
The background of the upper area will be colored depending on the current blood glucose value. When enabled in the settings you will get notifications depending on the
settings you defined for the different blood glucose levels. The notifications can also come with a sound if you enabled it.
In the lower part of the main window you see a chart showing the blood glucose values in the range you have selected on the top button bar (e.g. 7 days, 72 hours, 48 hours, 24 hours,
12 hours, 6 hours or 3 hours).
The area that you have defined for the normal range (default 70 mg/dl - 140 mg/dl) will be visualized as a green area. The area defined by the acceptable low and acceptable high
values will be visualized by yellow lines. In addition the current average glucose value will be shown as a dotted line in the chart.

In addition you will find 4 icons in the corners of the upper part of the app. The upper left icon will show you a matrix with the average values of the last 30 days colored by
the actual average. If you click on a circle it will show the average value for the selected day for 3 seconds and then jump back to the date.
The icon in the lower left corner will show you a pattern based on the values of the last week. The line in the chart shows the aggregated values for each hour of the day of all
values from last week. This can give you a hint about common patterns (e.g. high values in the morning etc.). In the upper part of the pattern chart it will also show you the
HbAc1 values based on the values of the last 30 days.
Clicking the icon on the lower right corner will open a time in range view for the currently selected range. It will give you an idea on how much time you've spend in which 
glucose level which is a commonly used indicator.

To use this app you need to have a nightscout server which url you have to put in the
text field in the settings dialog.
In the settings dialog you can will find the following parameters:

**"Unit mg/dl"**
Whatever unit you have defined on your nightscout server you can change the display to either mg/dl or mmol/l. In case you would like to see the values in mg/dl, please enable the switch "Unit mg/dl". If you disable this switch the values will be shown in mmol/l.

**"Low value notification"**
Enable the switch if you would like to receive notifications for low values (in GlucoStatusFX acceptable low means values between min acceptable and 55 mg/dl which is min critical).

**"Acceptable low value notification"**
Enable the switch if you would like to receive notifications for acceptable low values (in GlucoStatusFX acceptable low means values between min normal and min acceptable values).

**"Acceptable high value notification"**
Enable the switch if you would like to receive notifications for acceptable high values (in GlucoStatusFX acceptable high means values between max normal and max acceptable values).

**"High value notification"**
Enable the switch if you would like to receive notifications for high values (in GlucoStatusFX high means values between max acceptable and 350 mg/dl which is max critical).

**"Too low notification interval"**
You can define the interval for notifications of too low values in a range of 5-10 minutes. In case of too low values you will receive notifications in the given interval.

**"Too high notification interval"**
You can define the interval for notifications of too high values in a range of 5-30 minutes. In case of too high values you will receive notifications in the given interval.

**"Min acceptable"**
The min acceptable value can be defined in the range of 60-70 mg/dl.

**"Min normal"**
The min normal value can be defined in the range of 70-80 mg/dl.

**"Max normal"**
The max normal value can be defined in the range of 120-160 mg/dl.

**"Max acceptable"**
The max acceptable value can be defined in the range of 120-250 mg/dl.

