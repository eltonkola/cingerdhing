package com.eltonkola.crud.repository;

import com.eltonkola.crud.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {

    //List<Song> findByTitle(String title);

}