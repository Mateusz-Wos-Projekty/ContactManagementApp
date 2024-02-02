package com.example.CMA.API.Service;

import com.example.CMA.API.Model.Contact;
import com.example.CMA.API.Repository.ContactManagementAppRepository;
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
    public Contact getContactById(int id) {

        //to have a look at
//        try (contactManagementAppRepository) {
//            if (contactManagementAppRepository.getById()) {
//                return file.nextLine();
//            } else {
//                throw new IllegalArgumentException("Non readable file");
//            }
//        } catch (FileNotFoundException err) {
//            if (!isCorrectFileName(fileName)) {
//                throw new ContactNotFoundException(
//                        "The contact does not exist : " + fileName , err);
//            }
        return contactManagementAppRepository.findById(id).orElseThrow();
    }
    public int saveOrUpdateContact(Contact contact) {
        var newInt = contactManagementAppRepository.saveAndFlush(contact);
        contactManagementAppRepository.saveAndFlush(contact);
        return newInt.getId();
    }
    public int deleteContact(int id) {
        contactManagementAppRepository.deleteById(id);
        return id;
    }
    public class ContactNotFoundException extends RuntimeException{
        public ContactNotFoundException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
    }
}
