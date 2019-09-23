package edu.mum.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.BuyerDao;
import edu.mum.domain.Buyer;

@Repository
public class BuyerDaoImpl extends GenericDaoImpl<Buyer> implements BuyerDao {

	public BuyerDaoImpl() {
		super.setDaoType(Buyer.class);
	}

	public Buyer findByEmail(String email) {

		Query query = entityManager.createQuery(
				"select b from Buyer b join b.user u where u.email =:email");
		return (Buyer) query.setParameter("email", email).getSingleResult();

	}

}