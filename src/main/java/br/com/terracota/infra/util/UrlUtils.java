package br.com.terracota.infra.util;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

public final class UrlUtils {

    public static final List<RequestMatcher> PUBLIC_ENDPOINTS = List.of(
            PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.POST, "/api/v1/auth/login"),
            PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.POST, "/api/v1/customers"),
            PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.POST, "/api/v1/craftsmen"),

            PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.GET, "/api/v1/customers/{id}"),
            PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.GET, "/api/v1/craftsmen/{id}"),
            PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.GET, "/api/v1/users/{id}"),

            PathPatternRequestMatcher.withDefaults()
                    .matcher("/v3/api-docs/**"),
            PathPatternRequestMatcher.withDefaults()
                    .matcher("/swagger-ui/**")
    );
}
