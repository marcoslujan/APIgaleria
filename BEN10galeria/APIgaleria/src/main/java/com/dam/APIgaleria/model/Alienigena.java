package com.dam.APIgaleria.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "alienigena")
public class Alienigena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String raza;
    private String planeta;
    private String habilidad;
    private String descripcion;
    private String imagenUrl;

}
