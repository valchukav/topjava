package ru.javawebinar.topjava;

import javax.validation.groups.Default;

/**
 * @author Alexei Valchuk, 09.03.2023, email: a.valchukav@gmail.com
 */

public class View {

    // Validate only form UI/REST
    public interface Web extends Default {}

    // Validate only when DB save/update
    public interface Persist extends Default {}
}
