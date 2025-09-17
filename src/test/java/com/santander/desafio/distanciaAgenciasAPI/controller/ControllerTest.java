package com.santander.desafio.distanciaAgenciasAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.desafio.distanciaAgenciasAPI.domain.model.Agencia;
import com.santander.desafio.distanciaAgenciasAPI.dto.CadastrarAgenciaRequest;
import com.santander.desafio.distanciaAgenciasAPI.service.AgenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AgenciaService agenciaService;

    @Test
    void cadastrar_comRequestValida_deveRetornarCreated() throws Exception {
        CadastrarAgenciaRequest request = new CadastrarAgenciaRequest();
        request.setNome("Agencia Paulista");
        request.setPosX(-46.658);
        request.setPosY(-23.561);

        Agencia agenciaSalva = new Agencia(1L, "Agencia Paulista", -46.658, -23.561);
        when(agenciaService.cadastrarAgencia(any(), anyDouble(), anyDouble())).thenReturn(agenciaSalva);

        mockMvc.perform(post("/desafio/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Agencia Paulista"));
    }

    @Test
    void cadastrar_comRequestInvalida_deveRetornarBadRequest() throws Exception {
        CadastrarAgenciaRequest request = new CadastrarAgenciaRequest();
        request.setNome(" ");
        request.setPosX(null);

        mockMvc.perform(post("/desafio/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("O nome não pode estar em branco"))
                .andExpect(jsonPath("$.posX").value("A coordenada posX não pode ser nula"));
    }
}
