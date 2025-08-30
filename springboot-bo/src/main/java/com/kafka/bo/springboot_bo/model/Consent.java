package com.kafka.bo.springboot_bo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consent {

    private String reference;
    private String type;
    private String document;
    private String name;
    private boolean granted;
}