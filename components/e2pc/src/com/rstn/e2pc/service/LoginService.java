package com.rstn.e2pc.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.rstn.e2pc.Constants;
import com.rstn.e2pc.model.Address;
import com.rstn.e2pc.model.User;


public class LoginService {

	@SuppressWarnings("unchecked")
	public List<String> getUsers() {
		UserManager userManager = (UserManager) ApplicationContextProvider.getAppContext().getBean("userManager");
		List<User> users = userManager.getUsers(new User());
		List<String> names = new ArrayList<String>();
		for (User user : users) {
			names.add(user.getUsername());
		}
		return names;
	}
	
	public String createUser(String login) {
		User user = new User();
		user.setUsername(login);
		user.setFirstName("Demo");
		user.setLastName("Create");
		user.setEmail(login + "@e2pc.com");
		user.setWebsite("http://localhost:8080");
        user.setPasswordHint("Password is password.");
		user.setPassword("password");
		user.setConfirmPassword("password");
		
		Address address = new Address();
		address.setCity("Singapore");
		address.setCountry("Singapore");
		address.setAddress("Singapore");
		address.setPostalCode("0");
		address.setProvince("Singapore");
		user.setAddress(address);
		
		user.setEnabled(true);
		try {
			RoleManager roleManager = (RoleManager) ApplicationContextProvider.getAppContext().getBean("roleManager");
			user.addRole(roleManager.getRole(Constants.USER_ROLE));
			UserManager userManager = (UserManager) ApplicationContextProvider.getAppContext().getBean("userManager");
			userManager.saveUser(user);
		}
		catch (UserExistsException e) {
			e.printStackTrace();
		}
		return login;
	}
	
	public boolean deleteUser(String login) {
		boolean success = false;
		if (!"admin".equals(login) && !"user".equals(login)) {
			UserManager userManager = (UserManager) ApplicationContextProvider.getAppContext().getBean("userManager");
			try {		
				User user = userManager.getUserByUsername(login);
				userManager.removeUser(String.valueOf(user.getId()));
				success = true;
			}
			catch (UsernameNotFoundException e) {
			}
		}
		return success;
	}
}
