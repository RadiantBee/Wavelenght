package com.backend.wavelenght.services;

import com.backend.wavelenght.models.Client;
import com.backend.wavelenght.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    private static Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> GetAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> CreateClient(Client cl_to_create) {
        Client cli_saved = clientRepository.save (cl_to_create);
        return Optional.of(cli_saved);
    }

    @Override
    public Optional<Client> GetOneClient(Long id) {
        return (clientRepository.findById(id));
    }

    @Override
    public List<Client> GetClientsNameContains(String name_pattern)
    {
        return clientRepository.findFirst3ByNameContaining(name_pattern);
        /*
        name_pattern = "%" +name_pattern+ "%";
        //return clientRepository.findByNameLikeJPQLbased(name_pattern);
        return clientRepository.findByNameLikeSQLbased(name_pattern);
        */
    }

    @Override
    public Optional<Client> ModifyClient(Client cli_to_save) {
        //return Optional.empty();
        Optional<Client> ocli = clientRepository.findById(cli_to_save.getId());
        if (ocli.isPresent()){
            Client cli = ocli.get();
            cli.setName(cli_to_save.getName());
            cli.setSdname(cli_to_save.getSdname());
            cli = clientRepository.save(cli);
            return Optional.of(cli);

        } else {
            return Optional.empty();
        }

    }


    // intentionally generates error (duplication of name value)
    @Override
    @Transactional // on/off
    public List<Client> CreateTwoClients(Client cl) {
        List<Client> clientList = new ArrayList<Client>();
        Client cl_ret;
        cl_ret = clientRepository.save (cl);
        clientList.add(cl_ret);
        cl.setSdname(cl.getSdname()+"_2");
        cl_ret = clientRepository.save (cl);
        clientList.add(cl_ret);
        return  clientList;
    }

    @Override
    public List<Client> GetSortedAllClients(String sortField) {

        //return  (List<Client>) clientRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        return  (List<Client>) clientRepository.findAll(Sort.by(Sort.Direction.ASC,sortField));
        //return  clientRepository.findAll(new Sort(Sort.Direction.ASC,sortField));
    }

    @Override
    public List<Client> GetSortedPagedAllClients(String sortField, Integer pageSize, Integer pageNo) {

        Page<Client> pageOfClients;
        pageOfClients = clientRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC,sortField)));

        // Page metadata
        LOG.info("TotalElements: " + pageOfClients.getTotalElements());
        LOG.info("TotalPages: " + pageOfClients.getTotalPages());
        LOG.info("Page Number: " + pageOfClients.getNumber());
        LOG.info("Page NumberOfElements: " + pageOfClients.getNumberOfElements());

        return pageOfClients.getContent();

    }

}
