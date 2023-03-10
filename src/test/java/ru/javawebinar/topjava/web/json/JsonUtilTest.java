package ru.javawebinar.topjava.web.json;

import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.MealTestData;
import ru.javawebinar.topjava.util.UserTestData;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testWriteOnlyAccess() {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}
