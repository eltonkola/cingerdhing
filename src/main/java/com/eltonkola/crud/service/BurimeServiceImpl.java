package com.eltonkola.crud.service;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.NewsArticle;
import com.eltonkola.crud.repository.BurimArticlesRepository;
import com.eltonkola.crud.repository.NewsArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BurimeServiceImpl implements BurimeServiceInterface{

    @Autowired
    protected BurimArticlesRepository mBurimArticlesRepository;

    @Override
    public Burim saveBurim(Burim burim) {
        return mBurimArticlesRepository.save(burim);
    }

    @Override
    public Boolean deleteBurim(Long id) {
        Burim temp = mBurimArticlesRepository.findOne(id);
        if(temp!=null){
            mBurimArticlesRepository.delete(temp);
            return true;
        }
        return false;
    }

    @Override
    public Burim editBurim(Burim burim) {
        return mBurimArticlesRepository.save(burim);
    }

    @Override
    public Burim findBurimById(Long id) {
        Burim result = mBurimArticlesRepository.findOne(id);
        return result;
    }

    @Override
    public Collection<Burim> getAllBurimet() {
        List<Burim> allNews = makeList(mBurimArticlesRepository.findAll());
        return allNews;
    }


    public static List<Burim> makeList(Iterable<Burim> iter) {
        List<Burim> list = new ArrayList<>();
        for (Burim item : iter) {
            list.add(item);
        }
        return list;
    }

}
