package edu.mum.builder;

import edu.mum.domain.Buyer;
import edu.mum.domain.User;

public class BuyerBuilder {

	private Buyer buyer;

	public BuyerBuilder(User user) {
		buyer = new Buyer();
		buyer.setUser(user);
	}
	
	public BuyerBuilder withPoints(Integer p) {
		buyer.setPoints(p);
		return this;
	}

	public Buyer build() {
		return buyer;
	}
}
