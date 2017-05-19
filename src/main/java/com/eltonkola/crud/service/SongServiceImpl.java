package com.eltonkola.crud.service;

import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class SongServiceImpl implements SongServiceInterface {

    @Autowired
    protected SongRepository mSongRepository;

    @Override
    public Song saveSong(Song song) {
        return mSongRepository.save(song);
    }

    @Override
    public Boolean deleteSong(Long id) {
        Song temp = mSongRepository.findOne(id);
        if(temp!=null){
            mSongRepository.delete(temp);
            return true;
        }
        return false;
    }

    @Override
    public Song editSong(Song song) {
        return mSongRepository.save(song);
    }

    @Override
    public Song findSongById(Long id) {
        Song result = mSongRepository.findOne(id);
        return result;
    }

    @Override
    public boolean songExistWithPath(String url) {
        return  mSongRepository.findBysonghash(url.hashCode()).size() > 0;
    }

    @Override
    public Collection<Song> getAllSongs() {
        List<Song> allNews = makeList(mSongRepository.findAll());
        return allNews;
    }

    @Override
    public int nrTotalSongs() {
        return (int) mSongRepository.count();
    }


    public static List<Song> makeList(Iterable<Song> iter) {
        List<Song> list = new ArrayList<>();
        for (Song item : iter) {
            list.add(item);
        }
        return list;
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return mSongRepository.findAll(pageable);
    }



}
