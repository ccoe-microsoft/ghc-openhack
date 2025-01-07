package com.baeldung.springbootreact.controller;

import com.baeldung.springbootreact.domain.Client;
import com.baeldung.springbootreact.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientRepository clientRepository;

    public ClientsController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Get all clients.
     * 
     * @return a list of all clients
     */
    @GetMapping
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /**
     * Get a client by its ID.
     * 
     * @param id the ID of the client
     * @return the client with the specified ID
     */
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * Create a new client.
     * 
     * @param client the client to create
     * @return the created client
     * @throws URISyntaxException if the URI syntax is incorrect
     */
    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    }

    /**
     * Update an existing client.
     * 
     * @param id the ID of the client to update
     * @param client the updated client data
     * @return the updated client
     */
    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient = clientRepository.save(client);

        return ResponseEntity.ok(currentClient);
    }

    /**
     * Delete a client by its ID.
     * 
     * @param id the ID of the client to delete
     * @return a response entity indicating the result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Find a client by its email.
     * 
     * @param email the email of the client
     * @return the client with the specified email
     */
    @GetMapping("/email/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    /**
     * Find clients by their name.
     * 
     * @param name the name of the clients
     * @return a list of clients with the specified name
     */
    @GetMapping("/name/{name}")
    public List<Client> getClientsByName(@PathVariable String name) {
        return clientRepository.findByName(name).orElseThrow(RuntimeException::new);
    }
}
