package com.epam.spring.core;

import com.epam.spring.core.aspects.LoggingAspect;
import com.epam.spring.core.aspects.StatisticAspect;
import com.epam.spring.core.entity.Client;
import com.epam.spring.core.entity.Event;
import com.epam.spring.core.entity.typeLists.EventType;
import com.epam.spring.core.utils.CacheFileEventLogger;
import com.epam.spring.core.utils.ConsoleEventLogger;
import com.epam.spring.core.utils.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Dmitrii_Topolnik on 6/28/2017.
 */
public class App {
    private Client client;
    private EventLogger eventLogger;
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
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        StatisticAspect sa = (StatisticAspect) ctx.getBean("statisticAspect");
        Event event_1 = (Event) ctx.getBean("event");
        Event event_2 = (Event) ctx.getBean("event");
        Event event_3 = (Event) ctx.getBean("event");
        Event event_4 = (Event) ctx.getBean("event");
        Event event_5 = (Event) ctx.getBean("event");
        event_1.setMsg("Some event for user 1");
        event_2.setMsg("Some event for user 2");
        event_3.setMsg("Some event for user 3");
        event_4.setMsg("Some event for user 4");
        event_5.setMsg("Some event for user 5");
        app.logEvent(event_1, null);
        app.logEvent(event_2, EventType.INFO);
        app.logEvent(event_3, EventType.ERROR);
        app.logEvent(event_4, EventType.ERROR);
        app.logEvent(event_5, EventType.ERROR);
        System.out.println(sa.getCounter().toString());


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
}
