package com.tui.proof.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Orders")
@ToString(exclude = {"clientInfo"})
@EqualsAndHashCode(exclude = {"clientInfo"})
public class OrderEntity {

    @Id
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @NotNull
    @Column(name = "PILOTES")
    private Long pilotes;

    @NotNull
    @Column(name = "ORDER_TOTAL")
    private Long orderTotal;

    @NotNull
    @Column(name = "DONE")
    private boolean done;

    @JoinColumn(nullable = true, insertable = false, updatable = false, name = "ADDRESS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private AddressEntity address;

    @JoinColumn(nullable = true, insertable = false, updatable = false, name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private ClientEntity clientInfo;

}
