package com.proyecto_inventario.proyecto_inventario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.ItemPedidoDTO;
import com.proyecto_inventario.proyecto_inventario.DTO.PedidoDTO;
import com.proyecto_inventario.proyecto_inventario.model.Pedido;
import com.proyecto_inventario.proyecto_inventario.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDTO convertirADTO(Pedido pedido){
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setEstado(pedido.getEstado());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setNombreCliente(pedido.getCliente().getNombre());
        List<ItemPedidoDTO> itemsDTOs = pedido.getItems().stream().map(item ->{
            ItemPedidoDTO itemDto = new ItemPedidoDTO();
            itemDto.setNombreProducto(item.getProducto().getNombre());
            itemDto.setCantidad(item.getCantidad());
            return itemDto;
        }).toList();
        dto.setItems(itemsDTOs);
        return dto;
    }

    public List<PedidoDTO> obtenerTodos(){
        return pedidoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PedidoDTO buscarPorId(Integer id){
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return convertirADTO(pedido);
    }

    public String eliminar(Integer id){
        try {
            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se puede eliminar, el pedido "+ id + "eliminado"));
            pedidoRepository.delete(pedido);
            return "El pedido " + pedido.getId() + "ha sido eliminado";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public Pedido guardarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(Integer id,Pedido pedido){
        Pedido pedido2 = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no existe"));
        if(pedido.getCliente() != null){
            pedido2.setCliente(pedido.getCliente()); 
        }
        if(pedido.getEstado() != null){
            pedido2.setEstado(pedido.getEstado());
        }
        if(pedido.getFechaPedido() != null){
            pedido2.setFechaPedido(pedido.getFechaPedido());
        }
        if(pedido.getId() != null){
            pedido2.setItems(pedido.getItems());
        }
        return pedidoRepository.save(pedido);
    }

    public List<PedidoDTO> buscarPendientesPorCliente(String rut){
        return pedidoRepository.buscarPendientesPorCliente(rut).stream()
            .map(this :: convertirADTO)
            .toList();
    }
}
