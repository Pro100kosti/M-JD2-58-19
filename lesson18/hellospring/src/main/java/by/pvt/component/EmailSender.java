package by.pvt.component;

import by.pvt.pojo.Message;
import org.springframework.stereotype.Component;

public interface EmailSender {

    public static final String EMAIL_CHANNEL = "EMAIL";

    void send(Message message);

}
