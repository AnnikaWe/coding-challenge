package com.insure.premium.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Home controller for GUI.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 26.02.2025
 */
@Controller
public class HomeController {
	@GetMapping("/")
    public String home() {
        return "index";  // load templates/index.html
    }
	
}
