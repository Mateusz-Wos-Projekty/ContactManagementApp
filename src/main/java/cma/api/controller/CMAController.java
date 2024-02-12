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
import java.util.ArrayList;
import java.util.List;

@RestController
public class CMAController {
    private final  CMAService contactService;
    private final CMAMapper cmaMapper;
    @Autowired
    public CMAController(CMAService contactService,CMAMapper cmaMapper) {
        this.contactService = contactService;
        this.cmaMapper = cmaMapper;
    }

    /*skupiam sie narazie na post mapping gdy będę mieć już działającą werjsę,
      przejdę do pozostałych endpointow */
    @PostMapping("/contacts")
    public ResponseEntity<ContactDTO> saveContact(@RequestBody ContactDTO contactDto) throws ServerException {

        if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
            return new ResponseEntity<>(contactDto, HttpStatus.BAD_REQUEST);
        } else {

            Contact newContact = cmaMapper.convertDTOToAnEntity(contactDto);

            contactService.saveOrUpdateContact(newContact);

            ContactDTO newDTO = cmaMapper.convertAnEntityToDTO(newContact);

            return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
        }
    }
    @GetMapping("/greetings")
    public String getHelloWorld(@RequestParam(value = "name", defaultValue = "Hello World!") String name ) {
        return name;
    }
    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") Integer id) {

        if(contactService.getContactById(id).getId() == null){

            Contact newEmptyContact = new Contact();
            return new ResponseEntity<>(newEmptyContact, HttpStatus.OK);

        }else{
            return  new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
        }
    }
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts() {

        if(contactService.getContacts() == null){

            List<Contact> newEmptyContactList = new ArrayList<>();
            return new ResponseEntity<>(newEmptyContactList, HttpStatus.OK);

        }else{
            return  new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
        }
    }
    @PutMapping("/contact/{id}")
    public ResponseEntity<ContactDTO> update(@PathVariable("id") int id, @RequestBody ContactDTO contactDto) {
        if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
                return new ResponseEntity<>(contactDto, HttpStatus.BAD_REQUEST);
            } else {

                Contact originalContact = contactService.getContactById(id);

                if(originalContact == null)
                {
                    return new ResponseEntity<>(contactDto, HttpStatus.NOT_FOUND);
                }

                Contact newContact = cmaMapper.convertDTOToAnEntity(contactDto);

                originalContact.setFirstName(newContact.getFirstName());
                originalContact.setLastName(newContact.getLastName());
                originalContact.setDateOfBirth(newContact.getDateOfBirth());
                originalContact.setAddress(newContact.getAddress());
                originalContact.setMobileNumber(newContact.getMobileNumber());

                contactService.saveOrUpdateContact(originalContact);

                ContactDTO newDTO = cmaMapper.convertAnEntityToDTO(originalContact);

                return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
            }
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable("id") int id) {

        if(contactService.getContactById(id).getId() == null){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }else{
            contactService.deleteContact(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}