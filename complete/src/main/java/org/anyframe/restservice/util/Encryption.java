package org.anyframe.restservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Hahn on 2016-02-17.
 */
public class Encryption {
    /**
     * sha512 방식으로 암호화한 스트링을 리턴한다
     *
     * @param target
     * @return [encrypted string]
     */
    public final static String encryptSHA512(String target) {
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-512");
            sh.update(target.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : sh.digest()) {

                String s = Integer.toHexString(0xff & b);

                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);

                sb.append(s);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
