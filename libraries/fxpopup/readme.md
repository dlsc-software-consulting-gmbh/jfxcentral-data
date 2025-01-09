# FxPopup

FxPopup is a JavaFX library that simplifies the creation of automatic forms
and popup messages with minimal effort. With just a single line of code, developers
can generate dynamic forms or display messages, while maintaining the flexibility to
use custom views for both functionalities. The same library includes a light and dark style.

![](fxpopup.png)

## Example Code

Some example code for the different use-cases.

### Message Popup

![Screenshot of code and message popup](https://github.com/user-attachments/assets/d8bd3c96-ac0b-4818-b2c7-df24deecbbfe)

```java
// Full Message.
Message exampleMessage = new Message(
   "Title",
   "Context",
   MessageType. SUCCESS, // Message.INFO, Message.WARNING, Message.ERROR
   10 //Duration seconds 
)
```

### Message Form

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