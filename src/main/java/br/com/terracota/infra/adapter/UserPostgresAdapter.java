package br.com.terracota.infra.adapter;

import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import br.com.terracota.infra.model.UserEntity;
import br.com.terracota.infra.repository.UserRepository;
import br.com.terracota.infra.repository.specs.UserSpecs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPostgresAdapter implements UserGateway {

    private final UserRepository repository;

    @Override
    public User create(final User user) {
        return save(user);
    }

    @Override
    public User update(final User user) {
        return save(user);
    }

    @Override
    @Transactional
    public Optional<User> findById(final String id) {
        return this.repository.findById(id)
                .map(UserEntity::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(username)
                .map(UserEntity::toDomainWithoutAddress);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String id) {
        return this.repository.findByEmail(id)
                .map(UserEntity::toDomainWithoutAddress);
    }

    @Override
    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(final String username) {
        return this.repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return this.repository.existsByEmail(email);
    }

    @Override
    public boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    @Override
    public Pagination<User> search(final SearchFilter filter) {
        var pageRequest = PageRequest.of(
                filter.page(),
                filter.perPage(),
                Sort.by(Sort.Direction.fromString(filter.dir()), filter.sort())
        );

        Page<UserEntity> page = this.repository.findAll(UserSpecs.withUserFilter(filter), pageRequest);

        return new Pagination<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.map(UserEntity::toDomainWithoutAddress).toList()
        );
    }

    private User save(final User user) {
        return this.repository.save(UserEntity.from(user))
                .toDomainWithoutAddress();
    }
}
