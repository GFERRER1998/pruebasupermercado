package com.ventacrud.pruebasupermercado.service;

import com.ventacrud.pruebasupermercado.dto.ProductoDTO;
import com.ventacrud.pruebasupermercado.mappers.Mapper;
import com.ventacrud.pruebasupermercado.model.Producto;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ventacrud.pruebasupermercado.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService {

    @Autowired

    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream()
                .map(Mapper::toDTO)
                .toList();

    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {

        var prod = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {

        Producto prod = repo.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        prod.setNombre(productoDTO.getNombre());
        prod.setCategoria(productoDTO.getCategoria());
        prod.setPrecio(productoDTO.getPrecio());
        prod.setCantidad(productoDTO.getCantidad());
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repo.deleteById(id);
    }

}
