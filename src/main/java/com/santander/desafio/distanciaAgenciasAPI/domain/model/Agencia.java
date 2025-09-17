package com.santander.desafio.distanciaAgenciasAPI.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Agencia {

    private Long id;
    private String nome;
    private Double posX;
    private Double posY;

    public Agencia(String name, double posX, double posY) {
        if (Double.isNaN(posX) || Double.isNaN(posY))
            throw new IllegalArgumentException("Coordenadas inconsistentes ou inválidas");
        this.nome = name;
        this.posX = posX;
        this.posY = posY;
    }
}
