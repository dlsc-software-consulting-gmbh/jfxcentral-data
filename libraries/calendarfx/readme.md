A framework for creating sophisticated calendar views based on JavaFX. See the [Developer Manual](https://dlsc-software-consulting-gmbh.github.io/CalendarFX/) for full documentation.

## Usage

Creates a full calendar UI including day, week, month, year, agenda, and search views:

```java
CalendarView calendarView = new CalendarView(); // (1)

Calendar birthdays = new Calendar("Birthdays"); // (2)
birthdays.setStyle(Style.STYLE1); // (3)

Calendar holidays = new Calendar("Holidays");
holidays.setStyle(Style.STYLE2);

CalendarSource myCalendarSource = new CalendarSource("My Calendars"); // (4)
myCalendarSource.getCalendars().addAll(birthdays, holidays);

calendarView.getCalendarSources().addAll(myCalendarSource); // (5)
calendarView.setRequestedTime(LocalTime.now());

// Keep the displayed time current
Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
    @Override
    public void run() {
        while (true) {
            Platform.runLater(() -> {
                calendarView.setToday(LocalDate.now());
                calendarView.setTime(LocalTime.now());
            });
            try { sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
};
updateTimeThread.setPriority(Thread.MIN_PRIORITY);
updateTimeThread.setDaemon(true);
updateTimeThread.start();
```

1. Create the calendar view
2. Create one or more calendars
3. Set a style on each calendar (entries will use different colors)
4. Create a calendar source (e.g. "Google") and add calendars to it
5. Add calendars to the view