package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Dmitrii_Topolnik on 7/4/2017.
 */
public class CombinedEventLogger implements EventLogger{
    private Collection<EventLogger> loggers;

    public void logEvent(Event msg) throws IOException {
        Iterator itr = loggers.iterator();
        while (itr.hasNext()){
        EventLogger logger = (EventLogger) itr.next();
        logger.logEvent(msg);
      }
    }

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }
}
