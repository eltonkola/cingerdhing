package com.eltonkola.crud.domain;

import javax.persistence.*;

@Entity
@Table(name = "arkivi_mp3")
public class Song {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "url_song", nullable = false)
    private String url_song;

    @Column(name = "song_title", nullable = false)
    private String song_title;

    @Column(name = "artist_name", nullable = false)
    private String artist_name;


    public Song() {}

    public Song(String url_song, String song_title, String artist_name) {
        this.url_song = url_song;
        this.song_title = song_title;
        this.artist_name=artist_name;
    }

    @Override
    public String toString() {
        return String.format(
                "Burim[id=%d, title='%s', url='%s']",
                id, url_song, song_title);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl_song() {
        return url_song;
    }

    public void setUrl_song(String url_song) {
        this.url_song = url_song;
    }

    public String getSong_title() {
        return song_title;
    }

    public void setSong_title(String song_title) {
        this.song_title = song_title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}
