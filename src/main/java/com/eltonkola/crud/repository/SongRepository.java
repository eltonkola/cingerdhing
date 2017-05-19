package com.eltonkola.crud.repository;

import com.eltonkola.crud.domain.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findBysonghash(int hash);
    Page<Song> findAll(Pageable pageable);

}