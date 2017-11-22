package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.sun.istack.internal.Nullable;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addProductView.do")
	public String addProductView() {
		
		return "forward:/product/addProductView.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product) throws Exception {
		
		product.setManuDate(product.getManuDate().replaceAll("-", "").trim());
		
		productService.addProduct(product);
		
		return "forward:/product/addProductView.jsp";
		
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") int prodNo,
							@RequestParam("menu") String menu,
							@CookieValue(value="history", defaultValue="null") String history,
							HttpServletResponse response,
							Model model) throws Exception  {
		
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		if("manage".equals(menu)) {
			return "redirect:/updateProductView.do?prodNo="+prodNo+"&menu="+menu;
		} else if("search".equals(menu)) {
			
			Cookie cookie = null;
			
			if(history != null) {
				cookie = new Cookie("history", history+","+prodNo);
			} else {
				cookie = new Cookie("history", prodNo+"");
			}
			
			response.addCookie(cookie);
			
			return "forward:/product/getProduct.jsp?menu="+menu;
		}
		
		return "forward:/product/getProduct.jsp?menu="+menu;
	}
	
	@RequestMapping("/updateProductView.do") 
	public String updateProductView(@RequestParam("prodNo") int prodNo,
									@RequestParam("menu") String menu,
									Model model) throws Exception  {
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		model.addAttribute("menu", menu);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@RequestParam("prodNo") int prodNo,
								@ModelAttribute("product") Product product,
								Model model) throws Exception  {
		
		String menu = "ok";
		
		product.setManuDate(product.getManuDate().replaceAll("-", "").trim());
		
		productService.updateProduct(product);
		
		model.addAttribute("menu", menu);
		
		return "forward:/getProduct.do?prodNo="+prodNo;
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct(@RequestParam("menu") String menu,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							@RequestParam(value="searchCondition", required=false) String searchCondition,
							@RequestParam(value="searchKeyword", required=false) String searchKeyword,
							@RequestParam(value="priceOrderbyCode", required=false) String priceOrderbyCode,
							@ModelAttribute("search") @Nullable Search search,
							Model model) throws Exception  {
		
		search.setPageSize(pageSize);
		search.setCurrentPage(currentPage);
		search.setSearchCondition(searchCondition);
		search.setSearchKeyword(searchKeyword);
		search.setSearchOrderbyPrice(priceOrderbyCode);
		
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), 
				pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/historyProduct.do")
	public String historyProduct() {
		
		return "forward:/history.jsp";
	}

}
