package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.BuyerDao;
import edu.mum.domain.Buyer;
import edu.mum.domain.Seller;

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

	public List<Buyer> findAllPerf() {
		List<Buyer> ret = this.findAll();

		for (Buyer buyer : ret) {
			List<Seller> sellers = buyer.getSellers();
			if (!sellers.isEmpty()) {
				sellers.get(0);
			}
		}

		return ret;
	}

	public Buyer findByEmail(String email) {
		return buyerDao.findByEmail(email);
	}

	public Buyer update(Buyer buyer) {
		return buyerDao.update(buyer);

	}

}
