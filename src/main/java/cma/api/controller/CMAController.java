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
    private final CMAMapper cmaMapper;

    @Autowired
    public CMAController(CMAService contactService,CMAMapper cmaMapper) {
        this.contactService = contactService;
        this.cmaMapper = cmaMapper;
    }
    @GetMapping("/greetings")
    public String getHelloWorld(@RequestParam(value = "name", defaultValue = "Hello World!") String name ) {
        return name;
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
}