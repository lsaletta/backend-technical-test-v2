package com.tui.proof.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Client")
@ToString(exclude = {"address", "orderEntityList"})
@EqualsAndHashCode(exclude = {"address", "orderEntityList"})
public class ClientEntity {

    @Id
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "TELEPHONE")
    private String telephone;

    @OneToMany(mappedBy = "clientInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

}
