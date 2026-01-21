package com.ventacrud.pruebasupermercado.service;

import java.util.List;
import com.ventacrud.pruebasupermercado.dto.SucursalDTO;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();

    SucursalDTO crearSucursal(SucursalDTO sucursalDTO);

    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO);

    void eliminarSucursal(Long id);

}
