package com.epam.koretskyi.commission.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 16.10.2020.
 */
public class MD5UtilTest {

    @Test
    public void md5Apachetest() {
        String expected = "e16b2ab8d12314bf4efbd6203906ea6c";
        String actual = MD5Util.md5Apache("testpassword");
        Assert.assertEquals(expected, actual);
    }

}