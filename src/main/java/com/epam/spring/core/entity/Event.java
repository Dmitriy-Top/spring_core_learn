package com.epam.spring.core.entity;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Dmitrii_Topolnik on 6/29/2017.
 */
public class Event {
    private Integer id;
    private String msg;
    private Date date;
    private DateFormat df;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date) {
        this.id = (int) Math.random()*100;
        this.date = date;
    }
    public Event(Date date,DateFormat df) {
        this.id = (int) Math.random()*100;
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
}
