package com.myhotel.carsolution.Models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vehiculo")
@Data
@ToString @EqualsAndHashCode
@Inheritance(strategy=InheritanceType.JOINED)
public class Vehiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "marca", length = 50, nullable = false)
    private String marca;
    @Column(name = "modelo", length = 100, nullable = false)
    private String modelo;
    @Column(name = "patente", length = 200, nullable = false)
    private String patente;

//    @Column(name = "anno", nullable = false)
//    private Integer anno;
    @NotNull
    @DateTimeFormat(pattern = "yyyy")
    private String anno;
    @Column(name = "kilometraje", nullable = false)
    @Min(value = 0L, message = "El valor debe ser positivo")
    private Double kilometraje;
    @Column(name = "cilindrada", nullable = false)
    private String cilindrada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    private Set<Mantencion> matenciones;
}
