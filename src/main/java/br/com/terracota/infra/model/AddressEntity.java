package br.com.terracota.infra.model;

import br.com.terracota.domain.enums.AddressType;
import br.com.terracota.domain.model.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "ADDRESS")
@Table(name = "TRC_ADDRESS")
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class AddressEntity {

    @Id
    @Column(name = "ADDRESS_ID", nullable = false, length = 32)
    private String id;

    @Column(name = "ADDRESS_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "STREET", nullable = false, length = 90)
    private String street;

    @Column(name = "NUMBER", nullable = false, length = 10)
    private String number;

    @Column(name = "NEIGHBORHOOD", nullable = false, length = 60)
    private String neighborhood;

    @Column(name = "CITY", nullable = false, length = 60)
    private String city;

    @Column(name = "COUNTRY", nullable = false, length = 90)
    private String country;

    @Column(name = "ZIP", nullable = false, length = 8)
    private String zip;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "ADDRESS_USER_ID")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ADDRESS_TYPE", nullable = false, length = 15)
    private AddressType addressType;

    public static AddressEntity from(final Address address){
        return new AddressEntity(
                address.getId(),
                address.getName(),
                address.getStreet(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getCountry(),
                address.getZip(),
                address.getUser().map(UserEntity::from)
                        .orElse(null),
                address.getAddressType()
        );
    }

    public Address toDomain(){
        return Address.with(
                getId(),
                getName(),
                getStreet(),
                getNumber(),
                getNeighborhood(),
                getCity(),
                getCountry(),
                getZip(),
                getUser().toDomain(),
                getAddressType()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
