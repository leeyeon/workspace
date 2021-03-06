package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {

	public ProductDAO() {
	}
	
	public ProductVO findProduct(int index) throws Exception {
		
		ProductVO productVO = new ProductVO();
		
		Connection con = DBUtil.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement("SELECT PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE"
													 + " FROM PRODUCT"
													 + " WHERE PROD_NO = ?");
		pstmt.setInt(1, index);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		System.out.println(productVO);
		
		con.close();
		
		return productVO;
	}
	
	// arrayList : product list // count : total count
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		Connection con = DBUtil.getConnection();
		String sql = "SELECT TRAN_STATUS_CODE, PRODUCT.PROD_NO AS PROD_NO, PROD_NAME, "
					+" PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE"
					+" FROM product, transaction"
					+" WHERE product.prod_no = transaction.prod_no(+)";
		
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " AND PRODUCT.PROD_NO LIKE '" + searchVO.getSearchKeyword()
						+ "%'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " AND PROD_NAME LIKE '" + searchVO.getSearchKeyword()
						+ "%'";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += " AND PRICE >=" + searchVO.getSearchKeyword();
			}
		}
					
		sql +=" ORDER BY PRODUCT.PROD_NO";
		
		PreparedStatement pstmt = con.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = pstmt.executeQuery();
		
		// rs 끝으로 커서 이동
		rs.last();
		int total = rs.getRow();
		System.out.println("상품 개수: " + total);
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		//System.out.println("searchVO.getPage():" + searchVO.getPage());
		//System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO productVO = new ProductVO();
				productVO.setProdNo(rs.getInt("PROD_NO"));
				productVO.setProdName(rs.getString("PROD_NAME"));
				productVO.setProdDetail(rs.getString("PROD_DETAIL"));
				productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
				productVO.setPrice(rs.getInt("PRICE"));
				productVO.setFileName(rs.getString("IMAGE_FILE"));
				productVO.setRegDate(rs.getDate("REG_DATE"));
				productVO.setProTranCode(rs.getString("TRAN_STATUS_CODE"));
				
				list.add(productVO);
				if (!rs.next())
					break;
			}
		}
		
		//System.out.println("Productlist.size() : "+ list.size());
		map.put("list", list);
		//System.out.println("map().size() : "+ map.size());
		
		con.close();
		
		return map;
	}
	
	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO PRODUCT (PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE)"
							+ "VALUES (seq_product_prod_no.nextval,?,?,?,?,?,SYSDATE)");
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate().replaceAll("-", "").trim());
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());
		pstmt.executeUpdate();
		
		con.close();
		
	}
	
	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		PreparedStatement pstmt = 
				con.prepareStatement("UPDATE PRODUCT SET PROD_NAME = ?, PROD_DETAIL = ?, "
							+ "MANUFACTURE_DAY = ?, PRICE = ?, IMAGE_FILE = ?"
							+ " WHERE PROD_NO = ?");
		
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate().replaceAll("-", "").trim());
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());
		pstmt.setInt(6, productVO.getProdNo());
		pstmt.executeUpdate();
		
		con.close();
	}

}
