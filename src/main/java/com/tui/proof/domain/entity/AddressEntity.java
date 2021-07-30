package com.tui.proof.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Address")
public class AddressEntity {

    @Id
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "STREET")
    private String street;

    @NotNull
    @Column(name = "POST_CODE")
    private String postCode;

    @NotNull
    @Column(name = "CITY")
    private String city;

    @NotNull
    @Column(name = "COUNTRY")
    private String country;

}
