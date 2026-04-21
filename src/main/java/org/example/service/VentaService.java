package org.example.service;

import org.example.dto.VentaDTO;
import org.example.mapper.Mapper;
import org.example.modelos.Venta;
import org.example.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VentaService implements VentaServiceIMP {
    @Autowired
    private VentaRepository repoVen;

    @Override
    public List<VentaDTO> findVentas() {
        return repoVen.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public VentaDTO findVenta(Long id) {
        Venta venta = repoVen.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        return Mapper.toDTO(venta);
    }

    @Override
    public VentaDTO crearVenta(Venta venta) {

        calcularTotal(venta);

        Venta guardada = repoVen.save(venta);

        return Mapper.toDTO(guardada);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, Venta venta) {

        Venta existente = repoVen.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        existente.setFecha(venta.getFecha());
        existente.setEstado(venta.getEstado());
        existente.setSucursal(venta.getSucursal());
        existente.setDetalle(venta.getDetalle());

        // ✅ Recalcular total
        calcularTotal(existente);

        Venta actualizada = repoVen.save(existente);

        return Mapper.toDTO(actualizada);
    }

    @Override
    public void borrarVenta(Long id) {

        if (!repoVen.existsById(id)) {
            throw new RuntimeException("Venta no encontrada");
        }

        repoVen.deleteById(id);
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
