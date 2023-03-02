package ru.javawebinar.topjava.service.datajpa;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractJpaUserServiceTest;
import ru.javawebinar.topjava.util.MealTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.util.UserTestData.ADMIN;
import static ru.javawebinar.topjava.util.UserTestData.ADMIN_ID;

/**
 * @author Alexei Valchuk, 27.02.2023, email: a.valchukav@gmail.com
 */

@ActiveProfiles(Profiles.DATAJPA)
class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {

    @Test
    public void getWithMeals() {
        User admin = service.getWithMeals(ADMIN_ID);
        testData.assertMatch(admin, ADMIN);
        new MealTestData("user").assertMatch(admin.getMeals(), MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1);
    }

    @Test
    public void getWithMealsNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getWithMeals(1));
    }
}
