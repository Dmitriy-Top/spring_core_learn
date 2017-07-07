package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dmitrii_Topolnik on 7/3/2017.
 */
@Component
public class FileEventLogger implements EventLogger{
    @Value("log.txt")
    private String filename;
    private File file;
    public void logEvent(Event msg) throws IOException {

        FileUtils.writeStringToFile(file,msg.toString()+"\n",true);
    }
    @PostConstruct
    public void init() throws IOException {
        file = new File(filename);
        if (!file.canWrite()){
            throw new IOException();
        }

    }

    public FileEventLogger(String filename) {
        this.filename = filename;
        this.file = new File(this.filename);
    }
    public FileEventLogger(){

    }
    public FileEventLogger fileEventLogger(){
        return new FileEventLogger();
    }

}
