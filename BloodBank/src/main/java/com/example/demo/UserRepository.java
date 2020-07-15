package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
	List<Users> findById(String si);
	List<Users>  findByUserNameIgnoreCaseAndPasswordIgnoreCase(String username, String password);

}
