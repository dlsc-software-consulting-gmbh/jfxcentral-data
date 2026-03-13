# FxPopup

A JavaFX library for creating popup messages and auto-generated forms with minimal code. Supports custom views and includes built-in light and dark styles.

![](fxpopup.png)

## Message Popup

![Screenshot of code and message popup](https://github.com/user-attachments/assets/d8bd3c96-ac0b-4818-b2c7-df24deecbbfe)

```java
Message message = new Message(
    "Title",
    "Context",
    MessageType.SUCCESS, // INFO, WARNING, ERROR
    10 // duration in seconds
);
```

## Message Form

![Screenshot of code and message form](https://github.com/user-attachments/assets/b63da29d-6197-4528-9f43-eb33c259c28f)

```java
@MessageForm(name = "User Log", validator = UserLogController.class)
public class UserLog {
    @MessageField(label = "User Name", placeholder = "Write user name...", required = true, icon = FxPopIcon.USER)
    private String userName;
    @MessageField(label = "Password", placeholder = "Write password...", required = true, icon = FxPopIcon.PADLOCK, type = FieldType.PASSWORD)
    private String password;
    @MessageField(placeholder = "Remember me", type = FieldType.CHECK)
    private boolean remember;
}
```