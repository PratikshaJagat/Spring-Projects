package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DonorRepository extends CrudRepository<Donor, Long> {
	List<Donor> findByBloodGpIgnoreCase(String bloodGp);

}
