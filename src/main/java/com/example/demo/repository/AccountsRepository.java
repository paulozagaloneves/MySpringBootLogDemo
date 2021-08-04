package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidTypeException;
import com.example.demo.model.Account;

@Service
public class AccountsRepository {
	private static Map<Integer,Account> allAccounts;
	private static List<String> VALID_TYPES = Arrays.asList("business","retail");
	
	static {
		allAccounts = new HashMap<>();
		
		for (int i=1000;i<4000;i++) {
			Account newAccount = new Account(i, "firstName " + i, "lastName " + i, ("firstName " + i + "lastName " + i), new Date(), "business");
			allAccounts.put(i, newAccount);
		}
		
		for (int i=4000;i<5000;i++) {
			Account newAccount = new Account(i, "firstName " + i, "lastName " + i, ("firstName " + i + "lastName " + i), new Date(), "retail");
			allAccounts.put(i, newAccount);
		}
	}

	
	public List<Account> findAll() {
		return new ArrayList<>(allAccounts.values());
	}
	
	public List<Account> findByType(final String type) {
		if (type == null || !VALID_TYPES.contains(type.toLowerCase())) {
			throw new InvalidTypeException(type);
			
		}
		return new ArrayList<>(allAccounts.values().stream().filter(a -> a.getType().equalsIgnoreCase(type)).toList());
	}
	
	public Optional<Account> findById(final Integer id) {
		return Optional.ofNullable(allAccounts.get(id));
	}
}
