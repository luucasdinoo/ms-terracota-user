package br.com.terracota.application.dto.output;

import br.com.terracota.domain.model.Address;

public record AddressOutput(
         String id,
         String name,
         String street,
         String number,
         String neighborhood,
         String city,
         String country,
         String zip,
         String addressType
) {
    public static AddressOutput with(final Address address){
        return new AddressOutput(
                address.getId(),
                address.getName(),
                address.getStreet(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getCountry(),
                address.getZip(),
                address.getAddressType().name()
        );
    }
}
