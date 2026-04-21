package org.example.dto;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleDTO {
    private Long idDetalle;
    private Long idProducto;
    private int cantidad;
    private String notas;
    private BigDecimal subTotal;

}
