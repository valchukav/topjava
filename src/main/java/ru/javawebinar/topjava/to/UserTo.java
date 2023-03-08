package ru.javawebinar.topjava.to;

import lombok.*;

/**
 * @author Alexei Valchuk, 08.03.2023, email: a.valchukav@gmail.com
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTo {

    private Integer id;

    private String name;

    private String email;

    @ToString.Exclude
    private String password;

    public boolean isNew() {
        return id == null;
    }
}
