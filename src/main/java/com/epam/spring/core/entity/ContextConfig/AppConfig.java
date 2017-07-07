package com.epam.spring.core.entity.ContextConfig;

import com.epam.spring.core.entity.Client;
import com.epam.spring.core.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Dmitrii_Topolnik on 7/6/2017.
 */
@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public Date newDate(){
        return new Date();
    }
    @Bean
    public DateFormat dateFarmat(){
        return DateFormat.getDateInstance();
    }
    @Bean
    public Client client(){
        Client client = new Client(environment.getProperty("id"),environment.getProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }
}
