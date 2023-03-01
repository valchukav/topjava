package ru.javawebinar.topjava.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * @author Alexei Valchuk, 06.02.2023, email: a.valchukav@gmail.com
 */

@WebServlet(ServletUrls.MEALS)
public class MealServlet extends HttpServlet {

    private MealRestController mealController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        mealController = springContext.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        Meal meal = new Meal(
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories"))
        );

        if (req.getParameter("id").isEmpty()) {
            mealController.create(meal);
        } else {
            mealController.update(meal, getId(req));
        }

        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(req);
                mealController.delete(id);
                resp.sendRedirect("meals");
            }
            case "create", "update" -> {
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/WEB-INF/jsp/mealForm.jsp").forward(req, resp);
            }
            case "filter" -> {
                LocalDate startDate = parseLocalDate(req.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(req.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(req.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(req.getParameter("endTime"));
                req.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
                req.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(req, resp);
            }
            case "all" -> {
                req.setAttribute("meals", mealController.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(req, resp);
            }
        }
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
