package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		//PurchaseService service = new PurchaseServiceImpl();
		//Purchase purchase = service.getPurchase2(prodNo);
		//purchase.setBuyer(((User)session.getAttribute("user")));
		
		//request.setAttribute("purchase", purchase);
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);

		request.setAttribute("product", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
