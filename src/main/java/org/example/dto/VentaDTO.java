package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.modelos.EstadoVenta;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VentaDTO {
    private Long idVenta;
    private Date fecha;
    private EstadoVenta estado;
    private Long idSucursal;
    private List<DetalleDTO> detalle;

    private BigDecimal totalVenta;
}
