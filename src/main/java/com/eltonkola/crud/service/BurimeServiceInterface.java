package com.eltonkola.crud.service;


import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.NewsArticle;

import java.util.Collection;

public interface BurimeServiceInterface {

    public Burim saveBurim(Burim burim);
    public Boolean deleteBurim(Long id);
    public Burim editBurim(Burim burim);
    public Burim findBurimById(Long id);
    public Collection<Burim> getAllBurimet();

}
