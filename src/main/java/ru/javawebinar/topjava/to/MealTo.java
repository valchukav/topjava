package ru.javawebinar.topjava.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"dateTime", "calories"}, callSuper = true)
public class MealTo extends BaseTo{

    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final AtomicBoolean excess;

    public MealTo() {
        this(null, LocalDateTime.now(), "", 0, new AtomicBoolean(false));
    }

    @ConstructorProperties({"id", "dateTime", "description", "calories", "excess"})
    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, AtomicBoolean excess) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }
}
