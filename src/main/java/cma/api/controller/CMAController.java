package cma.api.controller;

import cma.api.dto.CreateContactDTO;
import cma.api.dto.ReturnContactDTO;
import cma.api.exceptions.ContactNotFoundException;
import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import cma.api.repository.ContactManagementAppRepository;
import cma.api.service.CMAService;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CMAController {
    private final CMAService contactService;
    private final CMAMapper cmaMapper;
    private final ContactManagementAppRepository repository;

    @Autowired
    public CMAController(CMAService contactService, CMAMapper cmaMapper, ContactManagementAppRepository repository) {
        this.contactService = contactService;
        this.cmaMapper = cmaMapper;
        this.repository = repository;
    }
    @GetMapping("/greetings")
    public String getHelloWorld(String name) {
        return name;
    }
    @PostMapping("/contacts")
    public ResponseEntity<ReturnContactDTO> saveContact(@RequestBody CreateContactDTO contactDto) {

        if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(contactService.saveContact(contactDto), HttpStatus.CREATED);
    }
    @GetMapping("/contacts/{id}")
    public ResponseEntity<ReturnContactDTO> getContact(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(contactService.getContact(id), HttpStatus.OK);
        } catch (ContactNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/contacts")
    public ResponseEntity<List<ReturnContactDTO>> getContacts() {

        return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    }
    @PutMapping("/contacts/{id}")
    public ResponseEntity<ReturnContactDTO> update(@PathVariable("id") int id, @RequestBody CreateContactDTO contactDto) {

        try {

            if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
                return ResponseEntity.badRequest().build();
            }

            return new ResponseEntity<>(contactService.updateContact(id, contactDto), HttpStatus.OK);

        } catch (ContactNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") int id) {

        try {
            contactService.deleteContact(id);
            return ResponseEntity.noContent().build();

        } catch (ContactNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/contacts/turkraft-imported-library/filtered-contacts")
    public ResponseEntity<List<ReturnContactDTO>> searchForContacts(@Filter Specification<Contact> spec) {
            return new ResponseEntity<>(contactService.filterContacts(spec), HttpStatus.OK);
    }
    @GetMapping("/contacts?firstname={firstName}&lastname={lastName}")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {

        return new ResponseEntity<>(contactService.filterContactsByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/contacts/filter-by-first-name?firstname={firstName}")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsByFirstName(@PathVariable("firstName") String firstName) {

        return new ResponseEntity<>(contactService.filterContactsByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/contacts?lastname={lastName}")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsByLastName(@PathVariable("lastName") String lastName) {

        return new ResponseEntity<>(contactService.filterContactsByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/contacts/search-for-contacts-without-a-filter")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsWithoutAFilter() {

        return new ResponseEntity<>(contactService.filterContactsWithoutAFilter(), HttpStatus.OK);
    }
}