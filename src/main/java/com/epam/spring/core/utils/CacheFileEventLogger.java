package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrii_Topolnik on 7/4/2017.
 */
public class CacheFileEventLogger extends FileEventLogger implements EventLogger {
    private Integer cacheSize;
    private List<Event> cache;

    public void logEvent(Event msg) throws IOException {
        cache.add(msg);
        if (cache.size()==cacheSize){
            writeEventsFromeCache();
            cache.clear();
        }
    }

    private void writeEventsFromeCache() throws IOException {
        for (int i = 0; i < cache.size();i++){
            super.logEvent(cache.get(i));
        }
    }

    public CacheFileEventLogger(Integer cacheSize,String filename) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>();
    }
    public void destroy() throws IOException {
        if (cache.size()>0){
            writeEventsFromeCache();
        }
    }
}
