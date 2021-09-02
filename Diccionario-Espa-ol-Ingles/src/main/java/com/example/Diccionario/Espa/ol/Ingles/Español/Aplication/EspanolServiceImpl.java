package com.example.Diccionario.Espa.ol.Ingles.Español.Aplication;

import com.example.Diccionario.Espa.ol.Ingles.Español.Domain.Espanol;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolSimpleOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Repository.Jpa.EspanolRepository;
import com.example.Diccionario.Espa.ol.Ingles.Exception.ErrorException;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Repository.Jpa.InglesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EspanolServiceImpl implements EspanolService {

    @Autowired
    EspanolRepository espanolRepository;

    @Autowired
    InglesRepository inglesRepository;

    Boolean isSave;

    @Override
    public EspanolSimpleOutputDto save(EspanolInputDto espanolInputDto) {

         if(espanolRepository.findByPalabra(espanolInputDto.getPalabra()).isPresent()){
             throw new ErrorException("Palabra repetida.");
         }

         if(espanolInputDto.getPalabra() != null && !espanolInputDto.getPalabra().matches("^[a-zA-Z]*$")){
             throw new ErrorException("Palabra no cumple requisitos.");
         }

         isSave=true;

         Espanol espanol = new Espanol();
         espanol.getEspanol(espanolInputDto,isSave);

         EspanolSimpleOutputDto espanolSimpleOutputDto = new EspanolSimpleOutputDto(espanolRepository.save(espanol));
         return espanolSimpleOutputDto;


    }

    @Override
    public List<EspanolOutputDto> findAll() {
        List<EspanolOutputDto> espanolOutputDtos;
        espanolOutputDtos = espanolRepository.findAll().stream().map(e ->
                new EspanolOutputDto(e,inglesRepository)).collect(Collectors.toList());
        return espanolOutputDtos;
    }

    @Override
    public EspanolOutputDto findByPalabra(String palabra) {
        Espanol espanol = espanolRepository.findByPalabra(palabra).orElseThrow(() ->
                new ErrorException("La palabra '" + palabra + "' no está en el diccionario."));
        EspanolOutputDto espanolOutputDto = new EspanolOutputDto(espanol,inglesRepository);
        return espanolOutputDto;
    }

    @Override
    public EspanolSimpleOutputDto updateByPalabra(String palabra, EspanolInputDto espanolInputDto) {
        Espanol espanol = espanolRepository.findByPalabra(palabra).orElseThrow(() ->
                new ErrorException("La palabra '" + palabra + "' no está en el diccionario."));

        isSave = false;
        espanol.getEspanol(espanolInputDto,isSave);
        EspanolSimpleOutputDto espanolSimpleOutputDto = new EspanolSimpleOutputDto(espanolRepository.save(espanol));

        return espanolSimpleOutputDto;
    }

    @Override
    public void deleteByPalabra(String palabra) {
        espanolRepository.findByPalabra(palabra).orElseThrow(() -> new ErrorException("La palabra '"
                + palabra + "' no está en el diccionario."));
        espanolRepository.deleteById(espanolRepository.findByPalabra(palabra).orElseThrow(()-> new ErrorException("Palabra '" +
                "' no encontrada.")).getId());
    }

    @Override
    public void deleteAll() {
        espanolRepository.deleteAll();
    }


}
