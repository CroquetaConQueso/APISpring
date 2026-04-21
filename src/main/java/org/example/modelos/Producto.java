package org.example.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long idProducto;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(name="categoria_producto", nullable = false)
    private String categoria;
    @Column(nullable = false, precision = 10 , scale= 2)
    private BigDecimal precio;
    @Column(nullable = false)
    private int cantidad;
}
