package fr.su.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.su.bankapp.model.Client;
import fr.su.bankapp.repository.ClientRepository;

@RestController
@RequestMapping("/api/v1/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Get all clients from the repository
     * 
     * @return list of Clients
     */
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
