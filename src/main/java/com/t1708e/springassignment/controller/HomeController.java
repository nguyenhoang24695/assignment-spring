package com.t1708e.springassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.Entity;

@Controller
@RequestMapping
public class HomeController {
    @RequestMapping(method = RequestMethod.GET,value = "index")
    public String index(){
        return "index";
    }
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "login")
    public String login(){
        return "login";
    }
}
