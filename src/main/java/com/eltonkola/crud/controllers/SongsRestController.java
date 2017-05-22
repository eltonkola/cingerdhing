package com.eltonkola.crud.controllers;

import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.service.SongServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by elton on 5/22/17.
 */
@RestController
public class SongsRestController {


    @Autowired
    SongServiceInterface mSongServiceInterface;

    @RequestMapping(value="/songs",method= RequestMethod.GET)
    Page<Song> list(Pageable pageable){
        Page<Song> persons = mSongServiceInterface.findAll((Pageable) pageable);
        return persons;
    }


}
