package fr.su.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import fr.su.bankapp.exception.ResourceNotFoundException;
import fr.su.bankapp.model.Client;
import fr.su.bankapp.repository.ClientRepository;

@Controller
@RequestMapping("/api/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Get all clients from the repository
     * 
     * @return list of Clients
     */
    @ResponseBody
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /** Get a Client by his account id */
    @ResponseBody
    @GetMapping(path = "/client/{id}")
    public Client getClientById(@PathVariable("id") long id) {

        // TODO logging : LOG.info("Reading client with id " + id + " from database.");
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
    }

    @ResponseBody
    @GetMapping(path = "/client/{firstname}/{lastname}/{email}")
    public Client getClientName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
            @PathVariable("email") String email) {

        return clientRepository.findByIdentity(firstname, lastname, email);
    }


    /** Add a client to the database with firstname, lastname and email */
    @ResponseBody
    @RequestMapping(path = "/secured/create/{lastName}/{firstName}/{email}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName,
            @PathVariable("email") String email) {
        Client savedClient = clientRepository.save(new Client(firstName, lastName, email));

        // TODO logging : LOG.info(savedClient.toString() + " successfully saved into
        // DB");

        return savedClient.getId();
    }

    /** Add a client to the database with full parameters */
    @ResponseBody
    @RequestMapping(path = "/secured/create/{lastName}/{firstName}/{email}/{balance}/{overdraft}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName,
            @PathVariable("email") String email,
            @PathVariable("balance") Double balance, @PathVariable("overdraft") Double overdraft) {
        Client savedClient = clientRepository.save(new Client(firstName, lastName, email));

        // TODO logging : LOG.info(savedClient.toString() + " successfully saved into
        // DB");

        return savedClient.getId();
    }

    /** Withdraw */
    @ResponseBody
    @RequestMapping(path = "/Withdraw/{id}/{amount}")
    public Client withdraw(@PathVariable("id") Long id, @PathVariable("amount") Double amount) {
        Client c = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
        c.setBalance(c.getBalance()-amount);
        clientRepository.save(c);
        return c;
    }

    /** Deposit */
    @ResponseBody
    @RequestMapping(path = "/Deposit/{id}/{amount}")
    public Client deposit(@PathVariable("id") Long id, @PathVariable("amount") Double amount) {
        Client c = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
        c.setBalance(c.getBalance()+amount);
        clientRepository.save(c);
        return c;
    }
}
