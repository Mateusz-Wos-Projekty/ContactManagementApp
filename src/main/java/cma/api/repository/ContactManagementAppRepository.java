package cma.api.repository;

import cma.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactManagementAppRepository extends JpaRepository<Contact,Integer>, JpaSpecificationExecutor<Contact> {

}