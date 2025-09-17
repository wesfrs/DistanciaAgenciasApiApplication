package com.santander.desafio.distanciaAgenciasAPI.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double posX;
    private Double posY;
}
