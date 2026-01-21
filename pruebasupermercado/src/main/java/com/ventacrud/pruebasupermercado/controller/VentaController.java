package com.ventacrud.pruebasupermercado.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.net.URI;
import com.ventacrud.pruebasupermercado.service.IVentaService;
import com.ventacrud.pruebasupermercado.dto.VentaDTO;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas() {
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO dto) {
        VentaDTO created = ventaService.crearVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public VentaDTO actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO dto) {
        return ventaService.actualizarVenta(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
