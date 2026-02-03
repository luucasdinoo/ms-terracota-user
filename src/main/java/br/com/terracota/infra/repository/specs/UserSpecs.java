package br.com.terracota.infra.repository.specs;

import br.com.terracota.domain.pagination.SearchFilter;
import br.com.terracota.infra.model.UserEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class UserSpecs {

    private UserSpecs(){}

    public static Specification<UserEntity> withUserFilter(final SearchFilter filter){
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.username() != null && !filter.username().isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("username")), "%" + filter.username().toLowerCase() + "%"
                        )
                );
            }
            if (filter.email() != null && !filter.email().isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("email")),
                                "%" + filter.email().toLowerCase() + "%"
                        )
                );
            }
            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("name")),
                                "%" + filter.name().toLowerCase() + "%"
                        )
                );
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static <T>Specification<T> withGenericFilter(final SearchFilter filter){
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.username() != null && !filter.username().isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("user").get("username")), "%" + filter.username().toLowerCase() + "%"
                        )
                );
            }
            if (filter.email() != null && !filter.email().isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("user").get("email")), "%" + filter.email().toLowerCase() + "%"
                        )
                );
            }
            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("user").get("name")), "%" + filter.name().toLowerCase() + "%"
                        )
                );
            }
            if (filter.document() != null && !filter.document().isBlank()) {
                predicates.add(
                        builder.like(
                                root.get("document").get("value"), "%" + filter.document() + "%"
                        )
                );
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
