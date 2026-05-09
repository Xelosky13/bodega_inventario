package com.proyecto_inventario.proyecto_inventario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.ClienteDTO;
import com.proyecto_inventario.proyecto_inventario.DTO.PedidoDTO;
import com.proyecto_inventario.proyecto_inventario.model.Cliente;
import com.proyecto_inventario.proyecto_inventario.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO convertirADTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setRut(cliente.getRut());
        if(cliente.getHistorialPedidos() != null){
            dto.setHistorialPedidos(cliente.getHistorialPedidos().stream()
            .map(pedido -> {
                PedidoDTO pDto = new PedidoDTO();
                pDto.setId(pedido.getId());
                pDto.setEstado(pedido.getEstado());
                pDto.setFechaPedido(pedido.getFechaPedido());
                return pDto;
            })
            .toList());  
        }
        return dto;
    }

    public List<ClienteDTO> obtenerTodos(){
        return clienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ClienteDTO buscarPorId(Integer id){
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return convertirADTO(cliente);
    }

    public ClienteDTO buscarPorRut(String rut){
        Cliente cliente = clienteRepository.findByRut(rut);
        if(cliente == null)
                throw new RuntimeException("Cliente no encontrado");
        return convertirADTO(cliente);              
    }

    public String eliminar (Integer id){
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(
                            "No se puede eliminar, el cliente no existe")
                        );
            clienteRepository.delete(cliente);
            return "El cliente " + cliente.getId() + "ha sido eliminado";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Integer id, Cliente cliente){
        Cliente actualizado = clienteRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Cliente no existe"));
        if(cliente.getRut() != null){
            actualizado.setRut(cliente.getRut());
        }
        if(cliente.getNombre() != null){
            actualizado.setNombre(cliente.getNombre());
        }
        if(cliente.getId() != null){
            actualizado.setId(cliente.getId());
        }
        if(cliente.getHistorialPedidos() != null){
            actualizado.getHistorialPedidos().clear();
            cliente.getHistorialPedidos().forEach(pedido -> {
                pedido.setCliente(actualizado);
                actualizado.getHistorialPedidos().add(pedido);
            });
        }
        return clienteRepository.save(actualizado);
    }


}
