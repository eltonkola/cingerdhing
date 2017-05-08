package com.eltonkola.radioz.controllers;

import com.eltonkola.radioz.domain.NewsArticle;
import com.eltonkola.radioz.liquidsoap.TelnetClient;
import com.eltonkola.radioz.service.NewsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by elton on 4/7/17.
 */

@Controller
@EnableAutoConfiguration
@ComponentScan
public class AdminNewsController {

    @Autowired
    NewsServiceInterface mNewsService;

    //@GetMapping("/admin/news")
    @RequestMapping(value = {"/admin/news"}, method = RequestMethod.GET)
    public String news(Model model) {
        model.addAttribute("allNews", mNewsService.getAllArticles());
        return "admin/news";
    }


    @RequestMapping(value = {"/admin/news/create"}, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("newsArticle", new NewsArticle());
        return "admin/news_create";
    }


    @RequestMapping(value = {"/admin/news/do_create"}, method = RequestMethod.POST)
    public String saveNewsArticle(@ModelAttribute("NewsArticle") NewsArticle newsArticle, final RedirectAttributes redirectAttributes) {

        if(mNewsService.saveArticle(newsArticle)!=null) {
            redirectAttributes.addFlashAttribute("saveNewsArticle", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveNewsArticle", "unsuccess");
        }

        return "redirect:/admin/news";
    }


    @RequestMapping(value = "/admin/news/{operation}/{newsId}", method = RequestMethod.GET)
    public String editRemoveNewsArticle(@PathVariable("operation") String operation,
                                        @PathVariable("newsId") Long newsId,
                                        final RedirectAttributes redirectAttributes,
                                        Model model) {
        if(operation.equals("delete")) {
            if(mNewsService.deleteArticle(newsId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if(operation.equals("edit")){
            NewsArticle editNewsArticle = mNewsService.findArticleById(newsId);
            if(editNewsArticle!=null) {
                model.addAttribute("editNewsArticle", editNewsArticle);
                return "/admin/news_edit";
            } else {
                redirectAttributes.addFlashAttribute("status","notfound");
            }
        }

        return "redirect:/admin/news";
    }

    @RequestMapping(value = "/admin/news/update", method = RequestMethod.POST)
    public String updateNewsArticle(@ModelAttribute("editNewsArticle") NewsArticle editNewsArticle,
                                 final RedirectAttributes redirectAttributes) {
        if(mNewsService.editArticle(editNewsArticle)!=null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/admin/news";
    }


}
