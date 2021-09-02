package com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto;

import com.example.Diccionario.Espa.ol.Ingles.Español.Domain.Espanol;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EspanolSimpleOutputDto {

    Integer id;
    String palabra;
    String descripcion;
    Date fechaAlta;
    Date fechaModificacion;

    public EspanolSimpleOutputDto(Espanol espanol){
        setId(espanol.getId());
        setPalabra(espanol.getPalabra());
        setDescripcion(espanol.getDescripcion());
        setFechaAlta(espanol.getFecha_alta());
        setFechaModificacion(espanol.getFecha_modif());
    }
}
