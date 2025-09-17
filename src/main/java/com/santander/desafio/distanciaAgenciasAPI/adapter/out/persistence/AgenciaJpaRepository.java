package com.santander.desafio.distanciaAgenciasAPI.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaJpaRepository extends JpaRepository<AgenciaEntity, Long> {
}
