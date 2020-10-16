package com.epam.koretskyi.commission.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

/**
 * Helper for sending mail to list of users with attachment.
 *
 * @author SANJAY
 * @see for list of mail servers and configuration details: https://sanjaymadnani.wordpress.com/
 */
public class CommunicationHelper {

    /**
     * USER_ID String email-Id for sending email.
     */
    private static final String USER_ID = "pet.project.email@gmail.com";

    /**
     * USER_PASSWORD String email-Password for sending email.
     */
    private static final String USER_PASSWORD = "0105110519022101";

    /**
     * Creates SMTP Session required for sending mail.
     *
     * @return Session.
     */
    private static Session getSendMailSession() {
        // Configurable Properties (as per Mail server).
        // SMTP_HOST ex: smtp.gmail.com
        final String SMTP_HOST = "smtp.gmail.com";
        // SMTP_PORT ex: 465 (gmail with SSL authentication), 587 (gmail with StartTLS authentication).
        final String SMTP_PORT = "465";
        // IS_AUTHENTICATION_REQUIRED for most of the mail server authentication is required.
        final String IS_AUTHENTICATION_REQUIRED = "true";
        // IS_STARTTLS_ENABLE ex: gmail & outlook mail can be send via startTLS authentication.
        // all servers don't use startTLS authentication like yahoo mail server don't have this authentication.
        final String IS_STARTTLS_ENABLE = "false";
        // IS_SSL_ENABLE ex: gmail mail can be send either by SSL or by startTLS.
        // For yahoo mail server it is compulsory to use SSL authentication for sending mail.
        final String IS_SSL_ENABLE = "true";

        final String IS_DEBUG = "false";

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.auth", IS_AUTHENTICATION_REQUIRED);
        props.put("mail.debug", IS_DEBUG);
        props.put("mail.smtp.ssl.enable", IS_SSL_ENABLE);
        props.put("mail.smtp.starttls.enable", IS_STARTTLS_ENABLE);
        props.put("mail.smtp.port", SMTP_PORT);
        if (IS_STARTTLS_ENABLE.equals("false") && IS_SSL_ENABLE.equals("true")) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_ID, USER_PASSWORD);
            }
        });
    }


    /**
     * Creates a message for email notification about admission to the university.
     *
     * @param form   form of education
     * @param facultyName   name of faculty
     * @return
     */
    public static String createMail(String form, String facultyName) {
        return "<font size=\"5\"><p>Dear candidate,</p><p>Im writing to inform You, that You accepted on <strong>" + form +
                "</strong> form of education to the Krtskiy University successfully.</p><p>You are enrolled to " + facultyName +
                ".</p><p>You will get all information about the course in a next letter in a month</p></font>";
    }


    /**
     * Sends Mail to list of users.
     * layer Authentication. Simple mail Transfer Protocol is used.
     *
     * @param toList   List of email-Ids.
     * @param msgBody   String to deliver.
     * @throws MessagingException
     */
    public static boolean sendMail(List<String> toList, String msgBody) throws MessagingException {
        // Creates MimeMessage by SMTP Session.
        final MimeMessage message = new MimeMessage(getSendMailSession());
        message.setFrom(new InternetAddress(USER_ID));
        final Address[] toAddress = new InternetAddress[toList.size()];
        for (int i = 0; i < toAddress.length; i++) {
            toAddress[i] = new InternetAddress(toList.get(i));
        }
        // sets the to list for sending mail.
        message.addRecipients(Message.RecipientType.TO, toAddress);
        final Multipart multipart = new MimeMultipart();

        message.setSubject("Congratulations on your admission to Krtsky University!");
        message.setSentDate(new java.util.Date());
        final BodyPart messageBodyPart1 = new MimeBodyPart();
        // Sends the message in html format.
        messageBodyPart1.setContent(msgBody, "text/html");
        multipart.addBodyPart(messageBodyPart1);

        message.setContent(multipart);
        Transport.send(message);
        return true;
    }
}