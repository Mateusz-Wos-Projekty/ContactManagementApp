package cma.api.controller;

import cma.api.dto.ContactDTO;
import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import cma.api.service.CMAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RestController
public class CMAController {
    private final  CMAService contactService;

    @Autowired
    public CMAController(CMAService contactService) {
        this.contactService = contactService;
    }
    @GetMapping("/greetings")
    public String getHelloWorld(@RequestParam(value = "name", defaultValue = "Hello World!") String name ) {
        return name;
    }
    /*skupiam sie narazie na post mapping gdy będę mieć już działającą werjsę,
      przejdę do pozostałych endpointow */
    @PostMapping("/contacts")
    public ResponseEntity<Contact> saveContact(@RequestBody ContactDTO contactDto) throws ServerException {
        CMAMapper newMapper = new CMAMapper();
        Contact newContact = newMapper.convertDTOToAnEntity(contactDto);

        if (newContact == null) {
            throw new ServerException("422 - Unprocessable Entity");
        } else {
            contactService.saveOrUpdateContact(newContact);
            return new ResponseEntity<>(newContact, HttpStatus.CREATED);
        }

    }
}