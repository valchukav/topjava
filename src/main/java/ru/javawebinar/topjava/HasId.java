package ru.javawebinar.topjava;

import org.springframework.util.Assert;

/**
 * @author Alexei Valchuk, 08.03.2023, email: a.valchukav@gmail.com
 */

public interface HasId {

    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
