package com.example.Diccionario.Espa.ol.Ingles.Ingles.Aplication;

import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesSimpleOutputDto;

import java.util.List;

public interface InglesService {
    InglesOutputDto save(InglesInputDto inglesInputDto);
    InglesOutputDto findByPalabra(String palabra);
    List<InglesOutputDto> findAll();
    InglesSimpleOutputDto updateIngles(String palabra, InglesInputDto inglesInputDto);
    void deleteByPalabra(String palabra);
}
