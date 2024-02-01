package com.example.CMA.api.repository;

import com.example.CMA.api.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactManagementAppRepository extends CrudRepository<Contact,Integer> {

}
