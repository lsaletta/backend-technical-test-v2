package com.tui.proof.domain.entity;

import com.tui.proof.domain.enums.EStatusType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_order")
    @SequenceGenerator(name = "id_order", sequenceName = "ORDER_SEQ", allocationSize = 0)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CLIENT_ID")
    private Long clientId;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @NotNull
    @Column(name = "PILOTES")
    private int pilotes;

    @Column(name = "ORDER_TOTAL")
    private double orderTotal;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EStatusType status;

    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @JoinColumn(nullable = true, insertable = false, updatable = false,
            name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ClientEntity clientInfo;

}
