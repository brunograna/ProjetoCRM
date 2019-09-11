package br.senac.rj.crm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Authentication authentication){
        System.out.println("Username: "+authentication.getName());
        System.out.println("Authorities: "+authentication.getAuthorities());
        return new ModelAndView("/auth/dashboard");
    }
}
