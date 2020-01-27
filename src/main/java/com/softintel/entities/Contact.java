package com.softintel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contact implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String lastName;
	private String firstName;
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	private String email;
	private long tel;
	private String photoUrl;
	
	public Contact(String lastName, String firstName, Date birthDate, String email, long tel, String photoUrl) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.email = email;
		this.tel = tel;
		this.photoUrl = photoUrl;
	}

}
