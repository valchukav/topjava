package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractJpaUserServiceTest;

/**
 * @author Alexei Valchuk, 27.02.2023, email: a.valchukav@gmail.com
 */

@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends AbstractJpaUserServiceTest {
}
