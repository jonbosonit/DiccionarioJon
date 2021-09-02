package com.example.Diccionario.Espa.ol.Ingles.Español.Aplication;

import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolSimpleOutputDto;

import java.util.List;

public interface EspanolService {
    EspanolSimpleOutputDto save(EspanolInputDto espanolInputDto);
    List<EspanolOutputDto> findAll();
    EspanolOutputDto findByPalabra(String palabra);
    EspanolSimpleOutputDto updateByPalabra(String palabra, EspanolInputDto espanolInputDto);
    void deleteByPalabra(String palabra);
    void deleteAll();
}
