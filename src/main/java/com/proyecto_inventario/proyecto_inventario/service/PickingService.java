package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.PickingDTO;
import com.proyecto_inventario.proyecto_inventario.model.Operario;
import com.proyecto_inventario.proyecto_inventario.model.Pedido;
import com.proyecto_inventario.proyecto_inventario.model.Picking;
import com.proyecto_inventario.proyecto_inventario.repository.OperarioRepository;
import com.proyecto_inventario.proyecto_inventario.repository.PedidoRepository;
import com.proyecto_inventario.proyecto_inventario.repository.PickingRepository;

@Service
public class PickingService {

    @Autowired
    private PickingRepository pickingRepository;

    @Autowired
    private OperarioRepository operarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PickingDTO> listarTodos() {
        return pickingRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PickingDTO obtenerPorId(Integer id) {
        Picking picking = pickingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Picking no encontrado con id: " + id));
        return convertirADTO(picking);
    }

    public PickingDTO crear(PickingDTO dto) {
        Operario operario = operarioRepository.findById(dto.getOperarioId())
                .orElseThrow(() -> new RuntimeException(
                        "Operario no encontrado con id: " + dto.getOperarioId()));

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException(
                        "Pedido no encontrado con id: " + dto.getPedidoId()));

        Picking picking = new Picking();
        picking.setOperario(operario);
        picking.setPedido(pedido);
        picking.setFechaInicio(dto.getFechaInicio());
        picking.setEstado(dto.getEstado());

        return convertirADTO(pickingRepository.save(picking));
    }

    public PickingDTO actualizar(Integer id, PickingDTO dto) {
        Picking picking = pickingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Picking no encontrado con id: " + id));

        Operario operario = operarioRepository.findById(dto.getOperarioId())
                .orElseThrow(() -> new RuntimeException(
                        "Operario no encontrado con id: " + dto.getOperarioId()));

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException(
                        "Pedido no encontrado con id: " + dto.getPedidoId()));

        picking.setOperario(operario);
        picking.setPedido(pedido);
        picking.setFechaInicio(dto.getFechaInicio());
        picking.setEstado(dto.getEstado());

        return convertirADTO(pickingRepository.save(picking));
    }

    public void eliminar(Integer id) {
        if (!pickingRepository.existsById(id)) {
            throw new RuntimeException("Picking no encontrado con id: " + id);
        }
        pickingRepository.deleteById(id);
    }

    private PickingDTO convertirADTO(Picking picking) {
        PickingDTO dto = new PickingDTO();
        dto.setId(picking.getId());
        dto.setOperarioId(picking.getOperario().getId());
        dto.setNombreOperario(picking.getOperario().getNombre());
        dto.setPedidoId(picking.getPedido().getId());
        dto.setFechaInicio(picking.getFechaInicio());
        dto.setEstado(picking.getEstado());
        return dto;
    }
}