package com.gimnasioconfig.gimnasio_api.controllers;

import com.gimnasioconfig.gimnasio_api.dto.ClienteDTO;
import com.gimnasioconfig.gimnasio_api.models.Cliente;
import com.gimnasioconfig.gimnasio_api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        return clienteService.all()
                .stream()
                .map(ClienteDTO::fromEntity)
                .collect(Collectors.toList());
    }
    @GetMapping("/activos")
    public ResponseEntity<List<ClienteDTO>> listarActivos() {
        List<ClienteDTO> dtos = clienteService.allActivos().stream()
                .map(ClienteDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(c -> ResponseEntity.ok(ClienteDTO.fromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<ClienteDTO> getActivoPorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findByIdAndActivo(id);
            ClienteDTO dto = ClienteDTO.fromEntity(cliente);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteData) {
        try {
            Cliente updatedCliente = clienteService.update(id, clienteData);
            ClienteDTO dto = ClienteDTO.fromEntity(updatedCliente);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivarCliente(@PathVariable Long id) {
        clienteService.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}