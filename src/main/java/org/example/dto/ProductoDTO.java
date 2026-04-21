package org.example.dto;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private String categoria;
    private BigDecimal precio;
    private int cantidad;
}
