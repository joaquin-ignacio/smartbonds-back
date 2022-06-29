package com.upc.smartbonds.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.smartbonds.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
public class SaveBondResource {

    @NotNull
    private Double valorNominal;

    @NotNull
    private Double tasa;

    @NotNull
    private Double periodoPago;

    @NotNull
    private Double vencimiento;

    @NotNull
    private Double tasaNegociacion;

    @NotNull
    private boolean mercadoPrimario;

    @NotNull
    private Double convexidad;

    @NotNull
    private Double tir;

    @NotNull
    private int duracion;

    @NotNull
    private  int duracionModificada;
}
