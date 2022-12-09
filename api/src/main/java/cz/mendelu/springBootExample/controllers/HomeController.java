package cz.mendelu.springBootExample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class HomeController {
    /**
     * start web page
     * @return home page
     */
    @GetMapping("/")
    public String Home() {
        return "person/person";
    }
}
