package com.eltonkola.crud.service;

import com.eltonkola.crud.domain.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface SongServiceInterface {

    public Song saveSong(Song song);
    public Boolean deleteSong(Long id);
    public Song editSong(Song song);
    public Song findSongById(Long id);
    public boolean songExistWithPath(String url);
    public Collection<Song> getAllSongs();

    public int nrTotalSongs();

    public void deleteAllSongs();

    Page<Song> findAll(Pageable pageable);
}
