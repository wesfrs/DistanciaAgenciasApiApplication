package com.santander.desafio.distanciaAgenciasAPI.service;

import com.santander.desafio.distanciaAgenciasAPI.domain.model.Agencia;
import com.santander.desafio.distanciaAgenciasAPI.dto.AgenciaDistanciaDTO;
import com.santander.desafio.distanciaAgenciasAPI.port.in.AgenciaDistancia;
import com.santander.desafio.distanciaAgenciasAPI.port.out.AgenciaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AgenciaService implements AgenciaDistancia {

    private final AgenciaRepositoryPort agenciaRepositoryPort;

    public AgenciaService(AgenciaRepositoryPort agenciaRepositoryPort) {
        this.agenciaRepositoryPort = agenciaRepositoryPort;
    }

    public Agencia cadastrarAgencia(String name, double posX, double posY) {
        String nomePadrao = (name == null || name.isBlank())
                ? "AGENCIA_" + UUID.randomUUID().toString().substring(0,6)
                : name.trim();
        Agencia a = new Agencia(nomePadrao, posX, posY);
        return agenciaRepositoryPort.save(a);
    }

    @Override
    public List<AgenciaDistanciaDTO> calculaDistancia(double userX, double userY) {
        List<Agencia> agencies = agenciaRepositoryPort.findAll();

        return agencies.stream()
                .map(agencia -> new AgenciaDistanciaDTO(
                        agencia.getNome(),
                        calculoEuclidiano(userX, userY, agencia.getPosX(), agencia.getPosY())
                ))
                .sorted(Comparator.comparingDouble(AgenciaDistanciaDTO::getDistancia))
                .collect(Collectors.toList());
    }

    private double calculoEuclidiano(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
