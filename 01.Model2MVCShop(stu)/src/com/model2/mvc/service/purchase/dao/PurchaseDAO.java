package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		return null;
	}
	
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		Connection con = DBUtil.getConnection();
		String sql = "SELECT TRAN_NO, RECEIVER_NAME, RECEIVER_PHONE, TRAN_STATUS_CODE"
					+" FROM users, product, transaction"
					+" WHERE users.user_id = transaction.buyer_id"
					+" AND product.prod_no = transaction.prod_no"
					+" AND USER_ID = ?"
					+" ORDER BY TRAN_NO";
		
		PreparedStatement pstmt = con.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		pstmt.setString(1, buyerId);
		
		ResultSet rs = pstmt.executeQuery();
		rs.last();
		int total = rs.getRow();
		System.out.println("�ֹ� ����: " + total);
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				
				UserService service = new UserServiceImpl();
				UserVO buyer = service.getUser(buyerId);
				
				purchaseVO.setBuyer(buyer);
				purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
				purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
				
				list.add(purchaseVO);
				if (!rs.next())
					break;
			}
		}
		
		map.put("list", list);
		con.close();
		
		return map;
	}
	
	public HashMap<String,Object> getSaleList(SearchVO searchVO) throws Exception {
		
		return null;
	}
	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		UserVO buyer = purchaseVO.getBuyer();
		ProductVO product = purchaseVO.getPurchaseProd();
		
		String sql = "INSERT INTO TRANSACTION (TRAN_NO, PROD_NO, BUYER_ID, PAYMENT_OPTION, RECEIVER_NAME, RECEIVER_PHONE, "
										+ "DLVY_ADDR, DLVY_REQUEST, TRAN_STATUS_CODE, ORDER_DATE, DLVY_DATE)"
										+ " VALUES (seq_transaction_tran_no.nextval, ?,?,?,?,?, ?,?,?,SYSDATE,?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, product.getProdNo());
		pstmt.setString(2, buyer.getUserId());
		pstmt.setString(3, purchaseVO.getPaymentOption());
		pstmt.setString(4, purchaseVO.getReceiverName());
		pstmt.setString(5, purchaseVO.getReceiverPhone());
		pstmt.setString(6, purchaseVO.getDivyAddr());
		pstmt.setString(7, purchaseVO.getDivyRequest());
		pstmt.setString(8,"1"); // ���ſϷ�
		pstmt.setString(9, purchaseVO.getDivyDate().replaceAll("-", "").trim());
		
		pstmt.executeUpdate();
		
		con.close();
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
	}

}