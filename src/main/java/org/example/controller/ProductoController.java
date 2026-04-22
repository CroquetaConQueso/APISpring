package org.example.controller;

import org.example.dto.ProductoDTO;
import org.example.service.ProductoServiceIMP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private ProductoServiceIMP productoServiceIMP;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProductos(){

        return ResponseEntity.ok(productoServiceIMP.findProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> traerProducto(@PathVariable Long id){
        return ResponseEntity.ok(productoServiceIMP.findProducto(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO){
        ProductoDTO creado = productoServiceIMP.crearProducto(productoDTO);

        return ResponseEntity.created(URI.create("/api/productos"+creado.getIdProducto())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> ponerProducto(@RequestBody ProductoDTO productoDTO,
                                                     @PathVariable Long id){
        return ResponseEntity.ok(productoServiceIMP.actualizarProducto(id,productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDTO> eliminarProducto(@PathVariable Long id){
        productoServiceIMP.borrarProducto(id);

        return ResponseEntity.noContent().build();
    }

}
