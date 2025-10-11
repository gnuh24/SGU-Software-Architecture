package vo;

import org.jetbrains.annotations.NotNull;

public record Address(String street, String postalCode, String city) {
    @Override
    public @NotNull String toString() {
        return street + " " + postalCode + " " + city;
    }
}