package org.example.service;

import org.example.dto.VentaDTO;
import org.example.modelos.Venta;

import java.util.List;

public interface VentaServiceIMP {
    List<VentaDTO> findVentas();
    VentaDTO findVenta(Long id);
    VentaDTO crearVenta(Venta venta);
    VentaDTO actualizarVenta(Long id, Venta venta);
    void  borrarVenta(Long id);
}
