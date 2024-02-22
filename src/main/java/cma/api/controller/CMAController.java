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
    public String getHelloWorld(@RequestParam(value = "name", defaultValue = "Hello World!") String name) {
        return name;
    }

    /*skupiam sie narazie na post mapping gdy będę mieć już działającą werjsę,
      przejdę do pozostałych endpointow */
    @PostMapping("/contacts")
    public ResponseEntity<ReturnContactDTO> saveContact(@RequestBody CreateContactDTO contactDto) {

        if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
            ReturnContactDTO newReturnContactDTO = cmaMapper.convertCreateContactDTOToReturnContactDTO(contactDto);
            return new ResponseEntity<>(newReturnContactDTO, HttpStatus.BAD_REQUEST);
        }

        Contact newContact = cmaMapper.convertCreateContactDTOToAnEntity(contactDto);
        contactService.saveOrUpdateContact(newContact);
        ReturnContactDTO newDTO = cmaMapper.convertAnEntityToReturnContactDTO(newContact);

        return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<ReturnContactDTO> getContact(@PathVariable("id") Integer id) {
        try {
            var storeValue = contactService.getContactById(id);

            ReturnContactDTO newReturnContactDTO = cmaMapper.convertAnEntityToReturnContactDTO(storeValue);

            return new ResponseEntity<>(newReturnContactDTO, HttpStatus.OK);

        } catch (ContactNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<ReturnContactDTO>> getContacts() {
        try {
            var storeValue = contactService.getContacts();

            List<ReturnContactDTO> newReturnContactDTO = cmaMapper.convertAnEntityListToReturnContactDTOList(storeValue);

            return new ResponseEntity<>(newReturnContactDTO, HttpStatus.OK);

        } catch (ContactNotFoundException e) {
            List<ReturnContactDTO> newList = new ArrayList<>();
            return ResponseEntity.ok().body(newList);
        }
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<ReturnContactDTO> update(@PathVariable("id") int id, @RequestBody CreateContactDTO contactDto) {

        try {

            if (contactDto.getFirstName() == null || contactDto.getLastName() == null || contactDto.getDateOfBirth() == null || contactDto.getAddress() == null || contactDto.getMobileNumber() == null) {
                return ResponseEntity.badRequest().build();
            }

            var originalContact = contactService.getContactById(id);

            Contact newContact = cmaMapper.convertCreateContactDTOToAnEntity(contactDto);

            originalContact.setFirstName(newContact.getFirstName());
            originalContact.setLastName(newContact.getLastName());
            originalContact.setDateOfBirth(newContact.getDateOfBirth());
            originalContact.setAddress(newContact.getAddress());
            originalContact.setMobileNumber(newContact.getMobileNumber());

            contactService.saveOrUpdateContact(originalContact);

            ReturnContactDTO newReturnContactDTO = cmaMapper.convertAnEntityToReturnContactDTO(originalContact);

            return new ResponseEntity<>(newReturnContactDTO, HttpStatus.OK);

        } catch (ContactNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") int id) {

        try {
            contactService.getContactById(id).getId();
            contactService.deleteContact(id);
            return ResponseEntity.noContent().build();

        } catch (ContactNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/search/contacts")
    public ResponseEntity<List<ReturnContactDTO>> search(@Filter Specification<Contact> spec) {

        try {

            var storeValue = repository.findAll(spec);

            List<ReturnContactDTO> newReturnContactDTOList = cmaMapper.convertAnEntityListToReturnContactDTOList(storeValue);

            return new ResponseEntity<>(newReturnContactDTOList, HttpStatus.OK);

        } catch (ContactNotFoundException e) {

            List<ReturnContactDTO> newList = new ArrayList<>();
            return ResponseEntity.ok().body(newList);
        }
    }
}