package edu.mum.main;

import java.util.ArrayList;
import java.util.List;

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

		System.out.println("********* User **********");
		User user;
		user = userService.findByEmail("jos@gmail.com");
		System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());
		user = userService.findByEmail("jad@hotmail.com");
		System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());

		System.out.println("********* Mock More Data **********");
		final int mockNum = 100;
		final int mockFollowStep = 10;

		List<Seller> mockSellers = new ArrayList<>();
		for (int i = 0; i < mockNum; i++) {
			String name = "seller" + i;
			mockSellers.add(new SellerBuilder(new UserBuilder()
					.withFirstName(name)
					.withLastName(name)
					.withEmail(name + "@gmail.com")
					.build())
							.withName(name)
							.withDesc(name)
							.withPic(name + ".jpg")
							.build());
			sellerService.save(mockSellers.get(i));
		}

		for (int i = 0; i < mockNum; i++) {
			String name = "buyer" + i;
			Buyer newBuyer = new BuyerBuilder(new UserBuilder()
					.withFirstName(name)
					.withLastName(name)
					.withEmail(name + "@hotmail.com")
					.build())
							.withPoints(i)
							.build();
			for (int j = i % mockFollowStep; j < mockNum; j+=mockFollowStep) {
				newBuyer.followSeller(mockSellers.get(j));
			}
			if (i % 10 == 0) {
				System.out.print(i + "/" + mockNum + "\r");
			}
			buyerService.save(newBuyer);
		}
		System.out.println("\nDone");
	}
}
