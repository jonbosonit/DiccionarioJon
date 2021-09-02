package com.example.Diccionario.Espa.ol.Ingles.Español.Infrastructure.Repository.Jpa;

import com.example.Diccionario.Espa.ol.Ingles.Español.Domain.Espanol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EspanolRepository extends JpaRepository<Espanol,Integer> {
    Optional<Espanol> findByPalabra(String palabra);
}
