package com.softintel.web;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

import com.softintel.dao.ContactRepository;
import com.softintel.entities.Contact;

@WebMvcTest(ContactRestController.class)
@MockBeans({@MockBean(ContactRepository.class)})
public class ContactRestControllerUnitTest extends UnitTests{

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ContactRepository contactReporitory;
	
	private static List<Contact> contacts;
	
	@BeforeAll
	static void setup() {
		fillContactsForTest();
	}
	
	static void fillContactsForTest(){
		contacts = new ArrayList<>();
		Contact c1 = new Contact("FAYE", "Ibrahima",null , "iba.faye7@gmail.com", 774356750, "");
		c1.setId(1L);
		contacts.add(c1);
		Contact c2 = new Contact("FAYE", "Abdoulaye",null , "abdou.faye@gmail.com", 778874378, "");
		c2.setId(2L);
		contacts.add(c2);
	}
	
	@Test
	void GET_ALL_CONTACTS_ISOK_AND_HAS_2_ELEMENTS() throws Exception {
		
		given(contactReporitory.findAll())
        .willReturn(contacts);
		
		mvc.perform(get("/contacts")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(2)));
	}
}
