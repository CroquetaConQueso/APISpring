package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDTO {
    private Long idDetalle;
    private Long idProducto;
    private int cantidad;
    private String notas;
    private BigDecimal subTotal;

}
