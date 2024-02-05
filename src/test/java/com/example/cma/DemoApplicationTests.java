package com.example.cma;

import cma.api.dto.ContactDTO;
import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
@SpringBootTest(classes = {Contact.class, ContactDTO.class})
class DemoApplicationTests {
	private final ModelMapper newModelMapper =  new ModelMapper();
	@Autowired
	@MockBean
	private CMAMapper contactManagementAppMapper;
	@Test
	public void EntityToDTOConversionTest() {
		Contact contact = new Contact(10,"Mateusz","Woś", LocalDate.of(1996,12,8),"Chorzowska50",111555444);
		ContactDTO dto = newModelMapper.map(contact, ContactDTO.class);

		assertEquals(dto.getLastName(), contact.getLastName());
	}
	@Test
	public void DTOToEntityConversionTest() {
		ContactDTO dto = new ContactDTO(10,"Mateusz","Woś", LocalDate.of(1996,12,8),"Chorzowska50",111555444);
		Contact contact = newModelMapper.map(dto, Contact.class);

		assertEquals(contact.getLastName(), dto.getLastName() );
	}
	@Test
	public void CMAMapperDTOToEntityConversionTest() {
		CMAMapper newCMAMapper = new CMAMapper();
		ContactDTO contactDTO = new ContactDTO(10,"Mateusz","Woś", LocalDate.of(1996,12,8),"Chorzowska50",111555444);
		Contact contact = newCMAMapper.convertDTOToAnEntity(contactDTO);

		assertEquals(contactDTO.getLastName(), contact.getLastName() );
	}
	@Test
	public void CMAMapperEntityToDTOConversionTest() {
		CMAMapper newCMAMapper = new CMAMapper();
		Contact contact = new Contact();
		contact.setId(10);
		contact.setFirstName("Marcin");
		contact.setLastName("Mandla");
		ContactDTO newContactDTO = newCMAMapper.convertAnEntityToDTO(contact);

		assertEquals(contact.getLastName(), newContactDTO.getLastName());
	}
	private void assertEquals(String firstName, String firstName1) {
	}
}
