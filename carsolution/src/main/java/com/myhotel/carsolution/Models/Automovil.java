package com.myhotel.carsolution.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@ToString @EqualsAndHashCode
public class Automovil extends Vehiculo {
    @Column(name = "tipo")
    private String tipo;
    @Min(value = 2, message = "El Automóvil debe tener al menos 2 puertas")
    @Column(name = "numero_puertas")
    private Integer numero_puertas;
    @Column(name = "cantidad_pasajeros")
    private Integer cantidad_pasajeros;
    @Column(name = "capacidad_maletero")
    private Integer capacidad_maletero;
}
