package com.eltonkola.crud.config;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.Role;
import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.domain.User;
import com.eltonkola.crud.repository.BurimRepository;
import com.eltonkola.crud.repository.SongRepository;
import com.eltonkola.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InitialDataLoader {


    private UserRepository mUserRepository;
    private BurimRepository mBurimRepository;
    private SongRepository mSongRepository;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, BurimRepository burimRepository, SongRepository songRepository) {
        this.mUserRepository = userRepository;
        mBurimRepository = burimRepository;
        mSongRepository = songRepository;

        loadDefaultUsers();
        loadDefaultBurimet();

//        loadDefaultSongs();
    }

    private void loadDefaultSongs() {

        for(int i = 0; i<120; i++){
            mSongRepository.save(new Song("http://eltonkola.com/poni - dale dale " + i + ".mp3"));
        }

    }

    private void loadDefaultBurimet(){
        if(mBurimRepository.findByTitle("shkariko.im").size() == 0){
            Burim burim = new Burim();
            burim.setTitle("shkariko.im");
            burim.setMp3_domain("http://shkarko.muzikpapare.com");
            burim.setUrl_burimi("http://www.shkarko.im");

            mBurimRepository.save(burim);
        }
    }

    private void loadDefaultUsers() {

        Optional<User> admin =  mUserRepository.findOneByEmail("admin@localhost.com");
         if(!admin.isPresent()){
            createDefaultAdminAccount();
        }

    }

    private void createDefaultAdminAccount() {


        String password = "admin";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);


        User admin = new User();
        admin.setEmail("admin@localhost.com");
        admin.setPasswordHash(hashedPassword);
        admin.setRole(Role.ADMIN);

        mUserRepository.save(admin);
    }


}
