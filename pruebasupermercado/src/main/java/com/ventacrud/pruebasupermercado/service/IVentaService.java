package com.ventacrud.pruebasupermercado.service;

import com.ventacrud.pruebasupermercado.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> traerVentas();

    VentaDTO crearVenta(VentaDTO ventaDTO);

    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);

    void eliminarVenta(Long id);

}
