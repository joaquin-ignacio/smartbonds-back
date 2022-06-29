package com.upc.smartbonds.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BondResource {

    private Long id;
    private Double valorNominal;
    private Double tasa;
    private Double periodoPago;
    private Double vencimiento;
    private Double tasaNegociacion;
    private boolean mercadoPrimario;
    private Double convexidad;
    private Double tir;
    private int duracion;
    private  int duracionModificada;
}
