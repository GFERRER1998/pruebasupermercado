package com.ventacrud.pruebasupermercado.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Long idSucursal;
    private List<DetalleVentaDTO> detalle;

    private Double total;

}
