package fr.su.bankapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import fr.su.bankapp.exception.ResourceNotFoundException;
import fr.su.bankapp.model.Client;
import fr.su.bankapp.repository.ClientRepository;

@Controller
@RequestMapping("/api/")
@CrossOrigin(origins="http://localhost:8081")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

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

        logger.info("Reading client with id " + id + " from database.");
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
    }

    @ResponseBody
    @GetMapping(path = "/client/{firstname}/{lastname}/{email}")
    public Client getClientName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
            @PathVariable("email") String email) {

        logger.info("Reading client : " + firstname + " " + lastname + " " + email + " from database.");
        return clientRepository.findByIdentity(firstname, lastname, email);
    }


    /** Add a client to the database with firstname, lastname and email */
    @ResponseBody
    @RequestMapping(path = "/secured/create/{lastName}/{firstName}/{email}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName,
            @PathVariable("email") String email) {
        Client savedClient = clientRepository.save(new Client(firstName, lastName, email));

        logger.info(savedClient.toString() + " successfully saved into DB");

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

        logger.info(savedClient.toString() + " successfully saved into sDB");

        return savedClient.getId();
    }

    public static class Operation {

        Long accountId;
        Double amount;

        public Operation(Long accountId,Double amount) {
            this.accountId = accountId;
            this.amount = amount;
        }
    }

    /** Withdraw */
    @ResponseBody
    @RequestMapping(path = "/Withdraw", method = RequestMethod.POST)
    public Client withdraw(@RequestBody Operation op) {

        Client c = clientRepository.findById(op.accountId).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + op.accountId + " couldn't be found in the database."));
        c.setBalance(c.getBalance()-op.amount);
        clientRepository.save(c);

        logger.info(" account " + op.accountId + " withdrawing " + op.amount + " from his account" );

        return c;
    }

    /** Deposit */
    @ResponseBody
    @RequestMapping(path = "/Deposit", method = RequestMethod.POST)
    public Client deposit(@RequestBody Operation op) {

        Client c = clientRepository.findById(op.accountId).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + op.accountId + " couldn't be found in the database."));
        c.setBalance(c.getBalance() + op.amount);
        clientRepository.save(c);

        logger.info(" account " + op.accountId + " depositing " + op.amount + " from his account" );

        return c;
    }
}
