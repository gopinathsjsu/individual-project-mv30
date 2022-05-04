package com.cmpe_202.mv30.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogService {

    private final String path;

    private Boolean isFirst;

    public LogService( String path) {
        this.path = path;
        this.isFirst = true;
    }

    public void log( String headers, List<String> logs) throws IOException {
        FileWriter fileWriter;
        if(isFirst) {
            isFirst = false;
            fileWriter = new FileWriter(path);
            fileWriter.write(headers+"\n");
        } else {
            fileWriter = new FileWriter(path, true);
        }
        for(String log: logs) {
            fileWriter.write(log+"\n");
        }
        fileWriter.close();
    }

}
