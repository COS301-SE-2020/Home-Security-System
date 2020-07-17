package com.springboot.SpringBackend.dto;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.Notification;

import java.io.Serializable;

public class UserDTO implements Serializable{
	public enum UserRole {
		Admin, Advanced, Basic;
	}

	private static final long serialVersionUID = 1L;
	private Long id;
	private String fname;
	private String lname;
	private String email;
	private String username;
	private String userPass;
	private UserRole userRole;
	private String deletionDate;

	private Image profilePhoto;
	//private Notification notification;

	public UserDTO() { }

	public UserDTO(String name, String surname, String email, String username, String pass, String role, Image id) {
		this.fname = name;
		this.lname = surname;
		this.email = email;
		this.username = username;
		this.userPass = pass;

		if(role.equalsIgnoreCase("Admin"))
		{
			this.userRole = UserRole.Admin;
		}
		else if(role.equalsIgnoreCase("Advanced"))
		{
			this.userRole = UserRole.Advanced;
		}
		else
		{
			this.userRole = UserRole.Basic;
		}

		this.profilePhoto = id;
	}

	public UserDTO(String name, String surname, String email, String username, String pass, String role) {
		this.fname = name;
		this.lname = surname;
		this.email = email;
		this.username = username;
		this.userPass = pass;

		if(role.equalsIgnoreCase("Admin"))
		{
			this.userRole = UserRole.Admin;
		}
		else if(role.equalsIgnoreCase("Advanced"))
		{
			this.userRole = UserRole.Advanced;
		}
		else
		{
			this.userRole = UserRole.Basic;
		}
	}

	public Long getUserId() {
		return this.id;
	}
	public void setUserId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.fname;
	}
	public void setName(String name) {
		this.fname = name;
	}

	public String getSurname() {
		return this.lname;
	}
	public void setSurname(String surname) {
		this.lname = surname;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}
	public void setUsername(String uname) {
		this.username = uname;
	}

	public String getPassword() {
		return this.userPass;
	}
	public void setPassword(String password) {
		this.userPass = password;
	}

	public String getRole() {
		return this.userRole.toString();
	}

	public void setRole(String role) {
		if(role.equalsIgnoreCase("Admin"))
		{
			this.userRole = UserRole.Admin;
		}
		else if(role.equalsIgnoreCase("Advanced"))
		{
			this.userRole = UserRole.Advanced;
		}
		else
		{
			this.userRole = UserRole.Basic;
		}
	}

	public Long getProfilePhotoById() { return this.profilePhoto.getImageId(); }
	public Image getProfilePhoto() { return this.profilePhoto; }
	public void setProfilePhoto(Image photo) { this.profilePhoto = photo; }

	public String getDeletionDate() {
		return this.deletionDate;
	}
	public void setDeletionDate(String date) {
		this.deletionDate = date;
	}

	@Override
	public String toString() {
		return "User [user_ID=" + id + ", fname=" + fname + ", lname=" + lname +
				", email=" + email + ", username=" + username +
				", userPass=" + userPass + ", userRole=" + userRole +
				", profilePhoto=" + profilePhoto.getImageId() +
				", deletionDate=" + deletionDate + "]";
	}
}
