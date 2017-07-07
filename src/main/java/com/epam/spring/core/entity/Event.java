package com.epam.spring.core.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dmitrii_Topolnik on 6/29/2017.
 */
@Component
@Scope("prototype")
public class Event {
    private Integer id;
    private String msg;
    @Autowired
    @Qualifier("newDate")
    private Date date;
    @Autowired
    private DateFormat df;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date) {
        this.id = (int) Math.round(Math.random()*100);
        this.date = date;
    }
    public Event(Date date,DateFormat df) {
        this.id = (int) Math.round(Math.random()*100);
        this.date = date;
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
    public Event(){
    }

    @PostConstruct
    void init(){
        if (id==null){
            id = (int) Math.round(Math.random()*100);
        }
    }

}
