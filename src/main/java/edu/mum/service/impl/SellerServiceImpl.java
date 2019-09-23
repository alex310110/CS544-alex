package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.SellerDao;
import edu.mum.domain.Seller;

@Service
@Transactional
public class SellerServiceImpl implements edu.mum.service.SellerService {

	@Autowired
	private SellerDao sellerDao;

	public void save(Seller seller) {
		sellerDao.save(seller);
	}

	public List<Seller> findAll() {
		return (List<Seller>) sellerDao.findAll();
	}

	public Seller findByEmail(String email) {
		return sellerDao.findByEmail(email);
	}

	public Seller update(Seller seller) {
		return sellerDao.update(seller);

	}
}
