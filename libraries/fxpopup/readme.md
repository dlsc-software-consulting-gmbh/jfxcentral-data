<h1 align="center">
FxPopup
</h1>

<p align="center">
  <a href="https://github.com/HugoQuinn2/fxpopup">
    <img src="https://github.com/user-attachments/assets/af66f7e1-55f5-4433-abce-4b37d4e973c9" alt="Logo">
  </a>
</p>

FxPopup is a JavaFX library that simplifies the creation of automatic forms
and popup messages with minimal effort. With just a single line of code, developers
can generate dynamic forms or display messages, while maintaining the flexibility to
use custom views for both functionalities. The same library includes a light and dark style.

For more information and performance visit [FxPopup repo.](https://github.com/HugoQuinn2/fxpopup)

## Messages popup
![image](https://github.com/user-attachments/assets/d8bd3c96-ac0b-4818-b2c7-df24deecbbfe)

```java
// Full Message.
Message exampleMessage = new Message(
   "Title",
   "Context",
   MessageType. SUCCESS, // Message.INFO, Message.WARNING, Message.ERROR
   10 //Duration seconds 
)
```

## Message form
![image](https://github.com/user-attachments/assets/b63da29d-6197-4528-9f43-eb33c259c28f)

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