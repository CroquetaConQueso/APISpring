package org.example.service;

import org.example.dto.VentaDTO;
import org.example.modelos.Venta;

import java.util.List;

public interface VentaServiceIMP {
    List<VentaDTO> findVentas();
    VentaDTO findVenta(Long id);
    VentaDTO crearVenta(VentaDTO ventaDto);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);
    void  borrarVenta(Long id);
}
