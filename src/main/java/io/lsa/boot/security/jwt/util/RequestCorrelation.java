package io.lsa.boot.security.jwt.util;

import java.io.Serializable;


public class RequestCorrelation implements Serializable {
    private static final long serialVersionUID = -2962533891546464512L;
    private static final String requestId = "request-id";

    private static final ThreadLocal<String> id = new InheritableThreadLocal<>();

    public static String getRequestId() {
        return requestId;
    }

    public static void setRequestId(String requestId) {
        id.set(requestId);
    }
}
