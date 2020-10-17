package com.epam.koretskyi.commission.db;

import com.epam.koretskyi.commission.db.entity.User;

/**
 * User account lock status.
 *
 * @author D.Koretskyi on 22.09.2020.
 */
public enum UserStatus {
    UNBLOCKED, BLOCKED;

    public static UserStatus getStatus(User user) {
        int statusId = user.getStatusId();
        return UserStatus.values()[statusId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
