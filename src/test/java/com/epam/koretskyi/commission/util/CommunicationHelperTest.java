package com.epam.koretskyi.commission.util;

import org.junit.Test;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 16.10.2020.
 */
public class CommunicationHelperTest {

    @Test
    public void sendMailTest() throws MessagingException {
        List<String> toList = new ArrayList<>();
        toList.add("koretskyi.denys@gmail.com");
        String msgTitle = "Title of test email.";
        String msgBody = "Body of test email.";
        assertTrue(CommunicationHelper.sendMail(toList, msgTitle, msgBody));
    }
}