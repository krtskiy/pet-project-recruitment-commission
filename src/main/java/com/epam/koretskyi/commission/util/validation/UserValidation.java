package com.epam.koretskyi.commission.util.validation;

import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.util.MD5Util;
import org.apache.commons.lang3.StringUtils;

/**
 * @author D.Koretskyi on 25.10.2020.
 */
public class UserValidation {

    public static boolean validateUserFields(User user) {
        return StringUtils.isAllBlank(user.getEmail(), user.getPassword(), user.getName(),
                user.getPatronymic(), user.getRegion(), user.getCity(), user.getInstitutionName());
    }

    public static boolean validateUserPassword(String password) {
        return password.length() >= 6 && password.length() <= 32;
    }

    public static boolean isPasswordSame(User user, String newPassword) {
        return user.getPassword().equals(MD5Util.md5Apache(newPassword));
    }
}
