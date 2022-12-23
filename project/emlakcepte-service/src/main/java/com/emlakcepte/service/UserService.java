package com.emlakcepte.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.User;
import com.emlakcepte.repository.UserRepository;


@Service
public class UserService{
		
	@Autowired
	private UserRepository userRepository;
	/*
	 * First of all, it is checked by email whether the entered user is in the system.
	 * If the conditions of the password and e-mail entered by the user are met, the user is created.
	 */
	public User createUser(User user) {			
		try {
			User person = getByEmail(user.getMail());
			if (person != null) {
				System.out.println("Already has account:: " + user);
				return null;
			}
			else {
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
		return userRepository.findAllUsers();
	}
	// Return suer by given name
	public User getByName(String name) {
		return userRepository.getByName(name);
	}
	// Updates user password
	public User updatePassword(String email, User updateInfo) {
		User user = getByEmail(updateInfo.getMail());
		if (user != null && isPasswordValid(updateInfo.getPassword()))
			return userRepository.updateUser(user, updateInfo);
		return null;
	}
	// Returns user by given email
	public User getByEmail(String email) {
		return userRepository.getByEmail(email);
	}
	// Deletes users by given email
	public  User deleteUser(String email) {
		User user = getByEmail(email);
		if (user != null)
			return userRepository.deleteByEmail(user);
		return null;
	}

}
