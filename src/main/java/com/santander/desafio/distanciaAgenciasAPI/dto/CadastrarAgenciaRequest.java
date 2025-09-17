package com.santander.desafio.distanciaAgenciasAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastrarAgenciaRequest {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotNull(message = "A coordenada posX não pode ser nula")
    private Double posX;

    @NotNull(message = "A coordenada posY não pode ser nula")
    private Double posY;
}
