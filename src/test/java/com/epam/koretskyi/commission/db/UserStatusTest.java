package com.epam.koretskyi.commission.db;

import com.epam.koretskyi.commission.db.entity.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 22.10.2020.
 */
public class UserStatusTest {

    @Test
    public void getStatusTestShouldReturnUnblocked() {
        User user = new User();
        user.setStatusId(0);
        UserStatus expected = UserStatus.UNBLOCKED;
        UserStatus actual = UserStatus.getStatus(user);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStatusTestShouldReturnBlocked() {
        User user = new User();
        user.setStatusId(1);
        UserStatus expected = UserStatus.BLOCKED;
        UserStatus actual = UserStatus.getStatus(user);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTestShouldReturnUnblocked() {
        String expected = "unblocked";
        String actual = UserStatus.UNBLOCKED.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTestShouldReturnBlocked() {
        String expected = "blocked";
        String actual = UserStatus.BLOCKED.getName();
        Assert.assertEquals(expected, actual);
    }
}