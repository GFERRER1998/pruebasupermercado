package com.ventacrud.pruebasupermercado.service;

import com.ventacrud.pruebasupermercado.dto.SucursalDTO;
import com.ventacrud.pruebasupermercado.mappers.Mapper;
import com.ventacrud.pruebasupermercado.repository.SucursalRepository;
import com.ventacrud.pruebasupermercado.model.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {

        Sucursal suc = Sucursal.builder()

                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal suc = repo.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        suc.setNombre(sucursalDTO.getNombre());
        suc.setDireccion(sucursalDTO.getDireccion());
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Sucursal no encontrada");
        }
        repo.deleteById(id);
    }
}
