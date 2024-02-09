package cma.api.mapper;

import cma.api.model.Contact;
import cma.api.dto.ContactDTO;
import org.springframework.stereotype.Component;
@Component
public class CMAMapper {
    public ContactDTO convertAnEntityToDTO(Contact contact) {
        return new ContactDTO(contact.getId(),contact.getFirstName(),contact.getLastName(),contact.getDateOfBirth(),contact.getAddress(),contact.getMobileNumber());
    }
    public Contact convertDTOToAnEntity(ContactDTO contactDTO) {

        return new Contact(contactDTO.getId(),contactDTO.getFirstName(),contactDTO.getLastName(),contactDTO.getDateOfBirth(),contactDTO.getAddress(),contactDTO.getMobileNumber());
    }
}
