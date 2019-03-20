package com.project.controller;

import com.project.entity.Client;
import com.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/client/{rollNo}", method = RequestMethod.GET)
    Client getClient(@PathVariable("rollNo") Long rollNo) throws Exception {
        return clientService.getClient(rollNo);
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    Iterable<Client> getClients() {
        return clientService.getClients();
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addClient(@RequestBody Client client) throws Exception {
        clientService.addClient(client);
    }

    @RequestMapping(value = "/client/{rollNo}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void updateClient(@PathVariable("rollNo") Long rollNo, @RequestBody Client client) throws Exception {
        client.setId(rollNo);
        clientService.updateClient(client);
    }

    @RequestMapping(value = "/client/{rollNo}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    void deleteClient(@PathVariable("rollNo") Long rollNo) throws Exception {
        clientService.deleteClient(rollNo);
    }
}