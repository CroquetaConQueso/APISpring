package org.example.service;

import org.example.dto.ProductoDTO;
import org.example.modelos.Producto;

import java.util.List;

public interface ProductoServiceIMP {
    List<ProductoDTO> findProductos();
    ProductoDTO findProducto(Long id);
    ProductoDTO findNameProducto(String nombre);
    ProductoDTO crearProducto(ProductoDTO producto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO producto);
    void  borrarProducto(Long id);
}
