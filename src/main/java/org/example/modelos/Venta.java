package org.example.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private EstadoVenta estado;
    @Column(name= "total", nullable = false, precision = 10 ,scale = 2)
    private BigDecimal totalVenta;
    @ManyToOne
    private Sucursal sucursal;
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalle = new ArrayList<>();
}
