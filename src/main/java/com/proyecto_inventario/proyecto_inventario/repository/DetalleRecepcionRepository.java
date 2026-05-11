package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proyecto_inventario.proyecto_inventario.model.DetalleRecepcion;
import com.proyecto_inventario.proyecto_inventario.model.Producto;

@Repository
public interface DetalleRecepcionRepository extends JpaRepository<DetalleRecepcion, Integer> {

    public DetalleRecepcion findDetalleRecepcionById(Integer Id);

    public List<DetalleRecepcion> findByEstado(String estado);

    public DetalleRecepcion findByProducto(Producto producto);

    // todo - agregar enum de estado con lista de estado posible
    @Query("SELECT d from DetalleRecepcion d WHERE d.estado=?1")
    public ArrayList<DetalleRecepcion> buscarDetallePorEstado(String estado);

    @Query("SELECT d FROM DetalleRecepcion d WHERE d.cantidad>?1")
    public ArrayList<DetalleRecepcion> buscarDetallePorCantidad(Integer cantidad);

}
