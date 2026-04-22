package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.VentaDTO;
import org.example.service.VentaServiceIMP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaServiceIMP ventaServiceIMP;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> obtenerVentas(){
        return ResponseEntity.ok(ventaServiceIMP.findVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVenta(@PathVariable Long id){
        return ResponseEntity.ok(ventaServiceIMP.findVenta(id));
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO){
        VentaDTO ventaCreada = ventaServiceIMP.crearVenta(ventaDTO);
        return ResponseEntity.created(URI.create("/api/ventas"+ventaCreada.getIdVenta())).body(ventaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@RequestBody VentaDTO ventaDTO,
                                                    @PathVariable Long id){
        return ResponseEntity.ok(ventaServiceIMP.actualizarVenta(id, ventaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VentaDTO> eliminarVenta(@PathVariable Long id){
        ventaServiceIMP.borrarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
