package com.santander.desafio.distanciaAgenciasAPI.port.out;

import com.santander.desafio.distanciaAgenciasAPI.domain.model.Agencia;

import java.util.List;

public interface AgenciaRepositoryPort {
        Agencia save(Agencia agency);
        List<Agencia> findAll();
    }

