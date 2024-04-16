package com.example.apibanco.service;

import com.example.apibanco.model.response.ClienteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClienteService {
    ResponseEntity<List<ClienteDTO>> listAll();
}
