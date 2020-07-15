package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCtr {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DonorRepository drepository;
	
	@RequestMapping("/users")
	 ResponseEntity<Object> getUser(){
	Iterable<Users> res =(Iterable<Users>) repository.findAll();
		return new ResponseEntity<>(res ,HttpStatus.OK);
	}
    @RequestMapping(value="/users",method = RequestMethod.PUT)
    ResponseEntity<Object> insert(@RequestBody Users u){
		Users user = repository.save(u);
		return new ResponseEntity<>(user ,HttpStatus.CREATED);
	}
    @RequestMapping(value="/user/{id}",method = RequestMethod.DELETE)
  	ResponseEntity<Object> deleteUser(@PathVariable("id") Long id ){
    	Optional<Users> u= repository.findById(id);
    	if(u.isPresent()) {
    		repository.delete(u.get());
    		return new ResponseEntity<>("Deleted" ,HttpStatus.OK);
    	}
    	else
  		 return new ResponseEntity<>("NOt Found",HttpStatus.BAD_REQUEST);
  	}
	@RequestMapping("/donors")
    ResponseEntity<Object> getDonor(){
		Iterable<Donor> res =(Iterable<Donor>) drepository.findAll();
		return new ResponseEntity<>(res ,HttpStatus.OK);
	}
	  @RequestMapping(value="/donor",method = RequestMethod.PUT)
	  ResponseEntity<Object> insertDonor(@RequestBody Donor u){
			Donor d = drepository.save(u);
			return new ResponseEntity<>(d ,HttpStatus.CREATED);
		}
	   @RequestMapping(value="/donor/{id}",method = RequestMethod.DELETE)
	   ResponseEntity<Object> deleteDonor(@PathVariable("id") Long id ){
	    	Optional<Donor> u= drepository.findById(id);
	    	if(u.isPresent()) {
	    		drepository.delete(u.get());
	    		return new ResponseEntity<>("Deleted" ,HttpStatus.OK);
	    	}
	    	else
	  		 return new ResponseEntity<>("NOt Found",HttpStatus.BAD_REQUEST);
	  	}
	   @RequestMapping(value="/donor/{gp}",method = RequestMethod.POST)
	   ResponseEntity<Object> getDonorByBGp(@PathVariable("gp") String gp ){
	    	    
		   List<Donor> u= drepository.findByBloodGpIgnoreCase(gp);
	    		return new ResponseEntity<>(u ,HttpStatus.OK);
	  	}

}
