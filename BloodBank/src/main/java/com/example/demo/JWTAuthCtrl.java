package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class JWTAuthCtrl {

	@Autowired
	UserProxy proxy;
	
	@Autowired
    private UserRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<String> getToken(@RequestBody Users login) throws ServletException {
		String jwttoken = "";
		System.out.println("aaya?");
		if(login.getUserName().isEmpty() || login.getPassword().isEmpty())
			return new ResponseEntity<String>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);
		String name = login.getUserName(), password = login.getPassword();
		Users u2 = proxy.validate(name, password);
	
		if(u2==null)
			return new ResponseEntity<String>("Invalid credentials. Please check the username and password.", HttpStatus.UNAUTHORIZED);
		else {
			// Creating JWT using the user credentials.
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("usr", u2.getUserName());
			claims.put("sub", "Authentication token");
			claims.put("iss", JWTConst.ISSUER_NAME);
			claims.put("rol", u2.getRole());
			claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			jwttoken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, JWTConst.SECRET_KEY).compact();
			System.out.println("Returning the following token to the user= "+ jwttoken);
		}
		return new ResponseEntity<String>(jwttoken, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody Users login) throws ServletException {
		if(login.getUserName().isEmpty() || login.getPassword().isEmpty())
			return new ResponseEntity<String>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);
		login.setRole("User");
		//int n = proxy.insert(login);
		int res = 0;
		try {
			Users p1Res = repository.save(login);
			res = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(res<=0)
			return new ResponseEntity<String>("Could Not Create!", HttpStatus.BAD_REQUEST);
		else {
		}
		return new ResponseEntity<String>("Created!", HttpStatus.OK);
	}

}