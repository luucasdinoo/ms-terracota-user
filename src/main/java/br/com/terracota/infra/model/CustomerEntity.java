package br.com.terracota.infra.model;

import br.com.terracota.domain.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity(name = "CUSTOMER")
@Table(name = "TRC_CUSTOMER")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CustomerEntity {

    @Id
    @Column(name = "CUSTOMER_ID")
    private String id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DOCUMENT_ID")
    private DocumentEntity document;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

    public static CustomerEntity from(final Customer customer){
        return new CustomerEntity(
                customer.getId(),
                customer.getUser().map(UserEntity::from).orElse(null),
                customer.getDocument().map(DocumentEntity::from).orElse(null),
                customer.getDateOfBirth(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public Customer toDomain(){
        return Customer.with(
                getId(),
                getUser().toDomain(),
                getDocument().toDomain(),
                getDateOfBirth(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
