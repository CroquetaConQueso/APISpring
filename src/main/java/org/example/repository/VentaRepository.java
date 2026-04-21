package org.example.repository;

import org.example.modelos.Venta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    @EntityGraph(attributePaths = {"detalle", "detalle.producto"})
    List<Venta> findAll();

}
