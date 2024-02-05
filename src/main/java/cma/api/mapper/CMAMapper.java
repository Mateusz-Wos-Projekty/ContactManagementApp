package cma.api.mapper;

import cma.api.dto.ContactDTO;
import cma.api.model.Contact;
import org.modelmapper.ModelMapper;
public class CMAMapper {
    private final ModelMapper CMAmodelMapper = new ModelMapper();
    public  ContactDTO convertAnEntityToDTO(Contact contact) {
        ContactDTO contactDto = CMAmodelMapper.map(contact, ContactDTO.class);

        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setDateOfBirth(contact.getDateOfBirth());
        contactDto.setAddress(contact.getAddress());
        contactDto.setMobileNumber(contact.getMobileNumber());

        return contactDto;
    }
    public Contact convertDTOToAnEntity(ContactDTO contactDTO) {
        Contact contact = CMAmodelMapper.map(contactDTO, Contact.class);

        contact.setId(contact.getId());
        contact.setFirstName(contact.getFirstName());
        contact.setLastName(contact.getLastName());
        contact.setDateOfBirth(contact.getDateOfBirth());
        contact.setAddress(contact.getAddress());
        contact.setMobileNumber(contact.getMobileNumber());

        return contact;
    }
}
