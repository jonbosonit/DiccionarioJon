package com.example.Diccionario.Espa.ol.Ingles.Ingles.Aplication;

import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Repository.Jpa.EspanolRepository;
import com.example.Diccionario.Espa.ol.Ingles.Exception.ErrorException;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Domain.Ingles;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesSimpleOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Repository.Jpa.InglesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InglesServiceImpl implements InglesService{

    @Autowired
    InglesRepository inglesRepository;

    @Autowired
    EspanolRepository espanolRepository;

    Boolean isSave;

    @Override
    public InglesOutputDto save(InglesInputDto inglesInputDto) {
        if(inglesRepository.findByPalabra(inglesInputDto.getPalabra()).isPresent()){
            throw new ErrorException("Palabra repetida.");
        }
        if(inglesInputDto.getPalabra() != null && !inglesInputDto.getPalabra().matches("^[a-zA-Z]*$")){
            throw new ErrorException("Palabra no cumple requisitos.");
        }

        if(inglesInputDto.getPalabraEspanol() == null){
            throw new ErrorException("No contiene palabra espanol.");
        }

        isSave = true;
        Ingles ingles = new Ingles();
        ingles.getIngles(inglesInputDto,espanolRepository,isSave);
        InglesOutputDto inglesOutputDto = new InglesOutputDto(inglesRepository.save(ingles));
        return inglesOutputDto;
    }

    @Override
    public InglesOutputDto findByPalabra(String palabra) {
        Ingles ingles = inglesRepository.findByPalabra(palabra).orElseThrow(() ->
                new ErrorException("La palabra '" + palabra + "' no está en el diccionario."));
        InglesOutputDto inglesOutputDto = new InglesOutputDto(ingles);
        return inglesOutputDto;
    }

    @Override
    public List<InglesOutputDto> findAll() {
        List<InglesOutputDto> inglesOutputDtos;
        inglesOutputDtos = inglesRepository.findAll().stream().map(i ->
                new InglesOutputDto(i)).collect(Collectors.toList());
        return inglesOutputDtos;
    }

    @Override
    public InglesSimpleOutputDto updateIngles(String palabra, InglesInputDto inglesInputDto) {
        isSave = false;
        Ingles ingles = inglesRepository.findByPalabra(palabra).orElseThrow(() ->
                new ErrorException("La palabra no está en el diccionario."));
        if(espanolRepository.findByPalabra(inglesInputDto.getPalabraEspanol()).isEmpty()){
            throw new ErrorException("PalabraEspanol no existe.");
        }
        ingles.getIngles(inglesInputDto,espanolRepository,isSave);
        InglesSimpleOutputDto inglesSimpleOutputDto = new InglesSimpleOutputDto(inglesRepository.save(ingles));
        return inglesSimpleOutputDto;
    }

    @Override
    public void deleteByPalabra(String palabra) {
        inglesRepository.deleteById(inglesRepository.findByPalabra(palabra).orElseThrow(()->
                new ErrorException("Palabra no encontrada.")).getId());
    }
}



