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
    public List<Contact> getHelloWorld(String name) {

        return contactService.findMobileNumberSevenSevenSeven();
    }

    @PostMapping("/contacts")
    public ResponseEntity<ReturnContactDTO> saveContact(@RequestBody CreateContactDTO contactDto) {

        if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
            return new ResponseEntity<>(cmaMapper.convertCreateContactDTOToReturnContactDTO(contactDto), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(contactService.addAContactToADatabaseAndReturnReturnContactDTOEntity(contactDto), HttpStatus.CREATED);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<ReturnContactDTO> getContact(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(contactService.getContactByIdAndReturnReturnContactDTOEntity(id), HttpStatus.OK);
        } catch (ContactNotFoundException e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<ReturnContactDTO>> getContacts() {

        if (contactService.getContacts().isEmpty()) {
            List<ReturnContactDTO> newList = new ArrayList<>();
            return ResponseEntity.ok().body(newList);
        }
        return new ResponseEntity<>(contactService.getContactsFromTheDatabaseAndReturnAListOfReturnContactDTO(), HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<ReturnContactDTO> update(@PathVariable("id") int id, @RequestBody CreateContactDTO contactDto) {

        try {

            if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
                return ResponseEntity.badRequest().build();
            }

            return new ResponseEntity<>(contactService.updateAnExistingContactAndReturnReturnContactDTOEntity(id,contactDto), HttpStatus.OK);

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

    @GetMapping("contacts/turkraft-imported-library/filtered-contacts/")
    public ResponseEntity<List<ReturnContactDTO>> search(@Filter Specification<Contact> spec) {

        try {
            return new ResponseEntity<>(contactService.filterTheEntitiesTakingIntoAccountAnAttributeAndReturnAListOfReturnContactDTO(spec), HttpStatus.OK);
        } catch (ContactNotFoundException e) {
            List<ReturnContactDTO> newList = new ArrayList<>();
            return ResponseEntity.ok().body(newList);
        }
    }

    @GetMapping("contacts?")
    public ResponseEntity<List<ReturnContactDTO>> searchUsingJpa(String firstName, String lastName){

        return new ResponseEntity<>(contactService.filterAContactByFirstNameAndLastName(firstName,lastName), HttpStatus.OK);
    }
}