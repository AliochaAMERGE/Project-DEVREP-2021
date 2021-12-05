package fr.su.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

     @ResponseBody
    @GetMapping(path = "/client/{firstname}/{lastname}/{email}")
    public Client getClientName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
            @PathVariable("email") String email) {

        return clientRepository.findByIdentity(firstname, lastname, email);
    }


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

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**', '/about.html'
    @RequestMapping(value = "{_:^(?!index\\.html|api|about).$}")
    public String redirectApi() {
        // LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
}
