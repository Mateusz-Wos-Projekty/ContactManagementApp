package cma.api.service;

import cma.api.exceptions.ContactNotFoundException;
import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import cma.api.repository.ContactManagementAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class CMAService {

    private final CMAMapper newMapper;
    private final ContactManagementAppRepository contactManagementAppRepository;
    private Contact singleContact;
    private List<Contact> listOfContacts;

    @Autowired
    public CMAService(CMAMapper newMapper, ContactManagementAppRepository contactManagementAppRepository){
        this.newMapper = newMapper;
        this.contactManagementAppRepository = contactManagementAppRepository;
    }
    public List<Contact> getContacts() {
        List<Contact> listOfContacts = new ArrayList<>();

        contactManagementAppRepository.findAll().forEach(listOfContacts::add);

        return listOfContacts;
    }
    public Contact getContactById(int id) {
        return  contactManagementAppRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact Not Found"));
    }
    public Contact saveOrUpdateContact(Contact contact) {
        return contactManagementAppRepository.save(contact);
    }
    public void deleteContact(int id) {
        contactManagementAppRepository.deleteById(id);
    }
}