package com.example.demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UserProxy {

    @Autowired
    private UserRepository repository;

	Users validate(String username, String password){
		Users res = null;
		System.out.println(username);
		System.out.println(password);
		System.out.println(repository);
		List<Users> res2 = repository.findByUserNameIgnoreCaseAndPasswordIgnoreCase(username, password);						
		if(res2.size()>0) {
			res = res2.get(0);
		}
		return res;
	}

	
	int insert(Users p1){
		int res = 0;
		try {
			Users p1Res = repository.save(p1);
			res = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
}
