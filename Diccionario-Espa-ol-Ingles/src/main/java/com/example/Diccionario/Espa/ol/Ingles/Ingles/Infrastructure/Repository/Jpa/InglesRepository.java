package com.example.Diccionario.Espa.ol.Ingles.Ingles.Infrastructure.Repository.Jpa;

import com.example.Diccionario.Espa.ol.Ingles.Español.Domain.Espanol;
import com.example.Diccionario.Espa.ol.Ingles.Ingles.Domain.Ingles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface InglesRepository extends JpaRepository<Ingles,Integer> {
    Optional<Ingles> findByPalabra(String plabra);
    List<Ingles> findByEspanol(Espanol espanol);

}
