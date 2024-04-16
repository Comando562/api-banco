package com.example.apibanco.service;

import com.example.apibanco.exception.ClienteSrvInternalServErrorException;
import com.example.apibanco.model.repository.ClienteRepository;
import com.example.apibanco.model.response.Cliente;
import com.example.apibanco.model.response.ClienteDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ClienteServiceImpl implements IClienteService{
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    private final CircuitBreakerFactory circtBreakFactory;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper,
                              CircuitBreakerFactory circtBreakFactory) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.circtBreakFactory = circtBreakFactory;
    }

    public List<Cliente> getAllClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public Cliente getClienteById(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            return optionalCliente.get();
        } else {
            throw new NoSuchElementException("Cliente no encontrado con el ID: " + id);
        }
    }

    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void updateCliente(Integer id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteSrvInternalServErrorException("No se ha encontrado el cliente por id");
        }
        cliente.setId(id); // Asignamos el ID recibido al cliente para asegurarnos de que se actualice el cliente correcto
        clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteSrvInternalServErrorException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<ClienteDTO>> listAll() {
        CircuitBreaker circtBreakListAll = circtBreakFactory.create("circtBreakListAll");

        List<ClienteDTO> clienteDTOList = circtBreakListAll.run(
                this::getAllCliente,throwable -> getDefaultAllBancoList());

        if (clienteDTOList.isEmpty()) {
            throw new ClienteSrvInternalServErrorException("The query to permiso table return empty list.");
        }
        return ResponseEntity.ok(clienteDTOList);
    }

    private List<ClienteDTO> getAllCliente() {

        try {
            List<Cliente> permisoList = (List<Cliente>) clienteRepository.findAll();

            return permisoList.
                    stream().
                    map(permiso -> modelMapper.map(permiso, ClienteDTO.class)).
                    toList();
        } catch(Exception ex) {
            log.error("Error in [{}] in getAllCliente: {}",this.getClass().getName(),ex.getMessage());
            throw new ClienteSrvInternalServErrorException(ex.getMessage());
        }
    }

    private List<ClienteDTO> getDefaultAllBancoList() {
        List<ClienteDTO> permisoDTOList = new ArrayList<>();
        permisoDTOList.add(getDefaultBancoDTO());
        return permisoDTOList;
    }

    private ClienteDTO getDefaultBancoDTO() {
        ClienteDTO permisoDTO = new ClienteDTO();
        permisoDTO.setId(1);
        permisoDTO.setNombre("Default Nombre");
        permisoDTO.setApellido("Esta es una descripcion para la respuesta por default Banco");
        permisoDTO.setTipo("Tipo de Cliente Individual / Empresarial");
        return permisoDTO;
    }
}
