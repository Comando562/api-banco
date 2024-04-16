package com.example.apibanco.controller;

import com.example.apibanco.model.response.Cliente;
import com.example.apibanco.model.response.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public interface IApiDocBanco {

    @GetMapping("/clientes")
    @Operation(summary = "Obtener todos los clientes")
    ResponseEntity<List<ClienteDTO>> getall();

    @GetMapping("/clientes/{id}")
    @Operation(summary = "Obtener un cliente por ID")
    @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve los detalles del cliente.")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    Cliente getClienteById(
            @Parameter(description = "ID del cliente a obtener", required = true)
            @PathVariable Integer id);

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente.")
    ResponseEntity<Void> createCliente(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo cliente", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = Cliente.class)))
            @RequestBody Cliente cliente);

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente.")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    ResponseEntity<Void> updateCliente(
            @Parameter(description = "ID del cliente a actualizar", required = true)
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del cliente", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = Cliente.class)))
            @RequestBody Cliente cliente);

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un cliente existente")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente. No hay contenido en la respuesta.")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    ResponseEntity<Void> deleteCliente(
            @Parameter(description = "ID del cliente a eliminar", required = true)
            @PathVariable Integer id);


}
