package ru.javawebinar.topjava.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.util.UserTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.util.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.util.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.util.UserTestData.ADMIN_ID;

/**
 * @author Alexei Valchuk, 27.02.2023, email: a.valchukav@gmail.com
 */

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void getWithUser() {
        Meal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        testData.assertMatch(adminMeal, ADMIN_MEAL1);
        new UserTestData("registered", "roles", "meals").assertMatch(adminMeal.getUser(), UserTestData.ADMIN);
    }

    @Test
    public void getWithUserNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getWithUser(1, ADMIN_ID));
    }
}
