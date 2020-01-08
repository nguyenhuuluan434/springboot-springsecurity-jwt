package io.lsa.boot.security.jwt.util;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class RequestCorrelation implements Serializable {
    private static final long serialVersionUID = -2962533891546464512L;
    public static final String requestId = "request-id";

    private static final ThreadLocal<String> id = new InheritableThreadLocal<>();

    public static String getRequestId() {
        if (Objects.isNull(id.get()))
            id.set(CommonUtil.getUuid());
        return id.get();
    }

    public static void setRequestId(String requestId) {
        if (Objects.isNull(id.get()))
            id.set(requestId);
    }

    public static void flush() {
        if (Objects.nonNull(id.get()))
            id.remove();
    }
}
