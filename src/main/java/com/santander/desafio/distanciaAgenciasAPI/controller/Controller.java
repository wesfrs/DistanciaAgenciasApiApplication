package com.santander.desafio.distanciaAgenciasAPI.controller;

import com.santander.desafio.distanciaAgenciasAPI.domain.model.Agencia;
import com.santander.desafio.distanciaAgenciasAPI.dto.AgenciaDistanciaDTO;
import com.santander.desafio.distanciaAgenciasAPI.dto.CadastrarAgenciaRequest;
import com.santander.desafio.distanciaAgenciasAPI.service.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/desafio")
public class Controller {

    private final AgenciaService agenciaService;

    public Controller(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String,Object>> cadastrar(@Valid @RequestBody CadastrarAgenciaRequest request) {
        Agencia agenciaSalva = agenciaService.cadastrarAgencia(request.getNome(), request.getPosX(), request.getPosY());
        Map<String,Object> resp = Map.of("id", agenciaSalva.getId(), "name", agenciaSalva.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/distancia")
    public ResponseEntity<List<AgenciaDistanciaDTO>> distancia(
            @RequestParam double posX,
            @RequestParam double posY) {
        List<AgenciaDistanciaDTO> distancias = agenciaService.calculaDistancia(posX, posY);
        return ResponseEntity.ok(distancias);
    }
}
