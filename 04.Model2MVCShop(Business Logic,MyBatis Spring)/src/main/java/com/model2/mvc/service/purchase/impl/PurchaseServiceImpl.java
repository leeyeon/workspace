package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addPurchase(Purchase purchase, int code) throws Exception {
		
		int addResult = purchaseDao.addPurchase(purchase);
		int updateAmount = purchaseDao.updateAmount(purchase, code);
		
		if(addResult == updateAmount) {
			return addResult;
		} else {
			return 0;
		}
	}

	@Override
	public Purchase getPurchaseBytranNo(int tranNo) throws Exception {
		return purchaseDao.getPurchaseBytranNo(tranNo);
	}

	@Override
	public Purchase getPurchaseByprodNo(int prodNo) throws Exception {
		return purchaseDao.getPurchaseByprodNo(prodNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		List<Purchase> list = purchaseDao.getPurchaseList(search, buyerId);
		
		int totalCount = purchaseDao.getTotalCount(search,buyerId);
		//System.out.println(search);
		//System.out.println(buyerId);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		
		List<Purchase> list = purchaseDao.getSaleList(search);
		int totalCount = purchaseDao.getTotalCount(search);
		//System.out.println(search);
		//System.out.println(buyerId);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public int updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.updatePurchase(purchase);
	}

	@Override
	public int updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.updateTranCode(purchase);
	}

}
