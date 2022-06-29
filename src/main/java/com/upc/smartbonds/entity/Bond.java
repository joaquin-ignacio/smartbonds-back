package com.upc.smartbonds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bonds")
@Data
public class Bond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "valor_nominal")
    private Double valorNominal;

    @NotNull
    @Column(name = "tasa")
    private Double tasa;

    @NotNull
    @Column(name = "periodo_pago")
    private Double periodoPago;

    @NotNull
    private Double vencimiento;

    @NotNull
    @Column(name = "tasa_negociacion")
    private Double tasaNegociacion;

    @NotNull
    @Column(name = "mercado_primario")
    private boolean mercadoPrimario;

    @NotNull
    private Double convexidad;

    @NotNull
    private Double tir;

    @NotNull
    private int duracion;

    @NotNull
    @Column(name = "duracion_modificada")
    private  int duracionModificada;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
