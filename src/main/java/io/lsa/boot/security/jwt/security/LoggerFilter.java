package io.lsa.boot.security.jwt.security;

import io.lsa.boot.security.jwt.util.CommonUtil;
import io.lsa.boot.security.jwt.util.RequestCorrelation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
public class LoggerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestId = httpServletRequest.getHeader(RequestCorrelation.requestId);
            if (Objects.isNull(requestId))
                requestId = CommonUtil.getUuid();
            RequestCorrelation.setRequestId(requestId);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            RequestCorrelation.flush();
        }
    }
}
