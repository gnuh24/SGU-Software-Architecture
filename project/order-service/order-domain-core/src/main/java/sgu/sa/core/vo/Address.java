package sgu.sa.core.vo;

import lombok.NonNull;

public record Address(String street, String postalCode, String city) {
    @Override
    public @NonNull String toString() {
        return street + " " + postalCode + " " + city;
    }
}