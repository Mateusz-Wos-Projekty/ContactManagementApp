package cma.api.controller;

import cma.api.dto.CreateContactDTO;
import cma.api.dto.ReturnContactDTO;
import cma.api.exceptions.ContactNotFoundException;
import cma.api.mapper.CMAMapper;
import cma.api.repository.ContactManagementAppRepository;
import cma.api.service.CMAService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getHelloWorld(String name) {
        name = "Hello World!";
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
    public ResponseEntity<List<ReturnContactDTO>> getContacts(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        return new ResponseEntity<>(contactService.filterContactsUsingJPAQueryMethods(firstName, lastName), HttpStatus.OK);
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
}