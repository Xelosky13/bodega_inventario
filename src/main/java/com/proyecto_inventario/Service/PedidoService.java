package com.proyecto_inventario.Service;

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

    private PedidoDTO convertirDTO(Pedido pedido){
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

    }
    


}
