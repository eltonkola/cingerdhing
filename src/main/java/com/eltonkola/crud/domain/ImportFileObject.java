package com.eltonkola.crud.domain;

import com.eltonkola.crud.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by elton on 5/19/17.
 */
public class ImportFileObject {

    private String name;
    private String path;
    private String createdionDate;
    private String size;

    public ImportFileObject(File file){
        if(file == null){
            return;
        }
        name =  file.getName();
        path =  file.getPath();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
        Date dt = new Date(file.lastModified());
        createdionDate = sdf.format(dt);
        size = Utils.readableFileSize(file.length());
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreatedionDate() {
        return createdionDate;
    }

    public void setCreatedionDate(String createdionDate) {
        this.createdionDate = createdionDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
