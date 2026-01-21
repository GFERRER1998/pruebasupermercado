package com.ventacrud.pruebasupermercado.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ventacrud.pruebasupermercado.dto.ProductoDTO;
import com.ventacrud.pruebasupermercado.model.Producto;
import com.ventacrud.pruebasupermercado.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    ProductoRepository repo;

    @InjectMocks
    ProductoService service;

    @Test
    void traerProductos_ShouldReturnListOfDtos() {
        Producto p1 = new Producto();
        p1.setId(1L);
        p1.setNombre("P1");

        Producto p2 = new Producto();
        p2.setId(2L);
        p2.setNombre("P2");

        when(repo.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<ProductoDTO> result = service.traerProductos();

        assertEquals(2, result.size());
        assertEquals("P1", result.get(0).getNombre());
        assertEquals("P2", result.get(1).getNombre());
    }
}
