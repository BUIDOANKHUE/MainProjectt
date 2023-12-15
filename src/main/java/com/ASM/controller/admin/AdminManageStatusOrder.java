package com.ASM.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ASM.cart.CartService;
import com.ASM.dao.OrderDAO;
import com.ASM.dao.OrderDetailDAO;
import com.ASM.entity.Category;
import com.ASM.entity.Order;
import com.ASM.entity.OrderDetail;
import com.ASM.entity.Product;
import com.ASM.service.OrderDetailService;
import com.ASM.service.OrderService;

@Controller
public class AdminManageStatusOrder {
	 @Autowired
	    OrderDAO orderDao;

	    @Autowired
	    OrderService orderservice;
	    
	  @Autowired
	  CartService cart;
	 
	    @GetMapping("/bill")
	    public String billpaid(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
	        int currentPage = Math.max(1, page.orElse(1));
	        int pageSize = 10; 
	        
	        Page<Order> orderPage;

	        if (phoneNumber != null && !phoneNumber.isEmpty()) {
	        	Sort sort = Sort.by(Sort.Direction.ASC, "status");
	            // Nếu có số điện thoại được nhập, thực hiện tìm kiếm theo số điện thoại
	            orderPage = orderDao.findByPhonenumberContaining(phoneNumber, PageRequest.of(currentPage - 1, pageSize));
	        } else {
	            // Nếu không có số điện thoại được nhập, hiển thị tất cả đơn hàng
	        	orderPage = orderDao.findAll(PageRequest.of(currentPage - 1, pageSize).withSort(Sort.by(Sort.Direction.ASC, "status")));
	        }

	        model.addAttribute("orderPage", orderPage);

	        int totalPages = orderPage.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                    .boxed()
	                    .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
	        

	        return "/admin/product/order";
	    }
	    @PostMapping("/bill/{id}")
	    public String updateStatus(
	            @PathVariable("id") Long id,
	            @RequestParam("stt") Integer status
	    ) {
	        System.out.println(" Updating status for order with id: " + id);

	        Optional<Order> optionalOrder = orderDao.findById(id);

	        optionalOrder.ifPresent(order -> {
	            order.setStatus(status);
	            orderDao.save(order);
	            System.out.println(" Order status updated successfully: " + order);
	        });

	        return "redirect:/bill";
	    }
	    @RequestMapping("/bill/detail/{id}")
	    public String detail(Model model, @PathVariable("id") Long id) {
	        Order order = orderservice.findById(id);

	    
	        double totalAmount = order.getOrderDetails().stream()
	                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
	                .sum();

	        model.addAttribute("order", order);
	        model.addAttribute("totalAmount", totalAmount);

	        return "/admin/product/orderdetails";
	    }
}
