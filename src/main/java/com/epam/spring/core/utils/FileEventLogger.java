package com.epam.spring.core.utils;

import com.epam.spring.core.entity.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dmitrii_Topolnik on 7/3/2017.
 */
public class FileEventLogger implements EventLogger{
    private String filename;
    private File file;
    public void logEvent(Event msg) throws IOException {

        FileUtils.writeStringToFile(file,msg.getMsg()+"\n",true);
    }
    public void init() throws IOException {
        if (!file.canWrite()){
            throw new IOException();
        }

    }

    public FileEventLogger(String filename) {
        this.filename = filename;
        this.file = new File(this.filename);
    }
}
