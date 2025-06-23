package com.gimnasioconfig.gimnasio_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasioconfig.gimnasio_api.models.Cliente;
import com.gimnasioconfig.gimnasio_api.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> all(){
        return clienteRepository.findAll();
    }

    public List<Cliente> allActivos(){
        return clienteRepository.findByActivoTrue();
    }

    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente findByIdAndActivo(Long id){
        return clienteRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado o inactivo"));
    }

    public Cliente save(Cliente clase){
        return (Cliente) clienteRepository.save(clase);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public void desactivar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setActivo(false);
        clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteData){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente con id" + id));

        cliente.setNombre(clienteData.getNombre());
        cliente.setApellido(clienteData.getApellido());
        cliente.setTelefono(clienteData.getTelefono());
        cliente.setFechaRegistro(clienteData.getFechaRegistro());
        cliente.setActivo(clienteData.getActivo());

        return clienteRepository.save(cliente);
    }
}
