package io.lsa.boot.security.jwt.security;

import io.lsa.boot.security.jwt.service.MyUserDetailsService;
import io.lsa.boot.security.jwt.util.JwtUtil;
import io.lsa.boot.security.jwt.util.RequestCorrelation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(10)
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LogManager.getLogger(JwtFilter.class);
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info(RequestCorrelation.getRequestId());
        final String uri = httpServletRequest.getRequestURI();
        if ("/authentication".equals(uri)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (Objects.isNull(username)) {
            throw new ServletException("invalid token");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtUtil.validateToken(jwt, userDetails))
            throw new ServletException("invalid token");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
