package com.proyecto_inventario.proyecto_inventario.Service;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.ItemPedidoDTO;
import com.proyecto_inventario.proyecto_inventario.model.ItemPedido;
import com.proyecto_inventario.proyecto_inventario.repository.ItemPedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    public ItemPedidoDTO  convertirADTO(ItemPedido itemPedido){
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setCantidad(itemPedido.getCantidad());
        dto.setNombreProducto(itemPedido.getProducto().getNombre());
        dto.setCantidad(itemPedido.getCantidad());
        return dto;
    }

    public List<ItemPedidoDTO> obtenerTodos(Integer id){
        return itemPedidoRepository.findAll().stream()
                .map(this :: convertirADTO)
                .toList();
    }

    public ItemPedidoDTO buscarPorId(Integer id){
        ItemPedido item = itemPedidoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        return convertirADTO(item);
    }

    public String eliminar (Integer id){
        try {
            ItemPedido item = itemPedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                    "No se puede eliminar, el ItemPedido no existe")
            );
            itemPedidoRepository.delete(item);
            return "El Item " + item.getId() + "ha sido eliminado";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public ItemPedido guardarItemPedido(ItemPedido itemPedido){
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido actualizarItemPedido(Integer id, ItemPedido itemPedido){
        ItemPedido actualizado = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        if(itemPedido.getId() != null){
            actualizado.setId(itemPedido.getId());
        }
        if(itemPedido.getCantidad() != null){
            actualizado.setId(itemPedido.getCantidad());
        }
        if(itemPedido.getProducto() != null){
            actualizado.setProducto(itemPedido.getProducto());
        }
        return itemPedidoRepository.save(actualizado);  
    }

    


}
