package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		//System.out.println(request.getParameter("buyerId"));
		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
		
		User buyer = new User();
		buyer.setUserId(request.getParameter("buyerId"));
		purchase.setBuyer(buyer);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		
		//System.out.println(purchase);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updatePurcahse(purchase);
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}

}
