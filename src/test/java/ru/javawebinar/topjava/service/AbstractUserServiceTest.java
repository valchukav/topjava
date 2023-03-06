package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.AbstractTestData;
import ru.javawebinar.topjava.util.UserTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.util.UserTestData.*;

/**
 * @author Alexei Valchuk, 14.02.2023, email: a.valchukav@gmail.com
 */

public abstract class AbstractUserServiceTest extends AbstractServiceTest{

    @Autowired
    protected UserService service;

    protected final AbstractTestData<User> testData = new UserTestData("registered", "meals");

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        Objects.requireNonNull(cacheManager.getCache("users")).clear();
    }

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
        User newUser = UserTestData.getNew();
        User created = service.create(new User(newUser));
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
        User updated = UserTestData.getUpdated();
        service.update(new User(updated));
        testData.assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void updateNotExist() {
        assertThrows(Exception.class, () -> service.update(Mockito.mock(User.class)));
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