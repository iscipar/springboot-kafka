package com.kafka.consumer.springboot_consumer.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consent")
@IdClass(ConsentEntityPK.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsentEntity {

    @Id
    private String reference;
    private String type;
    @Id
    private String document;
    private String name;
    private boolean granted;
}
