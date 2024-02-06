package cma.api.service;

import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import cma.api.repository.ContactManagementAppRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class CMAService {
    private CMAMapper newMapper;
    private ContactManagementAppRepository contactManagementAppRepository;
    private Contact singleContact;
    private List<Contact> listOfContacts;
    public CMAService(){

    }
    public CMAService(ContactManagementAppRepository contactManagementAppRepository,CMAMapper newMapper ,Contact singleContact, List<Contact> listOfContacts) {
        this.contactManagementAppRepository = contactManagementAppRepository;
        this.newMapper = newMapper;
        this.singleContact = singleContact;
        this.listOfContacts = listOfContacts;
    }
    public List<Contact> getContacts() {
        List<Contact> listOfContacts = new ArrayList<>();
        contactManagementAppRepository.findAll().forEach(listOfContacts::add);

        return listOfContacts;
    }
    public Contact getContactById(int id) {
        return  contactManagementAppRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact Not Found", new Throwable()));
    }
    public Contact saveOrUpdateContact(Contact contact) {
        return contactManagementAppRepository.saveAndFlush(contact);

    }
    public void deleteContact(int id) {
        contactManagementAppRepository.deleteById(id);
    }
    public CMAMapper getNewMapper() {
        return newMapper;
    }
    public void setNewMapper(CMAMapper newMapper) {
        this.newMapper = newMapper;
    }
    public class ContactNotFoundException extends RuntimeException {
        public ContactNotFoundException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
    }
}