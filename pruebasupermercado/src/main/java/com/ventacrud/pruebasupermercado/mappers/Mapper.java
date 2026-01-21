package com.ventacrud.pruebasupermercado.mappers;

import java.util.stream.Collectors;
import com.ventacrud.pruebasupermercado.dto.DetalleVentaDTO;
import com.ventacrud.pruebasupermercado.dto.ProductoDTO;
import com.ventacrud.pruebasupermercado.dto.VentaDTO;
import com.ventacrud.pruebasupermercado.model.Producto;
import com.ventacrud.pruebasupermercado.model.Venta;

import com.ventacrud.pruebasupermercado.model.Sucursal;
import com.ventacrud.pruebasupermercado.dto.SucursalDTO;

public class Mapper {

    public static ProductoDTO toDTO(Producto p) {
        if (p == null)
            return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    public static VentaDTO toDTO(Venta venta) {
        if (venta == null)
            return null;

        var detalle = venta.getDetalle().stream().map(det -> DetalleVentaDTO.builder()
                .id(det.getProd().getId())
                .nombreProd(det.getProd().getNombre())
                .cantProd(det.getCantProd())
                .precio(det.getPrecio())
                .subtotal(det.getPrecio() * det.getCantProd())
                .build()).collect(Collectors.toList());

        var total = detalle.stream()
                .mapToDouble(det -> det.getSubtotal() != null ? det.getSubtotal() : 0.0)
                .sum();

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }

    public static SucursalDTO toDTO(Sucursal s) {
        if (s == null)
            return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }
}
