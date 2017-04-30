package com.gradbuck.springsecurity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by alex on 25.01.17.
 */
@Controller
public class MainController {

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error",
            required = false) String error,
            @RequestParam(value = "logout",
                    required = false) String logout,
            Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @RequestMapping("/")
    public String getMainPage() {

        return "index";
    }
}
