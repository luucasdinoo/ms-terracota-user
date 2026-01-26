package br.com.terracota.infra.api.dto.response;

import br.com.terracota.application.dto.output.AddressOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressResponse(
        String id,
        String name,
        String street,
        String number,
        String neighborhood,
        String city,
        String country,
        String zip,
        @JsonProperty("address_type") String addressType
) {
    public static AddressResponse with(final AddressOutput data) {
        return new AddressResponse(
                data.id(),
                data.name(),
                data.street(),
                data.number(),
                data.neighborhood(),
                data.city(),
                data.country(),
                data.zip(),
                data.addressType()
        );
    }
}
