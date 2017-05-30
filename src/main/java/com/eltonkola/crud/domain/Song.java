package com.eltonkola.crud.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@Entity
@Table(name = "arkivi_mp3",
        indexes = {@Index(name = "song_index", columnList = "id, songhash")
        })
//@Document(indexName = "emri_full", type="song", shards=1)
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

    @Column(name = "emri_full", nullable = true)
    private String emri_full;


    public Song() {}

    public Song(String url) {
        urlsong = url;

        String tmp = url.substring(url.lastIndexOf("/", url.lastIndexOf(".")));
        if(tmp !=null) {

            emri_full = pastro(tmp);


            String[] vals = tmp.split("-");
            if (vals.length > 0) {
                artistname = pastro(vals[0]);
            }

            if (vals.length > 1) {
                songtitle = pastro(vals[1]);

            }

        }

        songhash = urlsong.hashCode();

    }

    private String pastro(String input){
        String result = input;
        result = result.replaceAll("%20", " ");
        result = result.replaceAll(".MP3", "");
        result = result.replaceAll(".mp3", "");
        return result;
    }


    public Song(String url_song, String song_title, String artist_name) {
        this.urlsong = url_song;
        this.songtitle = song_title;
        this.artistname=artist_name;
        emri_full = songtitle + " " + artistname;
        this.songhash = urlsong.hashCode();
    }

    @Override
    public String toString() {
        return String.format(
                "Burim[id=%d, title='%s', url='%s']",
                id, urlsong, songtitle);
    }

    public String getEmri_full() {
        return emri_full;
    }

    public void setEmri_full(String emri_full) {
        this.emri_full = emri_full;
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
