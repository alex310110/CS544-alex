package edu.mum.service;

import java.util.List;

import edu.mum.domain.Buyer;
 
public interface BuyerService {

	public void save(Buyer buyer);
	public List<Buyer> findAll();
	public List<Buyer> findAllPerf();
	public Buyer findByEmail(String email);
	public Buyer update(Buyer buyer);
}
