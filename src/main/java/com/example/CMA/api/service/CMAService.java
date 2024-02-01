package com.example.CMA.api.service;

import com.example.CMA.api.dto.ContactDTO;
import com.example.CMA.api.model.Contact;
import com.example.CMA.api.repository.ContactManagementAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CMAService {
    @Autowired
    ContactManagementAppRepository CMARepository;

    private Contact singleContact;
    private List<Contact> listOfContacts;

    public List<Contact> getContacts() {


        List<Contact> listOfContacts = new ArrayList<Contact>();
        CMARepository.findAll().forEach(Contact -> listOfContacts.add(Contact));

        return listOfContacts;
        


    }

    public Contact getContactById(int id){

        //ContactAppDTO ContactOne = new  ContactAppDTO(1,"new Contact");


        return CMARepository.findById(id).get();
    }

    public void saveOrUpdateContact(Contact Contact)
    {
        CMARepository.save(Contact);
    }
    //deleting a specific record
    public void deleteContact(int id)
    {
        CMARepository.deleteById(id);
    }




}
