package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.builder.BuyerBuilder;
import edu.mum.builder.SellerBuilder;
import edu.mum.builder.UserBuilder;
import edu.mum.domain.Buyer;
import edu.mum.domain.Seller;
import edu.mum.domain.User;
import edu.mum.service.BuyerService;
import edu.mum.service.SellerService;
import edu.mum.service.UserService;

@Component
public class TestUser {

	@Autowired
	UserService userService;

	@Autowired
	BuyerService buyerService;

	@Autowired
	SellerService sellerService;

	public void testUser() {
		Buyer buyer = new BuyerBuilder(new UserBuilder()
				.withFirstName("John")
				.withLastName("Smith")
				.withEmail("jos@gmail.com")
				.build())
						.withPoints(8)
						.build();

		Seller seller = new SellerBuilder(new UserBuilder()
				.withFirstName("Jane")
				.withLastName("Doe")
				.withEmail("jad@hotmail.com")
				.build())
						.withName("JD")
						.withDesc("Jane Doe")
						.withPic("jd.jpg")
						.build();
		
		sellerService.save(seller);
		buyer.followSeller(seller);
		buyerService.save(buyer);

		System.out.println();
		System.out.println("*********  User **********");

		User user;
		user = userService.findByEmail("jos@gmail.com");
		System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());
		user = userService.findByEmail("jad@hotmail.com");
		System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());
	}
}
