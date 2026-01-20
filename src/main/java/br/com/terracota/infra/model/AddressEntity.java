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
    @Column(name = "ADDRESS_ID")
    private String id;

    @Column(name = "ADDRESS_NAME")
    private String name;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ZIP")
    private String zip;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "ADDRESS_USER_ID")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
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
