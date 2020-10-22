package com.epam.koretskyi.commission.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 22.10.2020.
 */
public class VerifyCaptchaTest {

    @Test
    public void verifyTestShouldReturnFalseIfNull() {
        Assert.assertFalse(VerifyCaptcha.verify(null));
    }

    @Test
    public void verifyTestShouldReturnFalse() {
        String fakeRecaptchaResponse = "fakeString";
        Assert.assertFalse(VerifyCaptcha.verify(fakeRecaptchaResponse));
    }
}