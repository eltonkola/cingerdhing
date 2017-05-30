package com.eltonkola.crud.repository;

import com.eltonkola.crud.domain.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends
        //ElasticsearchRepository<Song, Long>{
        CrudRepository<Song, Long> {

    List<Song> findBysonghash(int hash);
    Page<Song> findAll(Pageable pageable);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<Song> findSong(String name, Pageable pageable);

    @Query("Select s from Song s where s.emri_full like %:emri_full%")
    List<Song> kerko(@Param("emri_full") String emri_full);

}