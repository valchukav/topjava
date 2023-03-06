package ru.javawebinar.topjava;

import org.springframework.lang.Nullable;
import org.springframework.test.context.ActiveProfilesResolver;

/**
 * @author Alexei Valchuk, 03.03.2023, email: a.valchukav@gmail.com
 */

public class AllActiveProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(@Nullable Class<?> testClass) {
        return new String[]{Profiles.REPOSITORY_IMPLEMENTATION, Profiles.getActiveDbProfile()};
    }
}
