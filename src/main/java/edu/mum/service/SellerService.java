package edu.mum.service;

import java.util.List;

import edu.mum.domain.Seller;
 
public interface SellerService {

	public void save(Seller seller);
	public List<Seller> findAll();
	public Seller findByEmail(String email);
	public Seller update(Seller seller);
}
