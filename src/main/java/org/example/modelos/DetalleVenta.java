package org.example.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private Long idDetalle;
    @ManyToOne
    private Venta venta;
    @ManyToOne
    private Producto producto;
    @Column(nullable = false)
    private int cantidad;
    private String notas;
    @Column(name="total_detalle", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDetalle;
}
