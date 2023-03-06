package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.AbstractTestData;
import ru.javawebinar.topjava.util.MealTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.util.MealTestData.*;
import static ru.javawebinar.topjava.util.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.util.UserTestData.USER_ID;

/**
 * @author Alexei Valchuk, 15.02.2023, email: a.valchukav@gmail.com
 */

public abstract class AbstractMealServiceTest extends AbstractServiceTest{

    @Autowired
    protected MealService service;

    protected final AbstractTestData<Meal> testData = new MealTestData("user");

    @Test
    public void delete() {
        service.delete(MEAL1_ID, USER_ID);
        testData.assertMatch(service.getAll(USER_ID), MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1, USER_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL1_ID, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        Integer newId = created.getId();
        newMeal.setId(newId);
        testData.assertMatch(created, newMeal);
        testData.assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void get() {
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        testData.assertMatch(actual, ADMIN_MEAL1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1, USER_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, ADMIN_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        testData.assertMatch(service.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(MEAL1, ADMIN_ID));
    }

    @Test
    public void getAll() {
        testData.assertMatch(service.getAll(USER_ID), MEALS);
    }

    @Test
    public void getBetween() {
        testData.assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getBetweenWithNullDates() {
        testData.assertMatch(service.getBetweenDates(null, null, USER_ID), MEALS);
    }
}