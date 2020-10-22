package com.epam.koretskyi.commission.db;

import com.epam.koretskyi.commission.db.entity.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 22.10.2020.
 */
public class RoleTest {

    @Test
    public void getRoleTestShouldReturnUser() {
        User user = new User();
        user.setRoleId(0);
        Role expected = Role.USER;
        Role actual = Role.getRole(user);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRoleTestShouldReturnAdmin() {
        User user = new User();
        user.setRoleId(1);
        Role expected = Role.ADMIN;
        Role actual = Role.getRole(user);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTestShouldReturnUser() {
        String expected = "user";
        String actual = Role.USER.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTestShouldReturnAdmin() {
        String expected = "admin";
        String actual = Role.ADMIN.getName();
        Assert.assertEquals(expected, actual);
    }
}