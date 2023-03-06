package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.AbstractTestData;
import ru.javawebinar.topjava.util.MealTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.util.MealTestData.*;
import static ru.javawebinar.topjava.util.MealsUtil.createWithExcess;
import static ru.javawebinar.topjava.util.MealsUtil.getWithExcesses;
import static ru.javawebinar.topjava.util.UserTestData.USER;
import static ru.javawebinar.topjava.util.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.TestUtil.readFromJson;
import static ru.javawebinar.topjava.web.TestUtil.readFromJsonMvcResult;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

/**
 * @author Alexei Valchuk, 06.03.2023, email: a.valchukav@gmail.com
 */

public class MealRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MealService mealService;

    private final AbstractTestData<Meal> mealData = new MealTestData("user");

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/" + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> mealData.assertMatch(readFromJsonMvcResult(result, Meal.class), MEAL1));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + "/" + MEAL1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    void update() throws Exception {
        Meal updated = getUpdated();

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + "/" + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        mealData.assertMatch(mealService.get(MEAL1_ID, START_SEQ), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Meal newMeal = MealTestData.getNew();
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)));

        Meal created = readFromJson(action, Meal.class);
        Integer newId = created.getId();
        newMeal.setId(newId);
        mealData.assertMatch(created, newMeal);
        mealData.assertMatch(mealService.get(newId, USER_ID), newMeal);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(getWithExcesses(MEALS, USER.getCaloriesPerDay())));
    }

    @Test
    void filter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/" + "filter?startDate=2015-05-30&startTime=07:00&endDate=2015-05-31&endTime=11:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(createWithExcess(MEAL1, new AtomicBoolean(false)), createWithExcess(MEAL5, new AtomicBoolean(true))));
    }

    @Test
    void filterAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/" + "filter?startDate=&endTime="))
                .andExpect(status().isOk())
                .andExpect(contentJson(getWithExcesses(MEALS, USER.getCaloriesPerDay())));
    }
}
