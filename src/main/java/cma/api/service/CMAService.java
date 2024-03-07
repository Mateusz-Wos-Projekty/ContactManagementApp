package cma.api.service;

import cma.api.dto.CreateContactDTO;
import cma.api.dto.ReturnContactDTO;
import cma.api.exceptions.ContactNotFoundException;
import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import cma.api.repository.ContactManagementAppRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class CMAService {
    @PersistenceContext
    EntityManager entityManager;

    private final CMAMapper cmaMapper;
    private final ContactManagementAppRepository contactManagementAppRepository;
    private Contact singleContact;
    private List<Contact> listOfContacts;

    @Autowired
    public CMAService(CMAMapper cmaMapper, ContactManagementAppRepository contactManagementAppRepository) {
        this.cmaMapper = cmaMapper;
        this.contactManagementAppRepository = contactManagementAppRepository;
    }

    public List<Contact> getContactsFromRepository() {
        List<Contact> listOfContacts = new ArrayList<>();

        contactManagementAppRepository.findAll().forEach(listOfContacts::add);

        return listOfContacts;
    }

    public Contact getContactById(int id) {
        return contactManagementAppRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact Not Found"));
    }

    public Contact saveOrUpdateContact(Contact contact) {
        return contactManagementAppRepository.save(contact);
    }

    public void deleteContact(int id) {
        contactManagementAppRepository.deleteById(id);
    }

    public ReturnContactDTO updateContact(int id, CreateContactDTO contactDTO) {

        var originalContact = getContactById(id);

        Contact newContact = cmaMapper.convertCreateContactDTOToAnEntity(contactDTO);

        originalContact.setFirstName(newContact.getFirstName());
        originalContact.setLastName(newContact.getLastName());
        originalContact.setDateOfBirth(newContact.getDateOfBirth());
        originalContact.setAddress(newContact.getAddress());
        originalContact.setMobileNumber(newContact.getMobileNumber());

        saveOrUpdateContact(originalContact);

        return cmaMapper.convertAnEntityToReturnContactDTO(originalContact);

    }

    public ReturnContactDTO saveContact(CreateContactDTO contactDTO) {

        Contact newContact = cmaMapper.convertCreateContactDTOToAnEntity(contactDTO);
        saveOrUpdateContact(newContact);

        return cmaMapper.convertAnEntityToReturnContactDTO(newContact);
    }

    public List<ReturnContactDTO> getContacts() {

        var storeValue = getContactsFromRepository();
        return cmaMapper.convertAnEntityListToReturnContactDTOList(storeValue);
    }

    public ReturnContactDTO getContact(int id) {

        var storeValue = getContactById(id);
        return cmaMapper.convertAnEntityToReturnContactDTO(storeValue);
    }

    public List<ReturnContactDTO> filterContacts(Specification<Contact> spec) {

        var storeValue = contactManagementAppRepository.findAll(spec);
        return cmaMapper.convertAnEntityListToReturnContactDTOList(storeValue);
    }

    public List<ReturnContactDTO> filterContactsByFirstNameAndLastName(String firstName, String lastName) {

        var listOfContactsFilteredByFirstNameAndLastName = contactManagementAppRepository.findByFirstNameAndLastName(firstName, lastName);
        return cmaMapper.convertAnEntityListToReturnContactDTOList(listOfContactsFilteredByFirstNameAndLastName);
    }

    public List<ReturnContactDTO> filterContactsByFirstName(String firstName) {

        var listOfContactsFilteredByFirstName = contactManagementAppRepository.findByFirstName(firstName);
        return cmaMapper.convertAnEntityListToReturnContactDTOList(listOfContactsFilteredByFirstName);
    }

    public List<ReturnContactDTO> filterContactsByLastName(String lastName) {

        var listOfContactsFilteredByLastName = contactManagementAppRepository.findByLastName(lastName);
        return cmaMapper.convertAnEntityListToReturnContactDTOList(listOfContactsFilteredByLastName);
    }

    public List<ReturnContactDTO> filterContactsUsingJPAQueryMethods(String firstName, String lastName) {

        List<ReturnContactDTO> result = null;

        if (firstName == null && lastName == null) {

            result = cmaMapper.convertAnEntityListToReturnContactDTOList(contactManagementAppRepository.findAll());

        } else if (firstName != null && lastName == null) {

            result = filterContactsByFirstName(firstName);

        } else if (firstName == null) {

            result = filterContactsByLastName(lastName);

        } else if (firstName != null && lastName != null) {

            result = filterContactsByFirstNameAndLastName(firstName, lastName);
        }

        return result;
    }

    public List<ReturnContactDTO> filterContactsWithoutDeclaringJPAMethods(String firstName, String lastName) {

        List<ReturnContactDTO> endResult = null;

        if (firstName == null && lastName == null) {

            endResult = cmaMapper.convertAnEntityListToReturnContactDTOList(contactManagementAppRepository.findAll());

        } else if (firstName != null && lastName == null) {

            List<Contact> result = contactManagementAppRepository.findAll();

            List<ReturnContactDTO> newContactDTOList = cmaMapper.convertAnEntityListToReturnContactDTOList(result);

            Predicate<ReturnContactDTO> findByFirstName = i -> i.getFirstName().equals(firstName);

            endResult = newContactDTOList.stream().filter(findByFirstName).toList();

        } else if (firstName == null) {

            List<Contact> result = contactManagementAppRepository.findAll();

            List<ReturnContactDTO> newContactDTOList = cmaMapper.convertAnEntityListToReturnContactDTOList(result);

            Predicate<ReturnContactDTO> findByLastName = i -> i.getLastName().equals(lastName);

            endResult = newContactDTOList.stream().filter(findByLastName).toList();

        } else if (firstName != null && lastName != null) {

            List<Contact> result = contactManagementAppRepository.findAll();

            List<ReturnContactDTO> newContactDTOList = cmaMapper.convertAnEntityListToReturnContactDTOList(result);

            Predicate<ReturnContactDTO> findByFirstName = i -> i.getFirstName().equals(firstName);
            Predicate<ReturnContactDTO> findByLastName = i -> i.getLastName().equals(lastName);

            endResult = newContactDTOList.stream().filter(findByFirstName).filter(findByLastName).toList();
        }


        return endResult;
    }

    public List<ReturnContactDTO> filterContactsUsingNamedQuries(String firstName, String lastName) {

        List endResult = null;

        if (firstName == null && lastName == null) {

            endResult = contactManagementAppRepository.findAll();

        } else if (firstName != null && lastName == null) {

            Query queryEmployeesByFirstName = entityManager.createNamedQuery(
                    "findByFirstName"
            );

            queryEmployeesByFirstName.setParameter("firstName", firstName);

            endResult = queryEmployeesByFirstName.getResultList();

        } else if (firstName == null) {

            Query queryEmployeesByLastName = entityManager.createNamedQuery(
                    "findByLastName"
            );

            queryEmployeesByLastName.setParameter("lastName", lastName);
            endResult = queryEmployeesByLastName.getResultList();

        } else if (firstName != null && lastName != null) {

            Query queryEmployeesByFirstNameAndLastName = entityManager.createNamedQuery(
                    "findByFirstNameAndLastName"
            );

            queryEmployeesByFirstNameAndLastName.setParameter("firstName", firstName).setParameter("lastName", lastName);

            endResult = queryEmployeesByFirstNameAndLastName.getResultList();

        }
        return cmaMapper.convertAnEntityListToReturnContactDTOList(endResult);
    }
}