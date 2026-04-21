package org.example.service;

import org.example.dto.SucursalDTO;
import org.example.modelos.Sucursal;

import java.util.List;

public interface SucursalServiceIMP {

    List<SucursalDTO> findSucursals();
    SucursalDTO findSucursal(Long id);
    SucursalDTO crearSucursal(SucursalDTO sucursalDto);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);
    void  borrarSucursal(Long id);
}
