package com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EspanolInputDto {

    @NotNull(message = "Tu campo palabra no puede ser nulo.")
    String palabra;

    String descripcion;


}
