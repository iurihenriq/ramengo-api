package br.com.redventures.ramen_go.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

@AllArgsConstructor
public class AuthenticationService {
    private final String apiKey;
    private final String apiKeyHeaderName;

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKeyRequest = request.getHeader(apiKeyHeaderName);
        if (apiKeyRequest == null || !apiKeyRequest.equals(this.apiKey)) {
            throw new BadCredentialsException("x-api-key header missing");
        }

        return new ApiKeyAuthentication(apiKeyRequest, AuthorityUtils.NO_AUTHORITIES);
    }
}
