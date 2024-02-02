package com.example.CMA.API.Repository;

import com.example.CMA.API.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactManagementAppRepository extends JpaRepository<Contact,Integer> {

}
