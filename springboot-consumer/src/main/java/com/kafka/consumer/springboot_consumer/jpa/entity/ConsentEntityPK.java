package com.kafka.consumer.springboot_consumer.jpa.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ConsentEntityPK implements Serializable {

    private String reference;
    private String document;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ConsentEntityPK consentEntityPK))
            return false;
        return Objects.equals(reference, consentEntityPK.reference)
                && Objects.equals(document, consentEntityPK.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, document);
    }
}
