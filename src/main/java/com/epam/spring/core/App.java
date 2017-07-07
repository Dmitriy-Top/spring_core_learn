package com.epam.spring.core;

import com.epam.spring.core.entity.Client;
import com.epam.spring.core.entity.ContextConfig.AppConfig;
import com.epam.spring.core.entity.ContextConfig.LoggersConfig;
import com.epam.spring.core.entity.Event;
import com.epam.spring.core.entity.typeLists.EventType;
import com.epam.spring.core.utils.CacheFileEventLogger;
import com.epam.spring.core.utils.ConsoleEventLogger;
import com.epam.spring.core.utils.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Dmitrii_Topolnik on 6/28/2017.
 */
@Service
public class App {
    @Autowired
    private Client client;
    @Resource(name="defaultLogger")
    private EventLogger eventLogger;
    @Resource(name="loggerMap")
    private Map<EventType, EventLogger> loggers;

    public void logEvent(Event msg, EventType type) throws IOException {
        String newMessage = msg.getMsg().replaceAll(client.getId(), client.getFullname());
        msg.setMsg(newMessage);
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        logger.logEvent(msg);
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggersConfig.class);
        ctx.scan("com.epam.spring.core");
        ctx.refresh();
        App app = (App) ctx.getBean("app");
        Event event_1 = (Event) ctx.getBean("event");
        Event event_2 = (Event) ctx.getBean("event");
        Event event_3 = (Event) ctx.getBean("event");
        event_1.setMsg("Some event for user 1");
        event_2.setMsg("Some event for user 2");
        event_3.setMsg("Some event for user 3");
        app.logEvent(event_1, null);
        app.logEvent(event_2, EventType.INFO);
        app.logEvent(event_3, EventType.ERROR);


    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public App(Client client, Map<EventType, EventLogger> loggers, EventLogger eventLogger) {
        this.client = client;
        this.loggers = loggers;
        this.eventLogger = eventLogger;
    }
    public App(){

    };
}
