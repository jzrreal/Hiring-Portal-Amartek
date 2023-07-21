package com.hiringportal.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.dto.WebResponse;
import com.hiringportal.entities.Token;
import com.hiringportal.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class LogoutConfiguration implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        String authHeader = request.getHeader("Authorization");
        String jwtToken;
        String stringJson;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            stringJson = "{\"timestamp\":\"" + new Date() + "\",\"status\":403,\"error\":\"Forbidden\",\"path\":\"/api/auth/logout\"}";
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().write(stringJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        jwtToken = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(jwtToken)
                .orElse(null);

        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevokeToken(true);
            tokenRepository.save(storedToken);
        }

        try {
            stringJson = "{\"status\":200,\"message\":\"Success logout\"}";
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(stringJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
