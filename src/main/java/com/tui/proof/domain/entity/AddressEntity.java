package com.tui.proof.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Address")
@ToString(exclude = {"orderEntityList"})
@EqualsAndHashCode(exclude = {"orderEntityList"})
public class AddressEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_address")
    @SequenceGenerator(name = "id_address", sequenceName = "ADDRESS_SEQ", allocationSize = 0)
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

    @JsonIgnore
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

}
