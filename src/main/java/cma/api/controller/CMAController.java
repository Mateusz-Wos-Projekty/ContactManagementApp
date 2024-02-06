package cma.api.controller;

import cma.api.service.CMAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class CMAController {
    private final CMAService contactService;
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
    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> saveContact() {
        return new ResponseEntity<>("POST Response", HttpStatus.OK);
    }
}