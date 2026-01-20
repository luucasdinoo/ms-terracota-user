package br.com.terracota.domain.model;

import br.com.terracota.domain.enums.AddressType;
import br.com.terracota.domain.utils.IdUtils;
import lombok.*;

import java.util.Objects;
import java.util.Optional;

@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String id;

    private String name;

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String country;

    private String zip;

    private User user;

    private AddressType addressType;

    public static Address create(
            final String name,
            final String street,
            final String number,
            final String neighborhood,
            final String city,
            final String country,
            final String zip,
            final User user,
            final AddressType addressType
    ) {
        return new Address(IdUtils.uuid(), name, street, number, neighborhood, city, country, zip, user, addressType);
    }

    public static Address with(
            final String id,
            final String name,
            final String street,
            final String number,
            final String neighborhood,
            final String city,
            final String country,
            final String zip,
            final User user,
            final AddressType addressType
    ) {
        return new Address(id, name, street, number, neighborhood, city, country, zip, user, addressType);
    }

    public static Address with(final Address address) {
        return new Address(
                address.getId(),
                address.getName(),
                address.getStreet(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getCountry(),
                address.getZip(),
                address.getUser().orElse(null),
                address.getAddressType()
        );
    }

    public Optional<User> getUser() {
        return Optional.of(this.user);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
