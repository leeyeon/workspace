package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

import junit.framework.Assert;

public class ProductTest {
	
	int prodNo = 10066;

	//@Test
	public void testAddProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		Product product = new Product();
		
		product.setProdName("testProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("testFileName");
		product.setAmount(5);
		
		System.out.println(":: testAddProduct ::");
		int result = sqlSession.insert("ProductMapper.addProduct", product);

		//==> console 확인
		System.out.println("addProduct ::" + result);
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20171115", product.getManuDate());
		Assert.assertEquals(1111, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals(5, product.getAmount());
		
		// delete from product where prod_name = "testProdName";
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		System.out.println(":: testSelectOneProduct ::");
		Product product = sqlSession.selectOne("ProductMapper.getProduct", prodNo);
		
		System.out.println(product);
		
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		Product product = new Product();
		
		product.setProdName("updateProdName");
		product.setProdDetail("updateProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("updateFileName");
		product.setProdNo(prodNo);
		
		System.out.println(":: testUpdateProduct ::");
		int result = sqlSession.update("ProductMapper.updateProduct", product);
		
		//==> console 확인
		System.out.println("updateProduct ::" + result);
		System.out.println(product);
		
	}
	
	//@Test
	public void testGetProductList() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	
	 	System.out.println(":: testUpdateProduct ::");
	 	List<Object> list = sqlSession.selectList("ProductMapper.getProductList", search);
	 	
	 	// 테스트!
	 	for(Object product: list) {
	 		System.out.println((Product)product);
	 	}                               
	}
	
	//@Test
	public void testGetTotalCount() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	
	 	System.out.println(":: testGetTotalCount ::");
	 	int result = sqlSession.selectOne("ProductMapper.getTotalCount", search);
	 	
	 	System.out.println("totalCount :: "+result);
		
	}

	/////////////////////////////////////////////////////////
	
	//@Test
	public void testDaoAddProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		Product product = new Product();
		
		product.setProdName("DaotestProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("testFileName");
		product.setAmount(5);
		
		System.out.println(":: testAddProduct ::");
		int result = productDao.addProduct(product);
		//==> console 확인
		System.out.println("addProduct ::" + result);
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20171115", product.getManuDate());
		Assert.assertEquals(1111, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals(5, product.getAmount());
		
		// delete from product where prod_name = "testProdName";
	}
	
	//@Test
	public void testDaoGetProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		System.out.println(":: testSelectOneProduct ::");
		Product product = productDao.getProduct(prodNo);
		
		System.out.println(product);
		
	}
	
	//@Test
	public void testDaoUpdateProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		Product product = new Product();
		
		product.setProdName("updateDaoProdName");
		product.setProdDetail("updateProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("updateFileName");
		product.setProdNo(prodNo);
		
		System.out.println(":: testUpdateProduct ::");
		int result = productDao.updateProduct(product);
		
		//==> console 확인
		System.out.println("updateProduct ::" + result);
		System.out.println(product);
		
	}
	
	//@Test
	public void testDaoGetProductList() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	
	 	System.out.println(":: testUpdateProduct ::");
	 	List<Product> list = productDao.getProductList(search);
	 	
	 	// 테스트!
	 	for(Product product: list) {
	 		System.out.println(product);
	 	}                               
	}
	
	//@Test
	public void testDaoGetTotalCount() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	
	 	System.out.println(":: testGetTotalCount ::");
	 	int result = productDao.getTotalCount(search);
	 	
	 	System.out.println("totalCount :: "+result);	
	}

	/////////////////////////////////////////////////////////
	
	//@Test
	public void testServiceAddProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.setProductDao(productDao);
		
		Product product = new Product();
		
		product.setProdName("ServicetestProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("testFileName");
		product.setAmount(5);
		
		System.out.println(":: testAddProduct ::");
		int result = productService.addProduct(product);
		//==> console 확인
		System.out.println("addProduct ::" + result);
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20171115", product.getManuDate());
		Assert.assertEquals(1111, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals(5, product.getAmount());
		
		// delete from product where prod_name = "testProdName";
	}
	
	//@Test
	public void testServiceGetProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.setProductDao(productDao);
		
		System.out.println(":: testSelectOneProduct ::");
		Product product = productService.getProduct(prodNo);
		
		System.out.println(product);
		
	}
	
	//@Test
	public void testServiceUpdateProduct() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.setProductDao(productDao);
		
		Product product = new Product();
		
		product.setProdName("updateServiceProdName");
		product.setProdDetail("updateProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("updateFileName");
		product.setProdNo(prodNo);
		
		System.out.println(":: testUpdateProduct ::");
		int result = productService.updateProduct(product);
		
		//==> console 확인
		System.out.println("updateProduct ::" + result);
		System.out.println(product);
		
	}
	
	//@Test
	public void testServiceGetProductList() throws Exception {
		
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.setProductDao(productDao);
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	
	 	System.out.println(":: testUpdateProduct ::");
	 	Map<String, Object> map = productService.getProductList(search);
	 	List<Product> list = (List<Product>)map.get("list");
	 	int result = (int)map.get("totalCount");
	 	
	 	// 테스트!
	 	for(Product product: list) {
	 		System.out.println(product);
	 	} 
	 	
	 	System.out.println("totalCount :: "+result);
	}
	
	/////////////////////////////////////////////////////////
	
	//@Test
	public void testSpringAddProduct() throws Exception {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[]
						{"/config/commonservice2.xml"});
		
		ProductService productService = (ProductService)context.getBean("productServiceImpl");
		
		Product product = new Product();
		
		product.setProdName("SpringtestProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("testFileName");
		product.setAmount(5);
		
		System.out.println(":: testAddProduct ::");
		int result = productService.addProduct(product);
		//==> console 확인
		System.out.println("addProduct ::" + result);
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20171115", product.getManuDate());
		Assert.assertEquals(1111, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals(5, product.getAmount());
		
		// delete from product where prod_name = "testProdName";
	}
	
	//@Test
	public void testSpringGetProduct() throws Exception {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[]
						{"/config/commonservice2.xml"});
		
		ProductService productService = (ProductService)context.getBean("productServiceImpl");
		
		System.out.println(":: testSelectOneProduct ::");
		Product product = productService.getProduct(prodNo);
		
		System.out.println(product);
		
	}
	
	//@Test
	public void testSpringUpdateProduct() throws Exception {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[]
						{"/config/commonservice2.xml"});
		
		ProductService productService = (ProductService)context.getBean("productServiceImpl");

		
		Product product = new Product();
		
		product.setProdName("updateSpringProdName");
		product.setProdDetail("updateProdDetail");
		product.setManuDate("20171115");
		product.setPrice(1111);
		product.setFileName("updateFileName");
		product.setProdNo(prodNo);
		
		System.out.println(":: testUpdateProduct ::");
		int result = productService.updateProduct(product);
		
		//==> console 확인
		System.out.println("updateProduct ::" + result);
		System.out.println(product);
		
	}
	
	//@Test
	public void testSpringGetProductList() throws Exception {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[]
						{"/config/commonservice2.xml"});
		
		ProductService productService = (ProductService)context.getBean("productServiceImpl");
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	
	 	System.out.println(":: testUpdateProduct ::");
	 	Map<String, Object> map = productService.getProductList(search);
	 	List<Product> list = (List<Product>)map.get("list");
	 	int result = (int)map.get("totalCount");
	 	
	 	// 테스트!
	 	for(Product product: list) {
	 		System.out.println(product);
	 	} 
	 	
	 	System.out.println("totalCount :: "+result);
	}
	
	
}