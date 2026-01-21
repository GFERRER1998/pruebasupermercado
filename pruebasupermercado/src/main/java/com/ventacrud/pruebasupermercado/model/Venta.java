package com.ventacrud.pruebasupermercado.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import lombok.Builder;
import jakarta.persistence.FetchType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Venta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private LocalDate fecha;
   private String estado;

   @ManyToOne
   private Sucursal sucursal;

   @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
   @Builder.Default
   @lombok.ToString.Exclude
   private List<DetalleVenta> detalle = new ArrayList<>();
   private double total;
}
