package com.santander.desafio.distanciaAgenciasAPI.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgenciaTest {

    @Test
    void construtor_comCoordenadaInvalida_deveLancarExcecao() {
        String nome = "Agencia Invalida";
        double invalido = Double.NaN;
        double valido = 10.0;

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {
            new Agencia(nome, invalido, valido);
        });
        assertEquals("Coordenadas inconsistentes ou inválidas", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> {
            new Agencia(nome, valido, invalido);
        });
        assertEquals("Coordenadas inconsistentes ou inválidas", ex2.getMessage());
    }

    @Test
    void construtor_comDadosValidos_deveCriarObjetoCorretamente() {
        String nome = "Agencia Valida";
        double posX = -46.5;
        double posY = -23.8;

        Agencia agencia = new Agencia(nome, posX, posY);

        assertNotNull(agencia);
        assertEquals(nome, agencia.getNome());
        assertEquals(posX, agencia.getPosX());
        assertEquals(posY, agencia.getPosY());
    }
}
