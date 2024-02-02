package CMA.API.Repository;

import CMA.API.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactManagementAppRepository extends JpaRepository<Contact,Integer> {

}
