package com.example.CMA.api.controller;

import com.example.CMA.api.model.Contact;
import com.example.CMA.api.service.CMAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class CMAController {

    private final CMAService contactService;

    //autowired annotation, it can be used to perform a service injection inside a constructor
    @Autowired
    public CMAController(CMAService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/greetings")
    public static String getHelloWorld(@RequestParam(value = "name", defaultValue = "world")String name ) {
        return name;
    }


    @GetMapping("/contact")
    public Contact getContacts(int id) {


        return (Contact) contactService.getContacts();
    }

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable("id") int id) {


        return contactService.getContactById(id);
    }

    @DeleteMapping("/contact/{contactId}")
    private void deleteContact(@PathVariable("ContactiId") int id)
    {

        contactService.deleteContact(id);
    }

    @PostMapping("/contact")
    private int saveContact(@RequestBody Contact contact, int id)
    {
        contactService.saveOrUpdateContact(contact);
        return contact.getId();
    }

    @PutMapping("/contact{id}")
    private int update(@RequestBody Contact Contact)
    {
        contactService.saveOrUpdateContact(Contact);

        return Contact.getId();

    }
}
