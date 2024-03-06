package cma.api.repository;

import cma.api.model.Contact;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.function.Function;

@Repository
public interface ContactManagementAppRepository extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {
    List<Contact> findByFirstNameAndLastName(String firstName, String lastname);
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByLastName(String lastName);

}
