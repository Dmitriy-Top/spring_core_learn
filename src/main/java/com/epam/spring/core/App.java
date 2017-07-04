package com.epam.spring.core;

import com.epam.spring.core.entity.Client;
import com.epam.spring.core.entity.Event;
import com.epam.spring.core.utils.ConsoleEventLogger;
import com.epam.spring.core.utils.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Dmitrii_Topolnik on 6/28/2017.
 */
public class App {
    private Client client;
    private EventLogger eventLogger;

    public void logEvent(Event msg) throws IOException {
        String newMessage = msg.getMsg().replaceAll(client.getId(),client.getFullname());
        msg.setMsg(newMessage);
        eventLogger.logEvent(msg);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)ctx.getBean("app");
        Event event_1 = (Event) ctx.getBean("event");
        event_1.setMsg("Some event for user 1");
        app.logEvent(event_1);
        Event event_2 = (Event) ctx.getBean("event");
        event_2.setMsg("Some event for user 2");
        app.logEvent(event_2);
        Event event_3 = (Event) ctx.getBean("event");
        event_3.setMsg("Some event for user 3");
        app.logEvent(event_3);
        Event event_4 = (Event) ctx.getBean("event");
        event_4.setMsg("Some event for user 4");
        app.logEvent(event_4);
        ctx.close();

    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

}
