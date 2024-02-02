package CMA.API.Controller;

import CMA.API.Model.Contact;
import CMA.API.Service.CMAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class CMAController {
    private final CMAService contactService;
    @Autowired
    public CMAController(CMAService contactService) {
        this.contactService = contactService;
    }
    @GetMapping("/greetings")
    public static String getHelloWorld(@RequestParam(value = "name", defaultValue = "Hello World!") String name ) {
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
    private int deleteContact(@PathVariable("ContactiId") int id) {
        contactService.deleteContact(id);
        return id;
    }
    @PostMapping("/contact")
    private int saveContact(@RequestBody Contact contact) {
        contactService.saveOrUpdateContact(contact);
        return contact.getId();
    }
    @PutMapping("/contact{id}")
    private int update(@RequestBody Contact contact, int id) {
        contactService.saveOrUpdateContact(contact);
        return contact.getId();
    }
}