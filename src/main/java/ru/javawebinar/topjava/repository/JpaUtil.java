package ru.javawebinar.topjava.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Alexei Valchuk, 01.03.2023, email: a.valchukav@gmail.com
 */

public class JpaUtil {

    @PersistenceContext
    private EntityManager entityManager;

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) entityManager.getDelegate();
        SessionFactory sf = s.getSessionFactory();
        sf.getCache().evictAllRegions();
    }
}
