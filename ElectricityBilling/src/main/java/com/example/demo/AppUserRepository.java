package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUsers ,Long> {
	
	List<AppUsers>findByUserType(String userType);

	AppUsers findById(String id);
	

}
