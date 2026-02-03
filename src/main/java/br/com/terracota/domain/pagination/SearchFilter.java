package br.com.terracota.domain.pagination;

public record SearchFilter(
    String username,
    String email,
    String name,
    String document,
    int page,
    int perPage,
    String sort,
    String dir
) {
    public static SearchFilter with(
        final String username,
        final String email,
        final String name,
        final String document,
        int page,
        int perPage,
        String sort,
        String dir
    ) {
        return new SearchFilter(username, email, name, document, page, perPage, sort, dir);
    }
}
