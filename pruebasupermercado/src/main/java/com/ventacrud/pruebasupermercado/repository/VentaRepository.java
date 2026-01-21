package com.ventacrud.pruebasupermercado.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ventacrud.pruebasupermercado.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
