package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

/**
 * @author Alexei Valchuk, 02.03.2023, email: a.valchukav@gmail.com
 */

public abstract class AbstractJpaUserServiceTest extends AbstractUserServiceTest{

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
