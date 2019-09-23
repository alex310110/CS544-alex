package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.builder.UserBuilder;
import edu.mum.domain.User;
import edu.mum.service.UserService;

@Component
public class TestUser {

	@Autowired
	UserService userService;

	public void testUser() {

		User user = new UserBuilder()
				.withFirstName("John")
				.withLastName("Doe")
				.withEmail("john@Doe.com")
				.build();
		userService.save(user);

		User readUser = userService.findByEmail("john@Doe.com");

		System.out.println();
		System.out.println("        *********  User **********");

		System.out.println("User Name: " + readUser.getFirstName() + " " + readUser.getLastName());

	}
}
