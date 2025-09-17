package com.santander.desafio.distanciaAgenciasAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaDistanciaDTO {
    private String nomeAgencia;
    private double distancia;
}
