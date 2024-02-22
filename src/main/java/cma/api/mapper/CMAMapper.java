package cma.api.mapper;

import cma.api.dto.CreateContactDTO;
import cma.api.dto.ReturnContactDTO;
import cma.api.model.Contact;
import cma.api.dto.ContactDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CMAMapper {
    public ContactDTO convertAnEntityToDTO(Contact contact) {
        return new ContactDTO(contact.getId(),contact.getFirstName(),contact.getLastName(),contact.getDateOfBirth(),contact.getAddress(),contact.getMobileNumber());
    }
    public Contact convertDTOToAnEntity(ContactDTO contactDTO) {

        return new Contact(contactDTO.getId(),contactDTO.getFirstName(),contactDTO.getLastName(),contactDTO.getDateOfBirth(),contactDTO.getAddress(),contactDTO.getMobileNumber());
    }
    public ReturnContactDTO convertCreateContactDTOToReturnContactDTO(CreateContactDTO createContactDTO){

        return new ReturnContactDTO(createContactDTO.getId(),createContactDTO.getFirstName(),createContactDTO.getLastName(),createContactDTO.getDateOfBirth(),createContactDTO.getAddress(),createContactDTO.getMobileNumber());

    }
    public Contact convertCreateContactDTOToAnEntity(CreateContactDTO createContactDTO){

        return new Contact(createContactDTO.getId(),createContactDTO.getFirstName(),createContactDTO.getLastName(),createContactDTO.getDateOfBirth(),createContactDTO.getAddress(),createContactDTO.getMobileNumber());

    }
    public ReturnContactDTO convertAnEntityToReturnContactDTO(Contact contact) {
        return new ReturnContactDTO(contact.getId(),contact.getFirstName(),contact.getLastName(),contact.getDateOfBirth(),contact.getAddress(),contact.getMobileNumber());
    }

    public java.util.List<ReturnContactDTO> convertAnEntityListToReturnContactDTOList(java.util.List<Contact> contact) {

        java.util.List<ReturnContactDTO> newList = new ArrayList<>() ;

        for (int i = 0; i < contact.size(); i++) {
            Contact contact1 = contact.get(i);

            ReturnContactDTO newReturnContactDTO = convertAnEntityToReturnContactDTO(contact1);

            newList.add(newReturnContactDTO);

        }

        return newList;
    }

}
