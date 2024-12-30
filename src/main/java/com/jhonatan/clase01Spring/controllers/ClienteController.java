package com.jhonatan.clase01Spring.controllers;

import com.jhonatan.clase01Spring.domain.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    private List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(
            new Cliente(123, "Gerardo Lopez", "gerardol", "contrasena123"),
            new Cliente(456, "Alejandro Garcia", "alegarcia", "clave456"),
            new Cliente(789, "Laura Sanchez", "lauras", "secreto12312"),
            new Cliente(234, "Daniel Peralta", "danielp", "daniel234")
    ));

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return listaClientes;
    }

    @GetMapping("/clientes/{usuario}")
    public Cliente getCliente(@PathVariable String usuario) {
        return listaClientes.stream().filter(
                cliente -> cliente.getNombreUsuario().equalsIgnoreCase(usuario)
        ).findFirst().orElse(null);
    }


    @PostMapping("/clientes")
    public Cliente postCliente(@RequestBody Cliente cliente) { //recibe un valor de tipo json y lo transforma
        this.listaClientes.add(cliente);
        return cliente;
    }

    @PutMapping("/clientes")
    public Cliente putCliente(@RequestBody Cliente cliente) {
        return listaClientes.stream()
                .filter(cliente1 -> cliente1.getIdCliente() == cliente.getIdCliente())
                .findFirst()//encuentra el primer valor
                .map(cliente1 -> {
                            cliente1.setNombre(cliente.getNombre());
                            cliente1.setNombreUsuario(cliente.getNombreUsuario());
                            cliente1.setPassword(cliente.getPassword());
                            return cliente1;
                        }
                ).orElse(null);
    }
}
