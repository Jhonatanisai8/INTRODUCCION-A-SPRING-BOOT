package com.jhonatan.clase01Spring.controllers;

import com.jhonatan.clase01Spring.domain.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    private final List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(
            new Cliente(123, "Gerardo Lopez", "gerardol", "contrasena123"),
            new Cliente(456, "Alejandro Garcia", "alegarcia", "clave456"),
            new Cliente(789, "Laura Sanchez", "lauras", "secreto12312"),
            new Cliente(234, "Daniel Peralta", "danielp", "daniel234")
    ));

    @GetMapping("clientes")
    public List<Cliente> getClientes() {
        return listaClientes;
    }

    @GetMapping("clientes/{usuario}")
    public Cliente getCliente(@PathVariable String usuario) {
        return listaClientes.stream().filter(
                cliente -> cliente.getNombreUsuario().equalsIgnoreCase(usuario)
        ).findFirst().orElse(null);
    }


}
