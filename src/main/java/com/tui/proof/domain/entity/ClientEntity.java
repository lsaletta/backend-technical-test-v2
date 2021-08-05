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
@Table(name = "Client")
@ToString(exclude = {"address", "orderEntityList"})
@EqualsAndHashCode(exclude = {"address", "orderEntityList"})
public class ClientEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_client")
    @SequenceGenerator(name = "id_client", sequenceName = "CLIENT_SEQ", allocationSize = 0)
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

    @JsonIgnore
    @OneToMany(mappedBy = "clientInfo", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

}
