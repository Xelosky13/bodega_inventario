package com.proyecto_inventario.proyecto_inventario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.DespachoDTO;
import com.proyecto_inventario.proyecto_inventario.model.Despacho;
import com.proyecto_inventario.proyecto_inventario.repository.DespachoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DespachoService {

    @Autowired
    private DespachoRepository despachoRepository;

    public DespachoDTO convertirADTO(Despacho despacho){
        DespachoDTO dto =new DespachoDTO();
        dto.setId(despacho.getId());
        dto.setFechaSalida(despacho.getFechaSalida());
        dto.setNombreCliente(despacho.getPicking().getPedido().getCliente().getNombre());
        dto.setPatenteVehiculo(despacho.getPatenteVehiculo());
        dto.setTransportista(despacho.getTransportista());
        if(despacho.getPicking() != null)
            dto.setNombreCliente(despacho.getPicking().getPedido().getCliente().getNombre());  
        return dto;
    }

    public List<DespachoDTO> obtenerTodos(){
        return despachoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DespachoDTO buscarPorId(Integer id){
        Despacho despacho = despachoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));
        return convertirADTO(despacho);
    }

    public String eliminar(Integer id){
        try {
            Despacho despacho = despachoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(
                                "No se pudo eliminar, despacho no encontrado")
                            );
            return "El despacho " + despacho.getId() + "ha sido eliminado";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Despacho guardarDespacho(Despacho despacho){
        return despachoRepository.save(despacho);
    }

    public Despacho actualizarDespacho(Integer id, Despacho despacho){
        Despacho despacho2 = despachoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Despacho no encontrado"));
        if(despacho.getFechaSalida() != null){
            despacho2.setFechaSalida(despacho.getFechaSalida());
        }    
        if(despacho.getId() != null){
            despacho2.setId(despacho.getId());
        }
        if(despacho.getObservaciones() != null){
            despacho2.setObservaciones(despacho.getObservaciones());
        }
        if(despacho.getPatenteVehiculo() != null){
            despacho2.setPatenteVehiculo(despacho.getPatenteVehiculo());
        }
        if(despacho.getPicking() != null){
            despacho2.setPicking(despacho.getPicking());
        }
        if(despacho.getTransportista() != null){
            despacho2.setTransportista(despacho.getTransportista());
        }
        return despachoRepository.save(despacho2);
    }

    public List<DespachoDTO> buscarPorCamionYFecha(String patente, LocalDateTime fecha){
        return despachoRepository.buscarPorCamionYFecha(patente, fecha).stream()
                .map(this::convertirADTO)
                .toList();
    }
    
}
