package ru.javawebinar.topjava;

import org.springframework.lang.NonNull;
import org.springframework.test.context.ActiveProfilesResolver;

/**
 * @author Alexei Valchuk, 21.02.2023, email: a.valchukav@gmail.com
 */

public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(@NonNull Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}
