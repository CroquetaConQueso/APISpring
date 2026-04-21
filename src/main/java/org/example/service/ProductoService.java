package org.example.service;

import org.example.dto.ProductoDTO;
import org.example.exception.NotFoundException;
import org.example.mapper.Mapper;
import org.example.modelos.Producto;
import org.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements ProductoServiceIMP {

    @Autowired
    private ProductoRepository proRepo;

    @Override
    public List<ProductoDTO> findProductos() {
        return proRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO findProducto(Long id) {
        return Mapper.toDTO(proRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado el producto")));
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad()).build();

        return  Mapper.toDTO(proRepo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        Producto producto = proRepo.findById(id).orElseThrow(() ->new NotFoundException("No se ha podido encontrar el producto"));

        producto.setNombre(productoDto.getNombre());
        producto.setCategoria(productoDto.getCategoria());
        producto.setPrecio(productoDto.getPrecio());
        producto.setCantidad(productoDto.getCantidad());

        return Mapper.toDTO(proRepo.save(producto));
    }

    @Override
    public void borrarProducto(Long id) {
        if(!proRepo.existsById(id)){
            throw new NotFoundException("No se ha encontrado un producto con esa id");
        }else{
            proRepo.deleteById(id);
        }
    }
}
