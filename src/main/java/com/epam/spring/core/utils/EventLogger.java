package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;

/**
 * Created by Dmitrii_Topolnik on 6/29/2017.
 */
public interface EventLogger {
    public void logEvent(Event msg);
}
