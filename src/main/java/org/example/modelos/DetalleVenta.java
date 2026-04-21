package org.example.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
