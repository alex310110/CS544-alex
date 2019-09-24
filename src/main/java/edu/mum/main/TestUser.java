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

	void testAFewUser() {
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
	}

	void mockData() {
		System.out.println("********* Mock More Data **********");
		final int mockNum = 200;
		final int mockFollowStep = 10;

		// mock sellers
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

		// mock buyers
		for (int i = 0; i < mockNum; i++) {
			String name = "buyer" + i;
			Buyer buyer = new BuyerBuilder(new UserBuilder()
					.withFirstName(name)
					.withLastName(name)
					.withEmail(name + "@hotmail.com")
					.build())
							.withPoints(i)
							.build();

			// mock buyer follows sellers
			for (int j = i % mockFollowStep; j < mockNum; j += mockFollowStep) {
				buyer.followSeller(mockSellers.get(j));
			}
			buyerService.save(buyer);

			// print progress
			if (i % 10 == 0) {
				System.out.print(i + "/" + mockNum + "\r");
			}
		}

		System.out.println("Done");
	}

	void testPerformance() {
		System.out.println("********* Performance **********");

		long startTime = System.currentTimeMillis();
		List<Buyer> buyers = buyerService.findAllPerf();
		long endTime = System.currentTimeMillis();
		System.out.println("buyerService.findAllPerf() took " +
				(endTime - startTime) + " ms");

		Buyer buyer = buyers.get(9);
		List<Seller> sellers = buyer.getSellers();
		System.out.println(buyer.getUser().getFirstName() +
				" is following " + sellers.size() + " sellers");
		System.out.println("First seller: " +
				sellers.get(0).getUser().getFirstName());
		System.out.println("Last seller: " +
				sellers.get(sellers.size() - 1).getUser().getFirstName());
	}

	public void testUser() {
		testAFewUser();
		mockData();
		testPerformance();
	}
}
