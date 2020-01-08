package io.lsa.boot.security.jwt.util;

import java.util.UUID;

public class CommonUtil {
    private CommonUtil() {
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
