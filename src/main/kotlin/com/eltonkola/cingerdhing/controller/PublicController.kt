package com.eltonkola.cingerdhing.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PublicController(){


    @RequestMapping("/", "/home", "/index")
    internal fun index(model : Model): String {
        return "index"
    }

    @RequestMapping("/hello")
    internal fun hello(model : Model): String {
        return "hello"
    }

    @RequestMapping("/login")
    internal fun login(model : Model): String {
        return "login"
    }

}