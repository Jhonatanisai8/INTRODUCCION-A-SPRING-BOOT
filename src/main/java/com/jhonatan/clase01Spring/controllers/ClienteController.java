package com.jhonatan.clase01Spring.controllers;

import com.jhonatan.clase01Spring.domain.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(new Cliente(123, "Gerardo Lopez", "gerardol", "contrasena123"), new Cliente(456, "Alejandro Garcia", "alegarcia", "clave456"), new Cliente(789, "Laura Sanchez", "lauras", "secreto12312"), new Cliente(234, "Daniel Peralta", "danielp", "daniel234")));

    //@RequestMapping(method = RequestMethod.GET) // otra forma de especificar la solicitud http
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(listaClientes);
        //return listaClientes;
    }

    //@RequestMapping(value = "/{usuario}", method = RequestMethod.GET)
    @GetMapping("/{usuario}")
    public ResponseEntity<?> getCliente(@PathVariable String usuario) {
        Cliente cliente = null;
        Optional<Cliente> clienteEncontrado = listaClientes.stream().filter(client -> client.getNombreUsuario().equalsIgnoreCase(usuario)).findFirst();
        if (clienteEncontrado.isPresent()) {
            cliente = clienteEncontrado.get();
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con nombre de Usuario: " + usuario);  // codigo de error 404

    }


    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Cliente cliente) { //recibe un valor de tipo json y lo transforma
        this.listaClientes.add(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente: " + cliente.getNombre());
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Cliente clienteB) {
        //buscamos el cliente por el id
        Optional<Cliente> clienteBuscado = this.listaClientes.stream()
                .filter(cliente -> cliente.getIdCliente() == clienteB.getIdCliente())
                .findFirst();
        if (clienteBuscado.isPresent()) {
            Cliente clienteExistente = clienteBuscado.get();
            //actualizamos sus datos
            clienteExistente.setNombre(clienteB.getNombre());
            clienteExistente.setNombreUsuario(clienteB.getNombreUsuario());
            clienteExistente.setPassword(clienteB.getPassword());
            //return ResponseEntity.ok("Cliente modificado satisfactoriamente: " + clienteExistente.getNombre());
            return ResponseEntity.noContent().build();
        }
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un cliente con el ID: " + clienteB.getIdCliente());
        return ResponseEntity.notFound().build();
    }

    //@RequestMapping(value = "/{idCliente}", method = RequestMethod.DELETE)
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable int idCliente) {
        Optional<Cliente> clienteBuscado = listaClientes.stream().filter(cliente -> cliente.getIdCliente() == idCliente).findFirst();
        if (clienteBuscado.isPresent()) {
            this.listaClientes.remove(clienteBuscado.get());
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado correctamente: " + idCliente);
            return ResponseEntity.noContent().build();
        }
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontradoo con el ID: " + idCliente);
        return ResponseEntity.notFound().build();
    }

    //    @RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Cliente cliente) {
        Optional<Cliente> clienteBuscado = listaClientes.stream()
                .filter(cliente1 -> cliente1.getIdCliente() == cliente.getIdCliente())
                .findFirst();
        if (clienteBuscado.isPresent()) {
            Cliente clienteExistente = clienteBuscado.get();
            if (cliente.getNombre() != null) {
                clienteExistente.setNombre(cliente.getNombre());
            }
            if (cliente.getNombreUsuario() != null) {
                clienteExistente.setNombreUsuario(cliente.getNombreUsuario());
            }
            if (cliente.getPassword() != null) {
                clienteExistente.setPassword(cliente.getPassword());
            }
            return ResponseEntity.ok("Cliente modificado correctamente: " + clienteExistente.getNombreUsuario());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + cliente.getIdCliente());
    }
}
