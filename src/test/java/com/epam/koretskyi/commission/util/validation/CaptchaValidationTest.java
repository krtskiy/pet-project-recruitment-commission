package com.epam.koretskyi.commission.util.validation;

import com.epam.koretskyi.commission.util.validation.CaptchaValidation;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author D.Koretskyi on 22.10.2020.
 */
public class CaptchaValidationTest {

    @Test
    public void verifyTestShouldReturnFalseIfNull() {
        Assert.assertFalse(CaptchaValidation.verify(null));
    }

    @Test
    public void verifyTestShouldReturnFalse() {
        String fakeRecaptchaResponse = "fakeString";
        Assert.assertFalse(CaptchaValidation.verify(fakeRecaptchaResponse));
    }
}