package com.model2.mvc.service.cart;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;

public interface CartService {
	
	public boolean addCart(Cart cart) throws Exception;
	
	public Cart getCart(int cartNo) throws Exception;
	
	public List<Cart> getCartList(Search search, String userId) throws Exception;
	
	// ��ٱ��� ���!
	public int updateCartStatus(int cartNo) throws Exception;
}
