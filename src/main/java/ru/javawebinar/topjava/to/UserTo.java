package ru.javawebinar.topjava.to;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import ru.javawebinar.topjava.HasEmail;
import ru.javawebinar.topjava.util.UserUtil;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Alexei Valchuk, 08.03.2023, email: a.valchukav@gmail.com
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTo extends BaseTo implements HasEmail, Serializable {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    @SafeHtml // https://stackoverflow.com/questions/17480809
    private String email;

    @NotBlank
    @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
    @ToString.Exclude
    private String password;

    @Range(min = 10, max = 10000)
    @NotNull
    private Integer caloriesPerDay = UserUtil.DEFAULT_CALORIES_PER_DAY;

    public UserTo(Integer id, String name, String email, String password, int caloriesPerDay) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
