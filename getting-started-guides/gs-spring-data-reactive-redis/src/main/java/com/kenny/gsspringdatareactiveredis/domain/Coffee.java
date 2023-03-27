package com.kenny.gsspringdatareactiveredis.domain;

import lombok.*;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    private String id;
    private String name;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coffee coffee = (Coffee) o;
        return Objects.equals(id, coffee.id) && Objects.equals(name, coffee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
