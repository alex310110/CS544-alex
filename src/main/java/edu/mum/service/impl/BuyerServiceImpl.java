package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.BuyerDao;
import edu.mum.domain.Buyer;

@Service
@Transactional
public class BuyerServiceImpl implements edu.mum.service.BuyerService {

	@Autowired
	private BuyerDao buyerDao;

	public void save(Buyer buyer) {
		buyerDao.save(buyer);
	}

	public List<Buyer> findAll() {
		return (List<Buyer>) buyerDao.findAll();
	}

	public Buyer findByEmail(String email) {
		return buyerDao.findByEmail(email);
	}

	public Buyer update(Buyer buyer) {
		return buyerDao.update(buyer);

	}
}
