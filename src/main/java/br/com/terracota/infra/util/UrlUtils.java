package br.com.terracota.infra.util;

public final class UrlUtils {

    public static final String[] PUBLIC_URLS_POST = {
            "/api/v1/auth/login",
            "/api/v1/customers",
            "/api/v1/craftsmen"
    };

    public static final String[] PUBLIC_URLS_GET = {
            "/api/v1/craftsmen/{id}",
            "/api/v1/customers/{id}",
            "/api/v1/users/{id}"
    };

    public static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };
}
