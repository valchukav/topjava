package ru.javawebinar.topjava.web.json;

import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealTestData;

import java.util.List;

import static ru.javawebinar.topjava.util.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.util.MealTestData.MEALS;

/**
 * @author Alexei Valchuk, 05.03.2023, email: a.valchukav@gmail.com
 */

public class JsonUtilTest {

    private final MealTestData mealData = new MealTestData();

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(ADMIN_MEAL1);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        mealData.assertMatch(meal, ADMIN_MEAL1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        mealData.assertMatch(meals, MEALS);
    }
}
