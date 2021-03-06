package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode");
		
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setTranCode(tranCode);
		
		System.out.println(purchaseVO.getTranNo());
		System.out.println(purchaseVO.getTranCode());
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		
		return "forward:/listPurchase.do";
	}

}
