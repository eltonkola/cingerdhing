package com.eltonkola.crud.repository;

import java.util.List;

import com.eltonkola.crud.domain.NewsArticle;
import org.springframework.data.repository.CrudRepository;

public interface NewsArticlesRepository extends CrudRepository<NewsArticle, Long> {

    List<NewsArticle> findByTitle(String title);

}