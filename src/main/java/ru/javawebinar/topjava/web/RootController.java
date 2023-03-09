package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexei Valchuk, 02.03.2023, email: a.valchukav@gmail.com
 */

@Controller
public class RootController {

    private final UserService userService;

    private final MealService mealService;

    @Autowired
    public RootController(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setId(userId);
        return "redirect:meals";
    }

    @GetMapping("/meals")
    public String getMeals(Model model) {
        model.addAttribute("meals",
                MealsUtil.getWithExcesses(mealService.getAll(SecurityUtil.getId()), SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }
}
