package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.DetalleDTO;
import org.example.dto.VentaDTO;
import org.example.mapper.Mapper;
import org.example.modelos.DetalleVenta;
import org.example.modelos.Producto;
import org.example.modelos.Sucursal;
import org.example.modelos.Venta;
import org.example.repository.ProductoRepository;
import org.example.repository.SucursalRepository;
import org.example.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService implements VentaServiceIMP {

    private final VentaRepository ventaRepo;
    private final ProductoRepository productoRepo;
    private final SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> findVentas() {
        return ventaRepo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public VentaDTO findVenta(Long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        return Mapper.toDTO(venta);
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        Venta venta = new Venta();
        venta.setFecha(ventaDto.getFecha());
        venta.setEstado(ventaDto.getEstado());

        Sucursal sucursal = sucursalRepo.findById(ventaDto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        venta.setSucursal(sucursal);

        List<DetalleVenta> detalles = construirDetalles(ventaDto.getDetalle(), venta);
        venta.setDetalle(detalles);

        calcularTotal(venta);

        Venta guardada = ventaRepo.save(venta);

        return Mapper.toDTO(guardada);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {

        Venta existente = ventaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        existente.setFecha(ventaDto.getFecha());
        existente.setEstado(ventaDto.getEstado());

        Sucursal sucursal = sucursalRepo.findById(ventaDto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        existente.setSucursal(sucursal);

        List<DetalleVenta> detalles = construirDetalles(ventaDto.getDetalle(), existente);
        existente.setDetalle(detalles);

        calcularTotal(existente);

        Venta actualizada = ventaRepo.save(existente);

        return Mapper.toDTO(actualizada);
    }

    @Override
    public void borrarVenta(Long id) {

        if (!ventaRepo.existsById(id)) {
            throw new RuntimeException("Venta no encontrada");
        }

        ventaRepo.deleteById(id);
    }

    private List<DetalleVenta> construirDetalles(List<DetalleDTO> detalleDTOs, Venta venta) {

        return detalleDTOs.stream().map(d -> {

            Producto producto = productoRepo.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setNotas(d.getNotas());
            detalle.setVenta(venta);

            return detalle;

        }).toList();
    }

    private void calcularTotal(Venta venta) {

        BigDecimal total = venta.getDetalle().stream()
                .map(det -> det.getProducto()
                        .getPrecio()
                        .multiply(BigDecimal.valueOf(det.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venta.setTotalVenta(total);
    }
}
