package ru.javawebinar.topjava.repository;

import org.springframework.lang.Nullable;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;

/**
 * @author Alexei Valchuk, 06.02.2023, email: a.valchukav@gmail.com
 */

public interface MealRepository {

    Meal save (Meal meal, int userId);

    boolean delete (int id, int userId);

    Meal get (int id, int userId);

    List<Meal> getAll(int userId);

    default List<Meal> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return getBetweenInclusive(getStartInclusive(startDate), getEndExclusive(endDate), userId);
    }

    List<Meal> getBetweenInclusive(@Nullable LocalDateTime startDate, @Nullable LocalDateTime endDate, int userId);

    default Meal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
