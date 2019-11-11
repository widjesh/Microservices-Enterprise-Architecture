package edu.mum.cs.order.service.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Jwt request filter.
 */
@Component
@AllArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            Jwts.parser().setSigningKey("VE9QU0VDUkVU").parseClaimsJws(jwt).getBody();
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            log.error("Invalid JWT token");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("Invalid JWT");
        }


    }
}
