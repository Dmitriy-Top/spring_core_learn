package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

/**
 * Created by Dmitrii_Topolnik on 8/11/2017.
 */
public class DBLogger implements EventLogger {
    private JdbcTemplate jdbcTemplate;

    public void logEvent(Event msg) throws IOException {
        jdbcTemplate.update("INSERT INTO t_event (id, msg) VALUES (?,?)", msg.getId(), msg.getMsg());
    }

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
