package com.eltonkola.radioz.repository;

import java.util.List;

import com.eltonkola.radioz.domain.NewsArticle;
import org.springframework.data.repository.CrudRepository;

public interface NewsArticlesRepository extends CrudRepository<NewsArticle, Long> {

    List<NewsArticle> findByTitle(String title);

}