package com.backend.demox.services;

import com.backend.demox.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> GetAllClients();
    Optional<Client> CreateClient(Client cl_to_create);


    Optional<Client> GetOneClient(Long id);
    List<Client> GetClientsNameContains(String name_pattern);
    Optional<Client> ModifyClient (Client cli_to_save);

    List<Client> CreateTwoClients (Client cl);

    List<Client> GetSortedAllClients(String sortField);

    List<Client> GetSortedPagedAllClients(String sortField, Integer pageSize, Integer pageNo);
}
