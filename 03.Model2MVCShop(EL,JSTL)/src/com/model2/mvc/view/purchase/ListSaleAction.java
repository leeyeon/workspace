package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListSaleAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// search logic
		Search search = new Search();
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String priceOrderbyCode = null;
		if(request.getParameter("priceOrderbyCode") != null){
			search.setSearchOrderbyPrice(request.getParameter("priceOrderbyCode"));
		}
		
		// web.xml  meta-data 로 부터 상수 추출 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		// business logic
		PurchaseService service = new PurchaseServiceImpl();
		Map<String, Object> map = service.getSaleList(search);
		
		Page resultPage	= 
						new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), 
						pageUnit, pageSize);
		//System.out.println("ListProductAction ::"+resultPage);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		request.setAttribute("menu", request.getParameter("menu"));
		
		return "forward:/purchase/listSale.jsp";
	}

}
