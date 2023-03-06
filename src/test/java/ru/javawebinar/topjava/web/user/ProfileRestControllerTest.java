package ru.javawebinar.topjava.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.AbstractTestData;
import ru.javawebinar.topjava.util.UserTestData;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.util.UserTestData.*;
import static ru.javawebinar.topjava.web.user.ProfileRestController.REST_URL;

/**
 * @author Alexei Valchuk, 05.03.2023, email: a.valchukav@gmail.com
 */

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    private final AbstractTestData<User> userData = new UserTestData("registered", "meals");

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(userData, USER));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent());
        userData.assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        userData.assertMatch(userService.get(USER_ID), updated);
    }
}
