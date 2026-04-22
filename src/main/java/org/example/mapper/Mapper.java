package org.example.mapper;

import org.example.dto.DetalleDTO;
import org.example.dto.ProductoDTO;
import org.example.dto.SucursalDTO;
import org.example.dto.VentaDTO;
import org.example.modelos.Producto;
import org.example.modelos.Sucursal;
import org.example.modelos.Venta;

import java.math.BigDecimal;
import java.util.List;

public class Mapper {
    public static ProductoDTO toDTO(Producto p){
        if(p == null) return null;

        return ProductoDTO.builder()
                .idProducto(p.getIdProducto())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    public static SucursalDTO toDTO(Sucursal s){
        if(s == null) return null;

        return SucursalDTO.builder()
                .idSucursal(s.getIdSucursal())
                .nombre(s.getNombreSucursal())
                .direccion(s.getDireccion())
                .build();
    }

    public static VentaDTO toDTO(Venta v){
        if(v==null) return null;

        List<DetalleDTO> listaDe = v.getDetalle().stream().map(det ->
                        DetalleDTO.builder()
                                .idDetalle(det.getIdDetalle())
                                .idProducto(det.getProducto().getIdProducto())
                                .cantidad(det.getCantidad())
                                .notas(det.getNotas())
                                .subTotal(det.getProducto().getPrecio().multiply(BigDecimal.valueOf(det.getCantidad())))
                                .build())
                .toList();

        BigDecimal total = listaDe.stream().map( DetalleDTO::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return VentaDTO.builder()
                .idVenta(v.getIdVenta())
                .fecha(v.getFecha())
                .estado(v.getEstado())
                .idSucursal(v.getSucursal().getIdSucursal())
                .detalle(listaDe)
                .totalVenta(total)
                .build();
    }

    public static Venta toEntity(VentaDTO dto){
        if(dto == null) return null;

        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setFecha(dto.getFecha());
        venta.setEstado(dto.getEstado());

        if(dto.getIdSucursal() != null){
            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal(dto.getIdSucursal());
            venta.setSucursal(sucursal);
        }

        return venta;
    }


}
