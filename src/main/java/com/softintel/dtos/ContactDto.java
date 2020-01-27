package com.softintel.dtos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.softintel.entities.Contact;
import com.softintel.utils.IntraDateUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto implements Serializable{

	private Long id;
	@NotNull(message = "lastname should not be null")
	private String lastName;
	@NotNull(message = "firstname should not be null")
	private String firstName;
	private String birthDate;
	@NotNull(message = "email should not be null")
	private String email;
	private long tel;
	private String photoUrl;
	
	public Supplier<Contact> mapToEntity = ()->{
		Contact c = new Contact();
		c.setId(this.getId());
		c.setLastName(this.getLastName());
		c.setFirstName(this.getFirstName());
		c.setBirthDate(IntraDateUtils.textToDateParser.apply(
				this.getBirthDate(), 
				new SimpleDateFormat("dd/MM/yyyy"),
				"erreur de formatage de la date "+this.getBirthDate()));
		c.setEmail(this.getEmail());
		c.setTel(this.getTel());
		c.setPhotoUrl(this.getPhotoUrl());
		return c;
	};
}
