package br.com.redventures.ramengo.security;

import br.com.redventures.ramengo.exception.ApiKeyMissingException;
import br.com.redventures.ramengo.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilter extends GenericFilterBean {
    private final String apiKeyHeaderName;
    private final String apiKey;

    protected AuthenticationFilter(String apiKey,
                                   String apiKeyHeaderName) {
        super();
        this.apiKey = apiKey;
        this.apiKeyHeaderName = apiKeyHeaderName;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            AuthenticationService authenticationService = new AuthenticationService(this.apiKey, this.apiKeyHeaderName);
            Authentication authentication = authenticationService.getAuthentication((HttpServletRequest) request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ApiKeyMissingException exp) {
            handleException((HttpServletResponse) response, HttpStatus.UNAUTHORIZED, exp.getMessage());
            return;
        } catch (Exception exp) {
            handleException((HttpServletResponse) response, HttpStatus.INTERNAL_SERVER_ERROR, "could not place order");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void handleException(HttpServletResponse httpResponse, HttpStatus status, String message) throws IOException {
        httpResponse.setStatus(status.value());
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonErrorResponse = objectMapper.writeValueAsString(new ErrorResponse(message));

        PrintWriter writer = httpResponse.getWriter();
        writer.print(jsonErrorResponse);
        writer.flush();
        writer.close();
    }

}
