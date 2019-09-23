package edu.mum.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.SellerDao;
import edu.mum.domain.Seller;

@Repository
public class SellerDaoImpl extends GenericDaoImpl<Seller> implements SellerDao {

	public SellerDaoImpl() {
		super.setDaoType(Seller.class);
	}

	public Seller findByEmail(String email) {

		Query query = entityManager.createQuery(
				"select s from Seller s join s.user u where u.email =:email");
		return (Seller) query.setParameter("email", email).getSingleResult();

	}

}