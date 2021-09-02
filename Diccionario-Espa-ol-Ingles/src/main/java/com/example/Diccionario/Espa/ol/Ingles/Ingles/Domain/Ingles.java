package com.example.Diccionario.Espa.ol.Ingles.Ingles.Domain;

import com.example.Diccionario.Espa.ol.Ingles.Español.Domain.Espanol;
import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Repository.Jpa.EspanolRepository;
import com.example.Diccionario.Espa.ol.Ingles.Exception.ErrorException;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Dto.InglesInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingles")
public class Ingles {

    @GeneratedValue
    @Id
    Integer id;

    @Column(nullable = false)
    String palabra;

    @Column
    Date fecha_alta;

    @Column
    Date fecha_modif;

    @JoinColumn(name = "id_palabra_espanol")
    @ManyToOne(fetch = FetchType.LAZY)
    Espanol espanol;

    public  void getIngles(InglesInputDto inglesInputDto, EspanolRepository espanolRepository, Boolean isSave){
        if(inglesInputDto.getPalabra() != null) setPalabra(inglesInputDto.getPalabra());
        if(espanolRepository.findByPalabra(inglesInputDto.getPalabraEspanol()).isPresent()){
            setEspanol(espanolRepository.findByPalabra(inglesInputDto.getPalabraEspanol()).get());
        } else throw new ErrorException("La palabra no contiene su igual en Español.");

        Calendar calendar = Calendar.getInstance();
        Date current_date = calendar.getTime();
        if(isSave) {
            setFecha_alta(current_date);
            setFecha_modif(null);
        }
        else setFecha_modif(current_date);

    }
}
