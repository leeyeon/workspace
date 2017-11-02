package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		//System.out.println(request.getParameter("buyerId"));
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranNo(tranNo);
		
		UserVO buyer = new UserVO();
		buyer.setUserId(request.getParameter("buyerId"));
		purchaseVO.setBuyer(buyer);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		
		System.out.println(purchaseVO);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updatePurcahse(purchaseVO);
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}

}
