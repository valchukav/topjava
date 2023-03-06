package ru.javawebinar.topjava.util;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.web.TestUtil.readFromJsonMvcResult;
import static ru.javawebinar.topjava.web.TestUtil.readListFromJsonMvcResult;

/**
 * @author Alexei Valchuk, 15.02.2023, email: a.valchukav@gmail.com
 */

public class UserTestData extends AbstractTestData<User> {

    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public UserTestData(String... fieldsToIgnore) {
        super(fieldsToIgnore);
    }

    public static ResultMatcher contentJson(AbstractTestData<User> testData, User... expected) {
        return result -> testData.assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(AbstractTestData<User> testData, User expected) {
        return result -> testData.assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static User getNew() {
        return new User(null, "DUMMY", "DUMMY", "DUMMY", Role.ROLE_USER);
    }

    public static User getUpdated() {
        User updated = new User(USER);
        String email = "DUMMY";
        updated.setEmail(email);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        updated.setRoles(Collections.singletonList(Role.ROLE_USER));
        return updated;
    }
}
