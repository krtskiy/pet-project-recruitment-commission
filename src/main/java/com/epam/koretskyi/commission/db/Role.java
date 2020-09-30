package com.epam.koretskyi.commission.db;

import com.epam.koretskyi.commission.db.entity.User;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public enum Role {
    USER, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
