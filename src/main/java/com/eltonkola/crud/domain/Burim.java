package com.eltonkola.crud.domain;
import com.eltonkola.crud.utils.Utils;

import javax.persistence.*;

@Entity
@Table(name = "burimet_mp3")
public class Burim {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url_burimi", nullable = false)
    private String url_burimi;

    @Column(name = "mp3_domain", nullable = false)
    private String mp3_domain;

    @Column(name = "save_after_stop")
    private boolean saveAfterStop;

    @Column(name = "user_agent", nullable = false)
    private String user_agent;

    @Column(name = "referrer", nullable = false)
    private String referrer;


    @Column(name = "timeout", nullable = false)
    private int timeout;

    public Burim() {
        saveAfterStop= false;
        user_agent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        referrer = "http://www.google.com";
        timeout = 30000;
    }

    public Burim(String title, String url_burimi, String mp3_domain) {
        this.title = title;
        this.url_burimi = url_burimi;
        this.mp3_domain=mp3_domain;
        saveAfterStop= false;
        user_agent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        referrer = "http://www.google.com";
        timeout = 30000;
    }

    public boolean isSaveAfterStop() {
        return saveAfterStop;
    }

    public void setSaveAfterStop(boolean saveAfterStop) {
        this.saveAfterStop = saveAfterStop;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return String.format(
                "Burim[id=%d, title='%s', url='%s']",
                id, title, url_burimi);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_burimi() {
        return url_burimi;
    }

    public void setUrl_burimi(String url_burimi) {
        this.url_burimi = url_burimi;
    }

    public String getMp3_domain() {
        return mp3_domain;
    }

    public void setMp3_domain(String mp3_domain) {
        this.mp3_domain = mp3_domain;
    }
}
