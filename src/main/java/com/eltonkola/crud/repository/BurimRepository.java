package com.eltonkola.crud.repository;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.NewsArticle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BurimRepository extends CrudRepository<Burim, Long> {

    List<Burim> findByTitle(String title);

}