package com.example.demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository  extends CrudRepository<Bills, String>{

    Bills findByUserId(String uId);
}
