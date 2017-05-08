package com.eltonkola.radioz.service;


import com.eltonkola.radioz.domain.NewsArticle;

import java.util.Collection;

public interface NewsServiceInterface {

    public NewsArticle saveArticle(NewsArticle article);
    public Boolean deleteArticle(Long id);
    public NewsArticle editArticle(NewsArticle article);
    public NewsArticle findArticleById(Long id);
    public Collection<NewsArticle> getAllArticles();

}
