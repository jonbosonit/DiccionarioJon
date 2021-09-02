package com.example.Diccionario.Espa.ol.Ingles.Español.Domain;

import com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Dto.EspanolInputDto;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Domain.Ingles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "español")
@Data
@NoArgsConstructor
public class Espanol {

    @GeneratedValue
    @Id
    Integer id;

    @Column(nullable = false)
    String palabra;

    @Column
    String descripcion;

    @Column
    Date fecha_alta;

    @Column
    Date fecha_modif;

    @OneToMany(mappedBy = "espanol", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Ingles> inglesList;


    public void getEspanol(EspanolInputDto espanolInputDto,Boolean isSave){
        if(espanolInputDto.getPalabra() != null) setPalabra(espanolInputDto.getPalabra());
        if(espanolInputDto.getDescripcion() != null) setDescripcion(espanolInputDto.getDescripcion());
        setInglesList(inglesList);
        Calendar calendar = Calendar.getInstance();
        Date current_Date = calendar.getTime();
        if(isSave) {
            setFecha_alta(current_Date);
        }
        else if(!isSave){
            setFecha_modif(current_Date);
        }
    }
}
