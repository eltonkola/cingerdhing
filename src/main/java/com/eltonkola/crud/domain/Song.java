package com.eltonkola.crud.domain;

import javax.persistence.*;

@Entity
@Table(name = "arkivi_mp3", indexes = {@Index(name = "song_index", columnList = "id, songhash")})
public class Song {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "urlsong", nullable = false, length = 100000)
    @Lob
    private String urlsong;

    @Column(name = "songtitle", nullable = true)
    private String songtitle;

    @Column(name = "artistname", nullable = true)
    private String artistname;

    @Column(name = "songhash", nullable = false)
    private int songhash;


    public Song() {}

    public Song(String url) {
        urlsong = url;

        String tmp = url.substring(url.lastIndexOf("/", url.lastIndexOf(".")));
        if(tmp !=null) {

            String[] vals = tmp.split("-");
            if (vals.length > 0) {
                artistname = vals[0];
            }

            if (vals.length > 1) {
                songtitle = vals[1];
            }

        }

        songhash = urlsong.hashCode();

    }

    public Song(String url_song, String song_title, String artist_name) {
        this.urlsong = url_song;
        this.songtitle = song_title;
        this.artistname=artist_name;
        this.songhash = urlsong.hashCode();
    }

    @Override
    public String toString() {
        return String.format(
                "Burim[id=%d, title='%s', url='%s']",
                id, urlsong, songtitle);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlsong() {
        return urlsong;
    }

    public void setUrlsong(String urlsong) {
        this.urlsong = urlsong;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public int getSonghash() {
        songhash = urlsong.hashCode();
         return songhash;
    }

    public void setSonghash(int songhash) {
        this.songhash = urlsong.hashCode();
    }
}
