package by.pvt.main;

import by.pvt.cmd.SendMessageCmd;
import by.pvt.component.EmailSender;
import by.pvt.service.MessageService;
import by.pvt.service.MessageType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("by.pvt")
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        System.out.println(context.getBeanDefinitionCount());
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        MessageService messageService = (MessageService) context.getBean("messageService");
        System.out.println(messageService);

        messageService.executeCommand(
                new SendMessageCmd(
                        "user@mail.ru",
                        MessageType.CANCEL_MESSAGE,
                        EmailSender.EMAIL_CHANNEL
                )
        );

    }

}
