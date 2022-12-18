package com.medium.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medium.model.User;
import com.medium.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	/*
	 * First of all, it is checked by email whether the entered user is in the system.
	 * If the conditions of the password and e-mail entered by the user are met, the user is created.
	 */
	public User createUser(User user) {			
		try {
			User person = getByName(user.getName());
			if(person != null) {
				System.out.println("Already has account:: " + user);
				return null;
			}else {

			if(isPasswordValid(user.getPassword())) {
				if(isEmailValid(user.getMail())) {
					System.out.println("[createUser] user oluşturuldu :: " + user);
					return userRepository.createUser(user);	
				}else {
					System.out.println("Invalid email:: " + user);
					return null;
				}
			}else {
				System.out.println("Invalid password :: " + user);
				return null;
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//It is checked whether the entered email meets the conditions.
	public static boolean isEmailValid(String email) {
	    final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
	     if (email == null) {
	            return false;
	        }
	    return EMAIL_REGEX.matcher(email).matches();
	}
	// It is checked whether the entered password meets the conditions.
	public static boolean isPasswordValid(String password) {
		final Pattern STRING_REGEX = Pattern.compile( "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
	     if (password == null) {
	            return false;
	        }
		return STRING_REGEX.matcher(password).matches();
	}
	// Returns users
	public List<User> getAllUser() {
		return userRepository.getAllUsers();
	}
	// Return suer by given name
	public User getByName(String name) {
		return userRepository.getByName(name);
	}
	// Updates userpassword
	public User updatePassword(String name, User updateInfo) {
		User user = getByName(name);
		if(user != null && isPasswordValid(updateInfo.getPassword())) {
			return userRepository.updateUser(user, updateInfo);
		}
		return null;
	}
}
