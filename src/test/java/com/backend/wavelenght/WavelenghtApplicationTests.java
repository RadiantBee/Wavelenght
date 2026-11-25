package com.backend.wavelenght;

import com.backend.wavelenght.models.Client;
import com.backend.wavelenght.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import java.util.Optional;


@SpringBootTest
class WavelenghtApplicationTests {


	/*
	@Test
	void test_of_test(){
		Assertions.assertTrue(true);
	}


	@Autowired
	private ClientRepository clientRepository;



	@Transactional
	@Test
	void test_ClientRepository() {
		Assertions.assertTrue(true);
		Client cli = new Client(1L,"test_name","test_sdname");
		cli = clientRepository.save(cli);
		Optional<Client> ocli_found = clientRepository.findById(cli.getId());
		Assertions.assertTrue(ocli_found.isPresent());
	}


 */

}
