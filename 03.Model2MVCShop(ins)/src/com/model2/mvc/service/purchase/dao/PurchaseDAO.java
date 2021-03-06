package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT PROD_NO, BUYER_ID, PAYMENT_OPTION, RECEIVER_NAME, RECEIVER_PHONE, " + 
							" DLVY_ADDR, DLVY_REQUEST, TRAN_STATUS_CODE, ORDER_DATE, DLVY_DATE" + 
							" FROM TRANSACTION" + 
							" WHERE TRAN_NO = ?";
		
		// TO_CHAR(DLVY_DATE, 'YYYYMMDD') AS DLVY_DATE
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, tranNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Purchase purchase = null;
		while(rs.next()) {
			purchase = new Purchase();
			
			purchase.setTranNo(tranNo);
			// setProduct
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			purchase.setPurchaseProd(product);
			// setBuyer
			User user = new User();
			user.setUserId(rs.getString("BUYER_ID"));
			purchase.setBuyer(user);
			
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DLVY_ADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchase.setOrderDate(rs.getDate("ORDER_DATE"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
		}
		
		System.out.println(purchase);
		
		con.close();

		return purchase;
	}
	
	public Purchase findPurchase2(int prodNo) throws Exception {
		
		System.out.println("findPurchase2 prodNo :: "+prodNo);
		Connection con = DBUtil.getConnection();
		String sql = "SELECT PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, " + 
							" IMAGE_FILE, REG_DATE" + 
							" FROM PRODUCT" + 
							" WHERE PROD_NO = ?";
		
		// TO_CHAR(DLVY_DATE, 'YYYYMMDD') AS DLVY_DATE
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, prodNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Purchase purchase = null;
		while(rs.next()) {
			purchase = new Purchase();
			
			purchase.setTranNo(prodNo);
			// setProduct
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			purchase.setPurchaseProd(product);
			// setBuyer
			//User user = new User();
			//purchase.setBuyer(user);
		}
		
		System.out.println(purchase);
		
		con.close();

		return purchase;
	}
	
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Connection con = DBUtil.getConnection();
		String sql = "SELECT TRAN_NO, PROD_NO, RECEIVER_NAME, RECEIVER_PHONE, TRAN_STATUS_CODE"
					+" FROM transaction"
					+" WHERE BUYER_ID = \'"+buyerId+"\'"
					+" ORDER BY TRAN_NO";
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = this.makeCurrentPageSql(sql, search);
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
			Purchase purchase = new Purchase();
			
			User buyer = new User();
			new User().setUserId(buyerId);
			
			purchase.setBuyer(buyer);
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			
			ProductService pService = new ProductServiceImpl();
			purchase.setPurchaseProd(pService.getProduct(rs.getInt("PROD_NO")));
			
			list.add(purchase);

		}
		
		map.put("totalCount", new Integer(totalCount));
		map.put("list", list);
		
		rs.close();
		pstmt.close();
		con.close();
		
		return map;
	}
	
	public Map<String,Object> getSaleList(Search search) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT TRANSACTION.AMOUNT AS AMOUNT, TRAN_STATUS_CODE, PRODUCT.PROD_NO AS PROD_NO, "
					+" USER_ID, PROD_NAME, PRICE, IMAGE_FILE, PRODUCT.REG_DATE AS REG_DATE"
					+" FROM product, transaction, users"
					+" WHERE product.prod_no = transaction.prod_no(+)"
					+" AND transaction.buyer_id = users.user_id"
					+" AND role = 'user'";
		
		String code = search.getSearchOrderbyPrice();

		sql += " ORDER BY TRAN_STATUS_CODE NULLS FIRST";
		
		// 높은 순
		if((code != null) || ("".equals(code))) {
			if(code.equals("0")) {
				sql +=" , PRICE DESC";
			} else if(code.equals("1")) {
				sql +=" , PRICE ASC";
			}
		} else {
			//sql += " , PRODUCT.PROD_NO";
		}
		
		//System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		//System.out.println("ProductDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
	
		System.out.println(search);

		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
			Purchase purchase = new Purchase();
			
			User user = new User();
			user.setUserId(rs.getString("USER_ID"));
			purchase.setBuyer(user);
			
			Product product = new Product();
			product.setProTranCode(rs.getString("TRAN_STATUS_CODE"));
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setPrice(rs.getInt("PRICE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			int amount = rs.getInt("AMOUNT");
			
			if(amount == 0) {
				amount = 1;
			}
			
			product.setAmount(amount);
			
			purchase.setPurchaseProd(product);
			System.out.println(product);
			list.add(purchase);
		}
		
		map.put("totalCount", new Integer(totalCount));
		map.put("list", list);
		
		rs.close();
		pstmt.close();
		con.close();
		
		return map;
	}
	
	public void insertPurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		User buyer = purchase.getBuyer();
		Product product = purchase.getPurchaseProd();
		
		String sql = "INSERT INTO TRANSACTION (TRAN_NO, PROD_NO, BUYER_ID, PAYMENT_OPTION, RECEIVER_NAME, RECEIVER_PHONE, "
										+ "DLVY_ADDR, DLVY_REQUEST, TRAN_STATUS_CODE, ORDER_DATE, DLVY_DATE)"
										+ " VALUES (seq_transaction_tran_no.nextval, ?,?,?,?,?, ?,?,?,?,SYSDATE)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, product.getProdNo());
		pstmt.setString(2, buyer.getUserId());
		pstmt.setString(3, purchase.getPaymentOption());
		pstmt.setString(4, purchase.getReceiverName());
		pstmt.setString(5, purchase.getReceiverPhone());
		pstmt.setString(6, purchase.getDivyAddr());
		pstmt.setString(7, purchase.getDivyRequest());
		pstmt.setString(8,"1"); // 구매완료
		pstmt.setString(9, purchase.getDivyDate().replaceAll("-", "").trim());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	public void updateAmount(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE PRODUCT"
					+ " SET AMOUNT = AMOUNT - ?"
					+ " WHERE PROD_NO = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, purchase.getPurchaseProd().getAmount());
		pstmt.setInt(2, purchase.getPurchaseProd().getProdNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();		
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE TRANSACTION SET PAYMENT_OPTION = ?, RECEIVER_NAME = ?, RECEIVER_PHONE = ?, "
					+ " DLVY_ADDR = ?, DLVY_REQUEST = ?, DLVY_DATE = ?"
					+ " WHERE TRAN_NO = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, purchase.getPaymentOption());
		pstmt.setString(2, purchase.getReceiverName());
		pstmt.setString(3, purchase.getReceiverPhone());
		pstmt.setString(4, purchase.getDivyAddr());
		pstmt.setString(5, purchase.getDivyRequest());
		pstmt.setString(6, purchase.getDivyDate());
		pstmt.setInt(7, purchase.getTranNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String tranCode = purchase.getTranCode().trim();
		int value = 0;
		
		String sql = "UPDATE TRANSACTION SET TRAN_STATUS_CODE = ?";
		
		// tranNo
		if("3".equals(tranCode)) {
			sql += " WHERE TRAN_NO = ?";
			value = purchase.getTranNo();
		} else {
			sql += " WHERE PROD_NO = ?";
			value = purchase.getPurchaseProd().getProdNo();
		}
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, purchase.getTranCode().trim());
		pstmt.setInt(2, value);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		rs.close();
		pStmt.close();
		con.close();		
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +
					" AND "+search.getCurrentPage()*search.getPageSize();
		
		//System.out.println("PurchaseDAO :: make SQL :: "+ sql);	
		
		return sql;
	}

}
