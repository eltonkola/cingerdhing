package com.eltonkola.crud.spider.msg;

import java.util.Date;

/**
 * Created by elton on 5/17/17.
 */
public class TaskStatus {


    public enum EVENT_TYPE{
        ADDED_PAGE, FOUND_MP3, ERROR_SCANNING_PAGE, TASK_ENDED, TASK_STOPED, TASK_STARTED, TASK_READY
    }

    private Date started_time;
    private Date current_time;
    private String time_elapsed;

    private String name;
    private boolean running;

    private int pages;
    private int songs;
    private int errors;
    private EVENT_TYPE mType;
    private String value;

    public TaskStatus(){

    }

    public EVENT_TYPE getType() {
        return mType;
    }

    public void setType(EVENT_TYPE type) {
        this.mType = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getStarted_time() {
        return started_time;
    }

    public void setStarted_time(Date started_time) {
        this.started_time = started_time;
    }

    public Date getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Date current_time) {
        this.current_time = current_time;
    }

    public String getTime_elapsed() {
        return time_elapsed;
    }

    public void setTime_elapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSongs() {
        return songs;
    }

    public void setSongs(int songs) {
        this.songs = songs;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

}
