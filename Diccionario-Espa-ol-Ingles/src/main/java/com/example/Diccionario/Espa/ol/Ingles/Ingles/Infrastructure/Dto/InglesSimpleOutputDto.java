package com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto;

import com.example.Diccionario.Espa.ol.Ingles.Ingles.Domain.Ingles;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class InglesSimpleOutputDto {

    Integer id;
    String palabra;
    Date fechaAlta;
    Date fechaModificacion;
    String palabraEspanol;

    public InglesSimpleOutputDto(Ingles ingles){
        setId(ingles.getId());
        setPalabra(ingles.getPalabra());
        setFechaAlta(ingles.getFecha_alta());
        setFechaModificacion(ingles.getFecha_modif());
        setPalabraEspanol(ingles.getEspanol().getPalabra());
    }
}
