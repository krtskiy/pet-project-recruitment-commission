package com.epam.koretskyi.commission.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Class for converting user's password to hashcode.
 *
 * @author D.Koretskyi on 22.09.2020.
 */
public class MD5Util {

    private MD5Util() {}

    public static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }

}
