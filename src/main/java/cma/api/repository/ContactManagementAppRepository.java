package cma.api.repository;

import cma.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactManagementAppRepository extends JpaRepository<Contact,Integer> {

}