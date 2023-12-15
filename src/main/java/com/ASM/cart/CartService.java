package com.ASM.cart;

import java.util.Collection;

public interface CartService {
	void add(Integer id);
	void remove(Integer id);
	void update(Integer id, int qty);
	void clear();
	Collection<CartItem> getItems();
	int getTotalCount();
	double getTotalAmount();
}