package com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Controller;

import com.example.Diccionario.Espa.ol.Ingles.Español.Aplication.EspanolService;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolSimpleOutputDto;
import com.example.Diccionario.Espa.ol.Ingles.Exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/espanol")
public class EspanolController {

    @Autowired
    EspanolService espanolService;

    @PostMapping
    public ResponseEntity<?> anadirEspanol(@RequestBody @Valid EspanolInputDto espanolInputDto){
        try{
            return ResponseEntity.status(202).body(espanolService.save(espanolInputDto));
        }catch (ErrorException e){

            if(e.toString().contains("Palabra repetida.")){
                return  ResponseEntity.status(409).body("La palabra '" + espanolInputDto.getPalabra() + "' ya está en el diccionario.");

            }else if(e.toString().contains("Palabra no cumple requisitos.")){
                return  ResponseEntity.status(400).body("Solo caracteres alfabeticos por favor.");
                //TODO
            }else{
                return ResponseEntity.status(500).body("Ha habido un error interno. No se ha guardado la palabra.");
            }
        }

    }

    @GetMapping
    public List<EspanolOutputDto> getEspanol(){
        return espanolService.findAll();
    }

    @GetMapping("/{palabra}")
    public EspanolOutputDto getEspanolByPalabra(@PathVariable String palabra){
        return espanolService.findByPalabra(palabra);
    }

    @PutMapping("/{palabra}")
    public EspanolSimpleOutputDto updateEspanol(@PathVariable String palabra,
                                         @RequestBody EspanolInputDto espanolInputDto){
    return (espanolService.updateByPalabra(palabra, espanolInputDto));
    }

    @DeleteMapping("/{palabra}")
    public ResponseEntity deleteByPalabra(@PathVariable String palabra){
        espanolService.deleteByPalabra(palabra);
        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping
    public void deleteAll(){
        espanolService.deleteAll();
    }

}
