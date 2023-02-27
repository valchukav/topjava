package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.AbstractTestData;
import ru.javawebinar.topjava.util.TimingExtension;
import ru.javawebinar.topjava.util.UserTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.util.UserTestData.*;

/**
 * @author Alexei Valchuk, 14.02.2023, email: a.valchukav@gmail.com
 */

@ExtendWith(SpringExtension.class)
@ExtendWith(TimingExtension.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceTest {

    @Autowired
    private UserService service;

    private final AbstractTestData<User> testData = new UserTestData("registered", "roles");

    @Test
    void getAll() {
        testData.assertMatch(service.getAll(), ADMIN, USER);
    }

    @Test
    void get() {
        testData.assertMatch(service.get(USER_ID), USER);
        testData.assertMatch(service.get(ADMIN_ID), ADMIN);
    }

    @Test
    void create() {
        User newUser = new User(null, "DUMMY", "DUMMY", "DUMMY", Role.ROLE_USER);
        User created = service.create(newUser);
        Integer newId = created.getId();
        newUser.setId(newId);
        testData.assertMatch(created, newUser);
        testData.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class,
                () -> service.create(
                        new User(null, "Duplicate", USER.getEmail(), "newPass", Role.ROLE_USER))
        );
    }

    @Test
    void delete() {
        service.delete(ADMIN_ID);
        testData.assertMatch(service.getAll(), USER);
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotFoundException.class, () -> service.delete(Integer.MAX_VALUE));
    }

    @Test
    void update() {
        User updated = new User(ADMIN);
        String email = "DUMMY";
        updated.setEmail(email);
        service.update(updated);
        testData.assertMatch(service.get(ADMIN_ID), updated);
    }

    @Test
    void updateNotExist() {
        assertThrows(NotFoundException.class, () -> service.update(Mockito.mock(User.class)));
    }

    @Test
    void getByMail() {
        testData.assertMatch(service.getByEmail(USER.getEmail()), USER);
    }

    @Test
    void getByMailNotExist() {
        assertThrows(NotFoundException.class, () -> service.getByEmail("DUMMY"));
    }
}