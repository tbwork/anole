package org.tbwork.anole.gui.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class HomeController {

	 @RequestMapping("/") 
     public ModelAndView home() {
           return new ModelAndView("index.html");
     }
	
}
