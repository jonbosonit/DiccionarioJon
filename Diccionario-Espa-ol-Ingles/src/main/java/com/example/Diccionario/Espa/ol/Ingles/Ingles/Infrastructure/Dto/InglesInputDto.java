package com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class InglesInputDto {

    @NotNull(message = "El campo de Ingles palabra no puede ser nulo.")
    String palabra;

    String palabraEspanol;
}
