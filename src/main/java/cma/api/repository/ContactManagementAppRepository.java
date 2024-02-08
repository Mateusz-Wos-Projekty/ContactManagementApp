package cma.api.repository;

import cma.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactManagementAppRepository extends JpaRepository<Contact,Integer> {

}