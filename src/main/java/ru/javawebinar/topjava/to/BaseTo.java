package ru.javawebinar.topjava.to;

import lombok.*;
import ru.javawebinar.topjava.HasId;

/**
 * @author Alexei Valchuk, 08.03.2023, email: a.valchukav@gmail.com
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseTo implements HasId {

    protected Integer id;
}
