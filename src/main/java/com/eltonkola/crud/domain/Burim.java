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


    public Burim() {}

    public Burim(String title, String url_burimi, String mp3_domain) {
        this.title = title;
        this.url_burimi = url_burimi;
        this.mp3_domain=mp3_domain;
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
