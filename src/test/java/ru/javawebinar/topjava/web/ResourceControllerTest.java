package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alexei Valchuk, 06.03.2023, email: a.valchukav@gmail.com
 */

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void testResources() throws Exception {
        mockMvc.perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("text/css;charset=UTF-8")))
                .andExpect(status().isOk());
    }
}
