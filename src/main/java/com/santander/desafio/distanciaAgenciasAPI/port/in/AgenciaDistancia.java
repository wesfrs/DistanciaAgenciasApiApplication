package com.santander.desafio.distanciaAgenciasAPI.port.in;

import com.santander.desafio.distanciaAgenciasAPI.dto.AgenciaDistanciaDTO;

import java.util.List;

public interface AgenciaDistancia {
    List<AgenciaDistanciaDTO> calculaDistancia(double userX, double userY);
}
