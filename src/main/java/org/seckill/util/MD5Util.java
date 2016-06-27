package org.seckill.util;

import org.springframework.util.DigestUtils;

/**
 * Created by lishaoxun on 2016/6/24.
 */
public class MD5Util {

    private static final String salt = "saflkahru2rewqjrioewqu(*&(*&(*&#$E$E";

    public static String getMD5(Object yourField){
        String base = yourField+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
