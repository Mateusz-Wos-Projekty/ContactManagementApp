package com.example.cma;

import cma.api.dto.ContactDTO;
import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;


@SpringBootTest(classes = {Contact.class, ContactDTO.class})
class DemoApplicationTests {
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