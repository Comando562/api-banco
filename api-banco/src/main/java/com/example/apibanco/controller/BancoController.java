package com.example.apibanco.controller;

import com.example.apibanco.model.response.Cliente;
import com.example.apibanco.model.response.ClienteDTO;
import com.example.apibanco.service.ClienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BancoController implements IApiDocBanco{

    private final ClienteServiceImpl clienteService;

    public BancoController(ClienteServiceImpl clienteService){
        this.clienteService = clienteService;
    }


    /*@Override
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }*/

    @Override
    public ResponseEntity<List<ClienteDTO>> getall() {
        return clienteService.listAll();
    }

    @Override
    public Cliente getClienteById(Integer id) {
        return clienteService.getClienteById(id);
    }

    @Override
    public ResponseEntity<Void> createCliente(Cliente cliente) {
        clienteService.createCliente(cliente);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateCliente(Integer id, Cliente cliente) {
        clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteCliente(Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
