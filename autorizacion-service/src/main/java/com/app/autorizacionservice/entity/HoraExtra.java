package com.app.autorizacionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hora_extras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int mes;
    private int anio;
    private double cantidadHorasExtras;
    private int autorizado;
    private String rutEmpleado;
}
