package com.example.apibanco.model.repository;

import com.example.apibanco.model.response.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {
}
