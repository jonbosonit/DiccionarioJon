package com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Controller;

import com.example.Diccionario.Espa.ol.Ingles.Exception.ErrorException;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Aplication.InglesService;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingles")
public class InglesController {

    @Autowired
    InglesService inglesService;

    @PostMapping
    public ResponseEntity<?> anadirIngles(@RequestBody @Valid InglesInputDto inglesInputDto){
        try{
            return ResponseEntity.status(202).body(inglesService.save(inglesInputDto));
        }catch (ErrorException e){
            if(e.toString().contains("Palabra repetida.")){
                return ResponseEntity.status(406).body("La palabra '" + inglesInputDto.getPalabra() + "' ya está" +
                        " en el diccionario.");
            }else if(e.toString().contains("Palabra no cumple requisitos.")){
                return  ResponseEntity.status(400).body("Solo caracteres alfabeticos por favor.");

            }else if(e.toString().contains("No contiene palabra espanol.")){
                return ResponseEntity.status(406).body("La palabra '" + inglesInputDto.getPalabra() + "' tiene" +
                        " que tener su igual en español.");
            }else{
                return ResponseEntity.status(500).body("Ha habido un error interno. No se ha guardado la palabra.");
            }

        }
    }

    @GetMapping("/{palabra}")
    public  InglesOutputDto getInglesByPalabra(@PathVariable String palabra){
        return inglesService.findByPalabra(palabra);
    }

    @GetMapping
    public List<InglesOutputDto> getIngles(){
        return inglesService.findAll();
    }

    @PutMapping("/{palabra}")
    public ResponseEntity<?> updateIngles(@PathVariable String palabra,
                                              @RequestBody InglesInputDto inglesInputDto){
        try{
            return ResponseEntity.status(200).body(inglesService.updateIngles(palabra,inglesInputDto));
        }catch (ErrorException e){

            if(e.toString().contains("PalabraEspanol no existe.")){
                return ResponseEntity.status(406).body("No existe la palabra '" + inglesInputDto.getPalabraEspanol() +
                        "' en Español.");

            }else if(e.toString().contains("Palabra no encontrada.")){
                return ResponseEntity.status(404).body("No existe la palabra '" + palabra + "' en el diccionario.");

            }else{
                return ResponseEntity.status(500).body("Ha habido un error en el servidor.");
            }
        }

    }

    @DeleteMapping("/{palabra}")
    public ResponseEntity deleteIngles(@PathVariable String palabra){
        try{
            inglesService.deleteByPalabra(palabra);
            return ResponseEntity.status(200).body(null);
        }catch (ErrorException e){
            if(e.toString().contains("Palabra no encontrada.")){
                return ResponseEntity.status(404).body("No existe la palabra '" + palabra + "' en el diccionario.");
            }else{
                return ResponseEntity.status(500).body("Ha habido un error en el servidor.");
            }
        }

    }
}
