package com.example.apibanco.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(name = "Cliente")
@Setter
@Getter
@ToString
public class ClienteDTO {

    @Schema(type = "integer", description = "ID del cliente", example = "1")
    @JsonProperty("id")
    private Integer id;

    @Schema(type = "string", description = "Nombre del cliente", example = "Juan")
    @JsonProperty("nombre")
    private String nombre;

    @Schema(type = "string", description = "Apellido del cliente", example = "Perez")
    @JsonProperty("apellido")
    private String apellido;

    @Schema(type = "string", description = "Tipo de  cliente", example = "Individual / Empresarial")
    @JsonProperty("tipo")
    private String tipo;


}
