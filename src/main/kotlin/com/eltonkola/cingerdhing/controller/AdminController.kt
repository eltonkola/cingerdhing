package com.eltonkola.cingerdhing.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AdminController(){


    @RequestMapping("/admin/")
    internal fun index(model : Model): String {
        return "admin/index"
    }

}