package com.santander.desafio.distanciaAgenciasAPI.adapter.out.persistence;

import com.santander.desafio.distanciaAgenciasAPI.domain.model.Agencia;
import com.santander.desafio.distanciaAgenciasAPI.port.out.AgenciaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class AgenciaRepositoryAdapter implements AgenciaRepositoryPort {

    private final AgenciaJpaRepository jpaRepository;

    @Override
    public Agencia save(Agencia agencia) {
        AgenciaEntity agenciaEntity = toEntity(agencia);
        AgenciaEntity savedEntity = jpaRepository.save(agenciaEntity);
        return toDomain(savedEntity);
    }

    @Override
    public List<Agencia> findAll() {
        List<AgenciaEntity> entities = jpaRepository.findAll();

        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Agencia toDomain(AgenciaEntity entity) {
        return new Agencia(
                entity.getId(),
                entity.getNome(),
                entity.getPosX(),
                entity.getPosY()
        );
    }

    private AgenciaEntity toEntity(Agencia agencia) {
        return new AgenciaEntity(
                agencia.getId(),
                agencia.getNome(),
                agencia.getPosX(),
                agencia.getPosY()
        );
    }
}
