package br.com.terracota.infra.adapter;

import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import br.com.terracota.infra.model.UserEntity;
import br.com.terracota.infra.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
                .map(UserEntity::toDomain);
    }

    @Override
    public void deleteById(String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    private User save(final User user) {
        return this.repository.save(UserEntity.from(user)).toDomain();
    }
}
