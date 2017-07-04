package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;

/**
 * Created by Dmitrii_Topolnik on 6/28/2017.
 */
public class ConsoleEventLogger implements EventLogger{
    public void logEvent(Event msg){
        System.out.println(msg.toString());
    }
}
