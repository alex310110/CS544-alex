package edu.mum.dao;

import edu.mum.domain.Buyer;

public interface BuyerDao extends GenericDao<Buyer> {
      
	public Buyer findByEmail(String email);
}
