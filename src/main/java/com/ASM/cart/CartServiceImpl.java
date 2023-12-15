package com.ASM.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.ASM.entity.Product;
import com.ASM.service.ProductService;

@SessionScope
@Service("cart")
public class CartServiceImpl implements CartService{
	@Autowired
	ProductService productService;
	
	Map<Integer, CartItem> map = new HashMap<>();
	
	@Override
	public void add(Integer id) {
		CartItem item = map.get(id);
		if(item != null) {
			item.setQuantity(item.getQuantity() + 1);
		} else {
			Product product = productService.findById(id);
			item = new CartItem(product);
			map.put(item.getId(), item);
		}
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public void update(Integer id, int qty) {
		CartItem item = map.get(id);
		item.setQuantity(qty);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<CartItem> getItems() {
		return map.values();
	}
	
	public int getTotalCount() {
		return this.getItems().stream().mapToInt(item -> item.quantity).sum();
	}
	
	public double getTotalAmount() {
		return this.getItems().stream().mapToDouble(item -> item.getAmount()).sum();
	}
}