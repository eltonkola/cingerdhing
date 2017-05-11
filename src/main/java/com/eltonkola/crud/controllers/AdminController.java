package com.eltonkola.crud.controllers;

import com.eltonkola.crud.liquidsoap.TelnetClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by elton on 4/7/17.
 */

@Controller
@EnableAutoConfiguration
public class AdminController {


    @RequestMapping("/admin/skip")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    String skip() {

        TelnetClient client = new TelnetClient();
        try {
            client.send(TelnetClient.ICECAST_SKIP);
            return "skiped";
        } catch (Exception e) {
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }

    @RequestMapping("/admin/version")
    @ResponseBody
    String version() {

        TelnetClient client = new TelnetClient();
        try {
            return "response:" + client.getResponse(TelnetClient.VERSION);
        } catch (Exception e) {
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }


    @GetMapping("/admin")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/home";
    }

    @GetMapping("/admin/media")
    public String media(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/media";
    }

    @GetMapping("/admin/playlists")
    public String playlists(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/playlists";
    }

    @GetMapping("/admin/shows")
    public String shows(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/shows";
    }

    @GetMapping("/admin/schedule")
    public String schedule(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/schedule";
    }

//    @GetMapping("/admin/news")
//    public String news(Map<String, Object> model) {
//        model.put("message", "Hello World");
//        model.put("title", "Hello Home");
//        model.put("date", new Date());
//        return "admin/news";
//    }


    @GetMapping("/admin/settings")
    public String settings(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/settings";
    }

    @GetMapping("/admin/account")
    public String account(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "admin/accounts";
    }

}
