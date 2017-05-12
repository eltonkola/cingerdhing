package com.eltonkola.crud.controllers;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.NewsArticle;
import com.eltonkola.crud.service.BurimeServiceInterface;
import com.eltonkola.crud.service.NewsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by elton on 4/7/17.
 */

@Controller
@EnableAutoConfiguration
@ComponentScan
public class AdminBurimeController {

    @Autowired
    BurimeServiceInterface mBurimService;

    @RequestMapping(value = {"/admin/burime"}, method = RequestMethod.GET)
    public String news(Model model) {
        model.addAttribute("tereBurimet", mBurimService.getAllBurimet());
        return "admin/burime";
    }


    @RequestMapping(value = {"/admin/burime/create"}, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("burimiIri", new Burim());
        return "admin/burim_create";
    }

    @RequestMapping(value = {"/admin/burime/run/{burimiId}"}, method = RequestMethod.GET)
    public String runSpider(final RedirectAttributes redirectAttributes,
                            Model model,
                            @PathVariable("burimiId") Long burimiId) {
        //model.addAttribute("burimiIri", new Burim());
        Burim editBurim = mBurimService.findBurimById(burimiId);
        if(editBurim!=null) {
            model.addAttribute("editBurim", editBurim);
            return "/admin/burime_run";
        } else {
            redirectAttributes.addFlashAttribute("status","notfound");
        }
        return "admin/burime";
    }




    @RequestMapping(value = {"/admin/burime/do_create"}, method = RequestMethod.POST)
    public String do_krijo(@ModelAttribute("Burim") Burim burimiIri, final RedirectAttributes redirectAttributes) {

        if(mBurimService.saveBurim(burimiIri)!=null) {
            redirectAttributes.addFlashAttribute("saveBurimi", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveBurimi", "unsuccess");
        }

        return "redirect:/admin/burime";
    }

    @RequestMapping(value = "/admin/burime/{operation}/{burimiId}", method = RequestMethod.GET)
    public String editDeleteBurim(@PathVariable("operation") String operation,
                                        @PathVariable("burimiId") Long burimiId,
                                        final RedirectAttributes redirectAttributes,
                                        Model model) {
        if(operation.equals("delete")) {
            if(mBurimService.deleteBurim(burimiId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if(operation.equals("edit")){
            Burim editBurim = mBurimService.findBurimById(burimiId);
            if(editBurim!=null) {
                model.addAttribute("editBurim", editBurim);
                return "/admin/burime_edit";
            } else {
                redirectAttributes.addFlashAttribute("status","notfound");
            }
        }

        return "redirect:/admin/burime";
    }


    @RequestMapping(value = "/admin/burime/update", method = RequestMethod.POST)
    public String do_update_burim(@ModelAttribute("editBurim") Burim editBurim,
                                 final RedirectAttributes redirectAttributes) {
        if(mBurimService.editBurim(editBurim)!=null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/admin/burime";
    }

}
