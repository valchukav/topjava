package ru.javawebinar.topjava.service.datajpa;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

/**
 * @author Alexei Valchuk, 27.02.2023, email: a.valchukav@gmail.com
 */

@ActiveProfiles(Profiles.DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
