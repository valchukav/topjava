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
public class UserTo extends BaseTo{
    
    private String name;

    private String email;

    @ToString.Exclude
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
