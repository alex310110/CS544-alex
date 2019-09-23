package edu.mum.dao;

import edu.mum.domain.Seller;

public interface SellerDao extends GenericDao<Seller> {
      
	public Seller findByEmail(String email);
}
