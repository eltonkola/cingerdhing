package com.eltonkola.crud.service;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.repository.BurimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class BurimeServiceImpl implements BurimeServiceInterface{

    @Autowired
    protected BurimRepository mBurimRepository;

    @Override
    public Burim saveBurim(Burim burim) {
        return mBurimRepository.save(burim);
    }

    @Override
    public Boolean deleteBurim(Long id) {
        Burim temp = mBurimRepository.findOne(id);
        if(temp!=null){
            mBurimRepository.delete(temp);
            return true;
        }
        return false;
    }

    @Override
    public Burim editBurim(Burim burim) {
        return mBurimRepository.save(burim);
    }

    @Override
    public Burim findBurimById(Long id) {
        Burim result = mBurimRepository.findOne(id);
        return result;
    }

    @Override
    public Collection<Burim> getAllBurimet() {
        List<Burim> allNews = makeList(mBurimRepository.findAll());
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
