package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Alexei Valchuk, 02.03.2023, email: a.valchukav@gmail.com
 */

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:meals";
    }

    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/meals")
    public String getMeals() {
        return "meals";
    }
}
