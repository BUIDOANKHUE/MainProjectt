package com.ASM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ASM.dao.OrderDAO;
import com.ASM.entity.Order;
import com.ASM.entity.Product;

@Service
public class OrderService {
    @Autowired
    OrderDAO dao;

    @Transactional
    public void create(Order order) {
        // Log thông tin trước khi lưu
        System.out.println("Order before saving: " + order.toString());

        dao.save(order);

        // Log thông tin sau khi lưu
        System.out.println("Order after saving: " + order.toString());
    }

    public Order findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    public Page<Order> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 9);
        return dao.findAll(pageable);
    }

 
   

	
}
