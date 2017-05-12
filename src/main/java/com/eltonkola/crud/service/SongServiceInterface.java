package com.eltonkola.crud.service;


import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.Song;

import java.util.Collection;

public interface SongServiceInterface {

    public Song saveSong(Song song);
    public Boolean deleteSong(Long id);
    public Song editSong(Song song);
    public Song findSongById(Long id);
    public Collection<Song> getAllSongs();

}
