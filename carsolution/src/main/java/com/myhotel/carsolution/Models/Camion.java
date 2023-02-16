package com.myhotel.carsolution.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@ToString @EqualsAndHashCode
public class Camion extends Vehiculo{
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "capacidad")
    private Double capacidad;
    @Min(value = 2, message = "El Camión debe tener al menos 2 ejes")
    @Column(name = "cantidad_ejes")
    private Integer cantidad_ejes;
}
