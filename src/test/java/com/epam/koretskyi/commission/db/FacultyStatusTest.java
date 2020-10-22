package com.epam.koretskyi.commission.db;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 22.10.2020.
 */
public class FacultyStatusTest {

    @Test
    public void getNameTestShouldReturnOpened() {
        String expected = "opened";
        String actual = FacultyStatus.OPENED.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTestShouldReturnFinalized() {
        String expected = "finalized";
        String actual = FacultyStatus.FINALIZED.getName();
        Assert.assertEquals(expected, actual);
    }
}