package com.eltonkola.radioz.controllers;

import com.eltonkola.radioz.RadiozApplication;
import com.eltonkola.radioz.domain.NewsArticle;
import com.eltonkola.radioz.liquidsoap.TelnetClient;
import com.eltonkola.radioz.service.NewsServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by elton on 4/7/17.
 */

@Controller
@EnableAutoConfiguration
@ComponentScan
public class WebController {


    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    NewsServiceInterface mNewsService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("allNews", mNewsService.getAllArticles());
        return "public/blog";
    }

    @GetMapping("/blog/read/{newsId}")
    public String read(@PathVariable("newsId") Long newsId, Model model) {
        NewsArticle newsArticle = mNewsService.findArticleById(newsId);
        if(newsArticle!=null) {
            model.addAttribute("newsArticle", newsArticle);

            log.info("Content: " + newsArticle.getContent() );


            return "public/read";
        }
        return "redirect:/blog";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "public/login";
    }


    @GetMapping("/about")
    public String about(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "public/about";
    }

}
