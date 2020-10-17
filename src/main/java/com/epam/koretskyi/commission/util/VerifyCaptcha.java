package com.epam.koretskyi.commission.util;

import com.epam.koretskyi.commission.exception.Messages;
import org.apache.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author D.Koretskyi on 17.10.2020.
 */
public class VerifyCaptcha {

    private static final Logger LOG = Logger.getLogger(VerifyCaptcha.class);

    private static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6LfrcdgZAAAAANJyG7pjVfJmKYzaK1ERTzgOEiQb";

    public static boolean verify(String gRecaptchaResponse) {
        LOG.trace("Captcha validation starts");
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);

            // open connection with Google
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

            // add the header information to the request
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // data sent to server
            String postParams = "secret=" + SECRET_KEY //
                    + "&response=" + gRecaptchaResponse;

            // send request
            conn.setDoOutput(true);

            // get the output stream of connection
            // write data in to stream (send data to server)
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            // getting response code
            int responseCode = conn.getResponseCode();
            LOG.trace("Response code --> " + responseCode);

            // get the input stream of connection to read data sent from the Server
            InputStream is = conn.getInputStream();

            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            LOG.trace("Captcha validation successful");
            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            LOG.trace("Captcha validation failed", e);
            return false;
        }
    }
}
