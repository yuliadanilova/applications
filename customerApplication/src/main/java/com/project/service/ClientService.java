package com.project.service;

import com.project.entity.Client;
import com.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;


    public void addClient(Client client) throws Exception {

            repository.save(client);
    }

    public Iterable<Client> getClients() {

        return repository.findAll();
    }

    public Client getClient(Long id) throws Exception {

        if (repository.existsById(id)) {
            return repository.findById(id).get();
        } else {
            throw new Exception("Client not found");
        }
    }

    public void updateClient(Client client) throws Exception {
        if(repository.existsById(client.getId())) {
            repository.save(client);
        }
        else {
            throw new Exception("client not found");
        }
    }

    public void deleteClient(Long id) throws Exception {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new Exception("client not found");
        }
    }

}