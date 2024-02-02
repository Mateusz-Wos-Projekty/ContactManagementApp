package CMA.API.Service;

import CMA.API.Model.Contact;
import CMA.API.Repository.ContactManagementAppRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CMAService {
    private ContactManagementAppRepository contactManagementAppRepository;
    private Contact singleContact;
    private List<Contact> listOfContacts;
    public CMAService(){

    }
    public CMAService(ContactManagementAppRepository contactManagementAppRepository, Contact singleContact, List<Contact> listOfContacts) {
        this.contactManagementAppRepository = contactManagementAppRepository;
        this.singleContact = singleContact;
        this.listOfContacts = listOfContacts;
    }
    public List<Contact> getContacts() {
        List<Contact> listOfContacts = new ArrayList<>();
        contactManagementAppRepository.findAll().forEach(listOfContacts::add);

        return listOfContacts;
    }
    public Contact getContactById(int id)  {
        try {
            return contactManagementAppRepository.findById(id).orElseThrow();
        } catch (ContactNotFoundException e) {
            throw new ContactNotFoundException("Contact Not Found",e);
        }
    }
    public void saveOrUpdateContact(Contact contact) {
        contactManagementAppRepository.saveAndFlush(contact);
    }
    public void deleteContact(int id) {
        contactManagementAppRepository.deleteById(id);
    }
    public class ContactNotFoundException extends RuntimeException{
        public ContactNotFoundException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
    }
}
