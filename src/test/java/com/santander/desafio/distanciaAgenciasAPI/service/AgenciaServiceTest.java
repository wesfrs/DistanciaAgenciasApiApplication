package com.santander.desafio.distanciaAgenciasAPI.service;

import com.santander.desafio.distanciaAgenciasAPI.domain.model.Agencia;
import com.santander.desafio.distanciaAgenciasAPI.dto.AgenciaDistanciaDTO;
import com.santander.desafio.distanciaAgenciasAPI.port.out.AgenciaRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgenciaServiceTest {

    @Mock
    private AgenciaRepositoryPort agenciaRepositoryPort;

    @InjectMocks
    private AgenciaService agenciaService;

    @Test
    void cadastrarAgencia_comNome_deveSalvarComNomeFornecido() {
        String nome = "Agencia Teste";
        double posX = 10.0;
        double posY = 20.0;
        Agencia agenciaMock = new Agencia(1L, nome, posX, posY);
        when(agenciaRepositoryPort.save(any(Agencia.class))).thenReturn(agenciaMock);

        agenciaService.cadastrarAgencia(nome, posX, posY);

        ArgumentCaptor<Agencia> agenciaCaptor = ArgumentCaptor.forClass(Agencia.class);
        verify(agenciaRepositoryPort).save(agenciaCaptor.capture());
        Agencia agenciaSalva = agenciaCaptor.getValue();

        assertEquals(nome, agenciaSalva.getNome());
        assertEquals(posX, agenciaSalva.getPosX());
        assertEquals(posY, agenciaSalva.getPosY());
    }

    @Test
    void cadastrarAgencia_semNome_deveSalvarComNomeGerado() {
        double posX = 10.0;
        double posY = 20.0;
        Agencia agenciaMock = new Agencia(1L, "AGENCIA_123456", posX, posY);
        when(agenciaRepositoryPort.save(any(Agencia.class))).thenReturn(agenciaMock);

        agenciaService.cadastrarAgencia(null, posX, posY);

        ArgumentCaptor<Agencia> agenciaCaptor = ArgumentCaptor.forClass(Agencia.class);
        verify(agenciaRepositoryPort).save(agenciaCaptor.capture());
        Agencia agenciaSalva = agenciaCaptor.getValue();

        assertTrue(agenciaSalva.getNome().startsWith("AGENCIA_"));
    }

    @Test
    void calculaDistancia_deveRetornarListaOrdenadaPorDistancia() {
        Agencia agenciaA = new Agencia(1L, "Agencia Longe", 10.0, 10.0);
        Agencia agenciaB = new Agencia(2L, "Agencia Perto", 1.0, 1.0);
        when(agenciaRepositoryPort.findAll()).thenReturn(List.of(agenciaA, agenciaB));

        List<AgenciaDistanciaDTO> resultado = agenciaService.calculaDistancia(0.0, 0.0);

        assertEquals(2, resultado.size());
        assertEquals("Agencia Perto", resultado.get(0).getNomeAgencia());
        assertEquals("Agencia Longe", resultado.get(1).getNomeAgencia());
        assertTrue(resultado.get(0).getDistancia() < resultado.get(1).getDistancia());
    }
}
