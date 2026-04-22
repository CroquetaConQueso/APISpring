package org.example.controller;

import org.example.dto.SucursalDTO;
import org.example.mapper.Mapper;
import org.example.service.SucursalServiceIMP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private SucursalServiceIMP sucursalServiceIMP;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> devolverSucursales(){
        return ResponseEntity.ok(sucursalServiceIMP.findSucursals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> devolverSucursal(@PathVariable Long id){
        return ResponseEntity.ok(sucursalServiceIMP.findSucursal(id));
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO){
        SucursalDTO sucursalCreada = sucursalServiceIMP.crearSucursal(sucursalDTO);

        return ResponseEntity.created(URI.create("/api/sucursales"+sucursalCreada.getIdSucursal())).body(sucursalCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@RequestBody SucursalDTO sucursalDTO, @PathVariable Long id){
        return ResponseEntity.ok(sucursalServiceIMP.actualizarSucursal(id, sucursalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SucursalDTO> eliminarSucursal(@PathVariable Long id){
        sucursalServiceIMP.borrarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
