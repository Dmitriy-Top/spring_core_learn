package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrii_Topolnik on 7/4/2017.
 */
@Component
public class CacheFileEventLogger extends FileEventLogger implements EventLogger {
    @Value("3")
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
    @PreDestroy
    public void destroy() throws IOException {
        if (cache.size()>0){
            writeEventsFromeCache();
        }
    }

    public CacheFileEventLogger(){};

    @PostConstruct
    public void init(){
        this.cache = new ArrayList<Event>();
    }
}
