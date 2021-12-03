package fr.su.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.su.bankapp.exception.ResourceNotFoundException;
import fr.su.bankapp.model.Client;
import fr.su.bankapp.repository.ClientRepository;

/**
 * Main routes class for Rest requests.
 * TODO :
 * ✅ get a list of all clients
 * ✅ get a client with account id
 * ❌ check if client exists in database (with mail, firstname and lastName)
 * ✅ add a client to the database
 * ❌ remove a client from the database
 */
@RestController
@RequestMapping("/api/")
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

    /** Get an Client by his account id */
    @ResponseBody
    @GetMapping(path = "/client/{id}")
    public Client getClientById(@PathVariable("id") long id) {

        return clientRepository.findById(id).map(client -> {
            // TODO logging : LOG.info("Reading client with id " + id + " from database.");
            return client;
        }).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
    }

    // /** Get an Client by his firstname, lastname and email */
    // @ResponseBody
    // @GetMapping(path = "/client/{firstname}/{lastname}/{email}")
    // public Client getClientName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
    //         @PathVariable("email") String email) {

    //     return clientRepository.findByName(firstname, lastname, email).map(client -> {
    //         // TODO logging : LOG.info("Reading client with id " + id + " from database.");
    //         return client;
    //     }).orElseThrow(() -> new ResourceNotFoundException(
    //             "The client with the name " + firstname + " " + lastname + " : " + email
    //                     + " couldn't be found in the database."));
    // }

    // TODO : change route ! admin reserved
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

}
