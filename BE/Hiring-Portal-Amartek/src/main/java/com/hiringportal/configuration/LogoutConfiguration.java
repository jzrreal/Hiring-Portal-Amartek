package com.hiringportal.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.entities.Token;
import com.hiringportal.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class LogoutConfiguration implements LogoutHandler {

    private final TokenRepository tokenRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        String authHeader = request.getHeader("Authorization");
        String jwtToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
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
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Map.of(
                    "status", 200,
                    "message", "Success logout"
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
