package org.example.dto;

import lombok.*;
import org.example.modelos.EstadoVenta;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VentaDTO {
    private Long idVenta;
    private Date fecha;
    private EstadoVenta estado;
    private Long idSucursal;
    private List<DetalleDTO> detalle;

    private BigDecimal totalVenta;
}
