package com.ventacrud.pruebasupermercado.service;

import com.ventacrud.pruebasupermercado.dto.VentaDTO;
import com.ventacrud.pruebasupermercado.exception.NotFoundException;
import com.ventacrud.pruebasupermercado.dto.DetalleVentaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ventacrud.pruebasupermercado.repository.VentaRepository;
import com.ventacrud.pruebasupermercado.repository.SucursalRepository;
import com.ventacrud.pruebasupermercado.repository.ProductoRepository;
import com.ventacrud.pruebasupermercado.mappers.Mapper;
import com.ventacrud.pruebasupermercado.model.Venta;
import com.ventacrud.pruebasupermercado.model.DetalleVenta;
import com.ventacrud.pruebasupermercado.model.Sucursal;
import java.util.ArrayList;
import com.ventacrud.pruebasupermercado.model.Producto;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private SucursalRepository sucursalRepo;
    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();
        VentaDTO dto;
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }
        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        // Validaciones
        if (ventaDto == null)
            throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null)
            throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);

        if (vent.getDetalle() == null) {
            vent.setDetalle(new ArrayList<>());
        }

        double totalCalculado = 0.0;

        List<DetalleVenta> detallesNuevos = new ArrayList<>();

        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()) {

            Producto p = productoRepo.findFirstByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null) {
                throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());
            }

            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setPrecio(p.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detallesNuevos.add(detalleVent);

            totalCalculado += (detalleVent.getPrecio() * detalleVent.getCantProd());
        }

        vent.setDetalle(detallesNuevos);
        vent.setTotal(totalCalculado);

        Venta ventaGuardada = ventaRepo.save(vent);

        return Mapper.toDTO(ventaGuardada);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null)
            throw new RuntimeException("Venta no encontrada");

        if (ventaDTO.getFecha() != null) {
            v.setFecha(ventaDTO.getFecha());
        }
        if (ventaDTO.getEstado() != null) {
            v.setEstado(ventaDTO.getEstado());
        }

        if (ventaDTO.getTotal() != null) {
            v.setTotal(ventaDTO.getTotal());
        }

        if (ventaDTO.getIdSucursal() != null) {
            Sucursal suc = sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (suc == null)
                throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepo.save(v);
        VentaDTO vs = Mapper.toDTO(v);
        return vs;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) {
            throw new RuntimeException("Venta no encontrada");
        }
        ventaRepo.delete(v);

    }

}
