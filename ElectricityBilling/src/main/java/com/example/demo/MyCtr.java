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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCtr {
	
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	BillRepository billRepository;
	
	
	@RequestMapping("/users")
    @ResponseBody
    ResponseEntity<Object> getUsers(){
		Iterable<AppUsers> a = appUserRepository.findAll();
		return new ResponseEntity<>(a,HttpStatus.OK);
	}
   

	@RequestMapping(value ="/bill/{id}/{unit}" , method  = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<Object> getBillByUId(@PathVariable("id") String id,@PathVariable ("unit") double unit ){
		try {
			Calculate cal = new Calculate();
			Bills bill =new Bills();
			AppUsers user = appUserRepository.findById(id);
			if (!user.equals(null)) {
				bill.setUserId(user.getId());
				bill.setUserName(user.getUserName());
				bill.setUserType(user.getUserType());		
				bill.setAmount( cal.getAmount(user.getUserType(), unit));
				bill.setUnits(unit);
//				billRepository.save(bill);
				return new ResponseEntity<>(bill, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Invalide Id !",HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Ayaa?");
			 e.printStackTrace();
			return new ResponseEntity<>("Invalide Id !",HttpStatus.BAD_REQUEST);

		}
		
	}
}
