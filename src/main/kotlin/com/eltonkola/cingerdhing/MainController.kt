package com.eltonkola.cingerdhing

import org.springframework.boot.SpringApplication
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller


@Controller
class DemoApplication {

    @RequestMapping("/")
    @ResponseBody
    internal fun home(): String {
        return "Hello World!"
    }

}