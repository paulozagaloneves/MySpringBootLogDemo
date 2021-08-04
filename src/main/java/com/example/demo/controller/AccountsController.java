package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountsRepository;

@RestController
public class AccountsController {
	
	@Autowired
	AccountsRepository repository;

	@GetMapping("/accounts")
	List<Account> all(@RequestParam(required = false) String type) {
		
		if (type != null) {
			return allByType(type);
		} else {
			return repository.findAll();
		}
	}
	
	private List<Account> allByType(String type) {

		return repository.findByType(type);
	}

	@GetMapping("/accounts/{id}")
	Account one(@PathVariable Integer id) {

		return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
	}


}
