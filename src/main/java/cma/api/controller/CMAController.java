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
    public List<ReturnContactDTO> getHelloWorld() {
        return contactService.filterContactsUsingNamedQuries("Master", "Yoda");
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

    @GetMapping("/contacts/method-one/filtered-contacts")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsOne(@Filter Specification<Contact> spec) {
        return new ResponseEntity<>(contactService.filterContacts(spec), HttpStatus.OK);
    }

    @GetMapping("/contacts/method-two/filtered-contacts")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsTwo(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        return new ResponseEntity<>(contactService.filterContactsUsingJPAQueryMethods(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/contacts/method-three/filtered-contacts")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsThree(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        return new ResponseEntity<>(contactService.filterContactsWithoutDeclaringJPAMethods(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/contacts/method-four/filtered-contacts")
    public ResponseEntity<List<ReturnContactDTO>> searchForContactsFour(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        return new ResponseEntity<>(contactService.filterContactsUsingNamedQuries(firstName, lastName), HttpStatus.OK);
    }
}