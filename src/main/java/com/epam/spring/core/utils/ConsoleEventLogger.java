package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * Created by Dmitrii_Topolnik on 6/28/2017.
 */
@Component
public class ConsoleEventLogger implements EventLogger{
    public void logEvent(Event msg){
        System.out.println(msg.toString());
    }
    public ConsoleEventLogger consoleEventLogger(){
        return new ConsoleEventLogger();
    }
}
