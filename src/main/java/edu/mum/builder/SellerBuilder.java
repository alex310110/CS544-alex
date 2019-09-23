package edu.mum.builder;

import edu.mum.domain.Seller;
import edu.mum.domain.User;

public class SellerBuilder {

	private Seller seller;

	public SellerBuilder(User user) {
		seller = new Seller();
		seller.setUser(user);
	}
	
	public SellerBuilder withName(String name) {
		seller.setName(name);
		return this;
	}
	
	public SellerBuilder withDesc(String desc) {
		seller.setDescription(desc);
		return this;
	}
	
	public SellerBuilder withPic(String pic) {
		seller.setPicture(pic);
		return this;
	}

	public Seller build() {
		return seller;
	}
}
