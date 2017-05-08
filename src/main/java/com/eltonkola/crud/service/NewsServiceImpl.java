package com.eltonkola.radioz.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import com.eltonkola.radioz.domain.NewsArticle;
import com.eltonkola.radioz.repository.NewsArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NewsServiceImpl implements NewsServiceInterface{

    @Autowired
    protected NewsArticlesRepository mNewsArticlesRepository;

    @Override
    public NewsArticle saveArticle(NewsArticle article) {
        return mNewsArticlesRepository.save(article);
    }

    @Override
    public Boolean deleteArticle(Long id) {
        NewsArticle temp = mNewsArticlesRepository.findOne(id);
        if(temp!=null){
            mNewsArticlesRepository.delete(temp);
            return true;
        }
        return false;
    }

    @Override
    public NewsArticle editArticle(NewsArticle article) {
        return mNewsArticlesRepository.save(article);
    }


    @Override
    public Collection<NewsArticle> getAllArticles() {
        List<NewsArticle> allNews = makeList(mNewsArticlesRepository.findAll());
        Collections.reverse(allNews);
        return allNews;
    }


    @Override
    public NewsArticle findArticleById(Long id) {
        NewsArticle result = mNewsArticlesRepository.findOne(id);
        return result;
    }

    public static List<NewsArticle> makeList(Iterable<NewsArticle> iter) {
        List<NewsArticle> list = new ArrayList<>();
        for (NewsArticle item : iter) {
            list.add(item);
        }
        return list;
    }


}
