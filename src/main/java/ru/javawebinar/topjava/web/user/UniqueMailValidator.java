package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.HasEmail;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.web.ExceptionInfoHandler;

import java.util.Objects;

/**
 * @author Alexei Valchuk, 11.03.2023, email: a.valchukav@gmail.com
 */

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    private final UserRepository repository;

    @Autowired
    public UniqueMailValidator(@Qualifier("dataJpaUserRepository") UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(@Nullable Class<?> clazz) {
        return HasEmail.class.isAssignableFrom(Objects.requireNonNull(clazz));
    }

    @Override
    public void validate(@Nullable Object target, @Nullable Errors errors) {
        HasEmail user = ((HasEmail) target);
        User dbUser = repository.getByEmail(Objects.requireNonNull(user).getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            Objects.requireNonNull(errors).rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
        }
    }
}
