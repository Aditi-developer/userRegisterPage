package com.example.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long userId;
	 public  String username;
     public  String password;
     public  String firstname;
     public  String lastname;
     public  char gender;
     public  String date_of_birth;
     public  String country;
     public  String zipcode;
     public  String email;
     public String companyName;
     public String mobileNumber;
     public String role;
	
     public UserModel(String username, String password, String firstname, String lastname, char gender,
			String date_of_birth, String country, String zipcode, String email, String role) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.country = country;
		this.zipcode = zipcode;
		this.email = email;
		this.role = role;
	}

	public UserModel(String username, String password, String firstname, String lastname, char gender,
			String date_of_birth, String country, String zipcode, String email, String companyName, String mobileNumber,
			String role) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.country = country;
		this.zipcode = zipcode;
		this.email = email;
		this.companyName = companyName;
		this.mobileNumber = mobileNumber;
		this.role = role;
	}

     
}
