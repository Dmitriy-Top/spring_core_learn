package com.epam.spring.core.entity.ContextConfig;

import com.epam.spring.core.entity.typeLists.EventType;
import com.epam.spring.core.utils.CombinedEventLogger;
import com.epam.spring.core.utils.ConsoleEventLogger;
import com.epam.spring.core.utils.EventLogger;
import com.epam.spring.core.utils.FileEventLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Dmitrii_Topolnik on 7/6/2017.
 */
@Configuration
public class LoggersConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Resource(name = "consoleEventLogger")
    private ConsoleEventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private FileEventLogger fileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Resource(name = "cacheFileEventLogger")
    private EventLogger cacheEventLogger;

    @Bean
    public Collection<EventLogger> combinedLoggers(){
        Collection<EventLogger> logers = new ArrayList<EventLogger>();
        logers.add(consoleEventLogger);
        logers.add(fileEventLogger);
        return logers;
    }

    @Bean
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger();
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new EnumMap<>(EventType.class);
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combinedEventLogger);
        return map;
    }

    @Bean
        public EventLogger defaultLogger(){
        return cacheEventLogger;
    }
}
