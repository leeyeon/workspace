package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDAO {

	public ProductDAO() {
	}
	
	public Product findProduct(int index) throws Exception {

		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT PRODUCT.PROD_NO AS PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, "
					 + " TRAN_STATUS_CODE, IMAGE_FILE, REG_DATE, PRODUCT.AMOUNT AS AMOUNT "
					 + " FROM PRODUCT , TRANSACTION"
					 + " WHERE product.prod_no = transaction.prod_no(+)"
					 + " AND PRODUCT.PROD_NO = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, index);
		
		//System.out.println("�̾߾ƾƾƤ���");
		
		ResultSet rs = pstmt.executeQuery();
		
		Product product = null;
		while(rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			product.setProTranCode(rs.getString("TRAN_STATUS_CODE"));
			int amount = rs.getInt("AMOUNT");
			
			if(rs.getString("TRAN_STATUS_CODE") == null) {
				if(amount == 0) {
					amount = 1;
				}
			} else {
				if(amount == 0) {
					amount = 0;
				} else {
					amount--;
				}
			}
			
			product.setAmount(amount);
		}
		
		System.out.println(product);
		
		rs.close();
		pstmt.close();
		con.close();
		
		return product;
	}
	
	// arrayList : product list // count : total count
	public Map<String,Object> getProductList(Search search) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT DISTINCT TRAN_STATUS_CODE, PRODUCT.PROD_NO AS PROD_NO, PROD_NAME, "
					+" PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE, PRODUCT.AMOUNT AS AMOUNT"
					+" FROM product, transaction"
					+" WHERE product.prod_no = transaction.prod_no(+)";

		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0") && !search.getSearchKeyword().equals("")) {
				sql += " AND PRODUCT.PROD_NO LIKE '" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " AND PROD_NAME LIKE '" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("")) {
				sql += " AND PRICE >=" + search.getSearchKeyword();
			}
		}
		
		String code = search.getSearchOrderbyPrice();
		// admin �� �� 2�� ù��°!!
		// user �� �� 2 -> null
		//sql +=" ORDER BY AMOUNT, DECODE(TRAN_STATUS_CODE, 2, 'a', 1,'b', 3,'c') NULLS FIRST"
		//		+ ", PRODUCT.PROD_NO";
		
		sql += " ORDER BY TRAN_STATUS_CODE NULLS FIRST, PRODUCT.AMOUNT";
		
		// ���� ��
		if((code != null) || ("".equals(code))) {
			if(code.equals("0")) {
				sql +=" , PRICE DESC";
			} else if(code.equals("1")) {
				sql +=" , PRICE ASC";
			}
		} else {
			sql += " , PRODUCT.PROD_NO";
		}
		
		//System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		//System.out.println("ProductDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage �Խù��� �޵��� Query �ٽñ���
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
	
		System.out.println(search);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()) {
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			product.setProTranCode(rs.getString("TRAN_STATUS_CODE"));
			int amount = rs.getInt("AMOUNT");
			
			if(rs.getString("TRAN_STATUS_CODE") == null) {
				if(amount == 0) {
					amount = 1;
				}
			} else {
				if(amount == 0) {
					amount = 0;
				} else {
					amount--;
				}
			}
			product.setAmount(amount);
			
			list.add(product);
		}
		
		//==> totalCount ���� ����
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage �� �Խù� ���� ���� List ����
		map.put("list", list);
		
		rs.close();
		pstmt.close();
		con.close();
		
		return map;
	}
	
	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO PRODUCT"
					+ "(PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, "
					+ "IMAGE_FILE, REG_DATE, AMOUNT, BEFOREAMOUNT)"
					+ "VALUES (seq_product_prod_no.nextval,?,?,?,?,?,SYSDATE,?,?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, product.getProdName());
		pstmt.setString(2, product.getProdDetail());
		pstmt.setString(3, product.getManuDate().replaceAll("-", "").trim());
		pstmt.setInt(4, product.getPrice());
		pstmt.setString(5, product.getFileName());
		pstmt.setInt(6, product.getAmount());
		pstmt.setInt(7, product.getAmount());
		pstmt.executeUpdate();
		
		con.close();
		
	}
	
	public void updateProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE PRODUCT SET PROD_NAME = ?, PROD_DETAIL = ?, "
					+ " MANUFACTURE_DAY = ?, PRICE = ?, IMAGE_FILE = ?"
					+ " WHERE PROD_NO = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, product.getProdName());
		pstmt.setString(2, product.getProdDetail());
		pstmt.setString(3, product.getManuDate().replaceAll("-", "").trim());
		pstmt.setInt(4, product.getPrice());
		pstmt.setString(5, product.getFileName());
		pstmt.setInt(6, product.getProdNo());
		pstmt.executeUpdate();
		
		con.close();
	}
	
	// �Խ��� Page ó���� ���� ��ü Row(totalCount)  return
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
	
	// �Խ��� currentPage Row ��  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		//System.out.println("ProductDAO :: make SQL :: "+ sql);	
		
		return sql;
	}

}
