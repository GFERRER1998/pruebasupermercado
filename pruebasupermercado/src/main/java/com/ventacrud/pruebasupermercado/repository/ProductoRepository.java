package com.ventacrud.pruebasupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ventacrud.pruebasupermercado.model.Producto;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findFirstByNombre(String nombre);

}
