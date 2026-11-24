package com.backend.demox.controllers;

import com.backend.demox.models.Client;
import com.backend.demox.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/test")
    public String testClientControler() {
        return  "test ClientControler";
    }

    /*
    @GetMapping("/clients")
    public List<Client> GetAllClients(){
        return clientService.GetAllClients();
    }
    */

    @GetMapping(value="/clients/{id:\\d*}")
    public ResponseEntity<Client> FindOne(@PathVariable("id") Long id) {
        Optional<Client> ocli =  clientService.GetOneClient(id);

        if (ocli.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ocli.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/clients")
    public ResponseEntity<Client> CreateClient (@RequestBody Client cli) {
        Optional<Client> ocli = clientService.CreateClient(cli);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ocli.get());
    }


    @GetMapping("/clients")
    public List<Client> GetNamedClients(@RequestParam(value = "name", defaultValue = "") String name_pattern)
    {
        if (name_pattern.isEmpty())
            return clientService.GetAllClients();
        else
           return clientService.GetClientsNameContains(name_pattern);
    }

/*
    @GetMapping("/clients-ext")
    public List<Client> GetNamedClients(@RequestParam(value = "name", defaultValue = "")  name_pattern)
    {
        if (name_pattern.isEmpty())
            return clientService.GetAllClients();
        else
            return clientService.GetClientsNameContains(name_pattern);
    }
*/

    @PutMapping("/clients/{id:\\d+}")
    public ResponseEntity<Client> ModifyClient (
            @PathVariable("id") Long cli_id,
            @RequestBody Client cli_to_modify)

    {
        cli_to_modify.setId(cli_id);
        Optional<Client> ocli = clientService.ModifyClient(cli_to_modify);
        if (ocli.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ocli.get());
        } else {
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    // fake 2 inserts
    @PostMapping(value="/clients2")
    public ResponseEntity<List<Client>> CreateTwoClients (@RequestBody Client cli_param) {
        List<Client> li_clis =  clientService.CreateTwoClients(cli_param);
        /*
        final HttpStatus ret =  (cli == null)? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED;
        return  ResponseEntity
                .status(ret)
                .body(cli);

         */
        return  ResponseEntity
                .status(500)
                .body(li_clis);

    }

    // localhost:8082/demox/clients-ext?sort=name
    // localhost:8082/demox/clients-ext?sort=sdname
    // localhost:8082/demox/clients-ext?sort=id

    // localhost:8082/demox/clients-ext?sort=name&page-size=3&page-no=2


    @GetMapping("/clients-ext")
    public List<Client> GetClientsExt(
            @RequestParam(value = "sort", defaultValue = "name") String sortField,
            @RequestParam(value = "page-size", defaultValue = "2") Integer pageSize,
            @RequestParam(value = "page-no", defaultValue = "") Integer pageNo)
    {
        /*
        if (name_pattern.isEmpty())
            return clientService.GetAllClients();
        else
            return clientService.GetClientsNameContains(name_pattern);
         */
        if (pageNo==null)
            return clientService.GetSortedAllClients(sortField);
        else
            return clientService.GetSortedPagedAllClients(sortField,pageSize,pageNo);
    }

}
