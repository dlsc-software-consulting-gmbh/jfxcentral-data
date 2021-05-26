The following section shows you how to quickly setup a JavaFX application that will show a complete calendar user interface. It includes a day view, a week view, a month view, a year view, an agenda view, a calendar selection view, and a search UI.

```
package com.calendarfx.app;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarApp extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {

            CalendarView calendarView = new CalendarView(); (1)

                Calendar birthdays = new Calendar("Birthdays"); (2)
                Calendar holidays = new Calendar("Holidays");

                birthdays.setStyle(Style.STYLE1); (3)
                holidays.setStyle(Style.STYLE2);

                CalendarSource myCalendarSource = new CalendarSource("My Calendars"); (4)
                myCalendarSource.getCalendars().addAll(birthdays, holidays);

                calendarView.getCalendarSources().addAll(myCalendarSource); (5)

                calendarView.setRequestedTime(LocalTime.now());

                Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                        @Override
                        public void run() {
                                while (true) {
                                        Platform.runLater(() -> {
                                                calendarView.setToday(LocalDate.now());
                                                calendarView.setTime(LocalTime.now());
                                        });

                                        try {
                                                // update every 10 seconds
                                                sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }

                                }
                        };
                };

                updateTimeThread.setPriority(Thread.MIN_PRIORITY);
                updateTimeThread.setDaemon(true);
                updateTimeThread.start();

                Scene scene = new Scene(calendarView);
                primaryStage.setTitle("Calendar");
                primaryStage.setScene(scene);
                primaryStage.setWidth(1300);
                primaryStage.setHeight(1000);
                primaryStage.centerOnScreen();
                primaryStage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }
}
```

1. Create the calendar view
2. Create one or more calendars
3. Set a style on each calendar (entries will use different colors)
4. Create a calendar source (e.g. "Google") and add calendars to it
5. Add calendars to the view