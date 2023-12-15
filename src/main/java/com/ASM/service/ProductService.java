package com.ASM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ASM.dao.ProductDAO;
import com.ASM.entity.Product;

@Service
public class ProductService {
	@Autowired
	ProductDAO dao;

	public List<Product> findAll() {
		List<Product> list = dao.findAll();
		return list;
	}
	
	

	public Product findById(Integer id) {
		Product product = dao.findById(id).get();
		return product;
	}

	public List<Product> findByCategoryId(Integer id) {
		List<Product> list = dao.findByCategoryId(id);
		return list;
	}
	
	public List<Product> findBynxb(String nxb) {
		List<Product> list = dao.findBynxb(nxb);
		return list;
	}
	
	public List<Product> findByprice100000() {
		List<Product> list = dao.findByPrice100000();
		return list;
	}
	
	public List<Product> findByprice200000() {
		List<Product> list = dao.findByPrice200000();
		return list;
	}
	public List<Product> findByprice300000() {
		List<Product> list = dao.findByPrice300000();
		return list;
	}
	public List<Product> findByprice400000() {
		List<Product> list = dao.findByPrice400000();
		return list;
	}

	public Product create(Product product) {
		return dao.save(product);
	}

	public boolean existedById(Integer id) {
		return dao.existsById(id);
	}

	public Product update(Product product) {
		return dao.save(product);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public List<Product> findAllByOrderByPriceAsc() {
		List<Product> list = dao.findAllByOrderByPriceAsc();
		return list;
	}
	
	public List<Product> findAllByOrderByPriceDesc() {
		List<Product> list = dao.findAllByOrderByPriceDesc();
		return list;
	}
	public List<Product> findByKeywords(String keywords) {
		List<Product> page = dao.findByKeywords(keywords);
		return page;
	}
	
	public Page<Product> findAll(Integer pageNo){
		Pageable pageable = PageRequest.of(pageNo-1, 9);
		return this.dao.findAll(pageable);
	}
	
	public Page<Product> findByKeyWordsAndPages(String keywords, Integer pageNo){
		List<Product> list = dao.findByKeywords(keywords);
		
		Pageable pageable = PageRequest.of(pageNo-1, 9);
		
		Integer start = (int) pageable.getOffset();
		
		Integer end = (int) ((pageable.getOffset()+ pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize()) ;
		
		list = list.subList(start, end);
		return new PageImpl<Product>(list, pageable, dao.findByKeywords(keywords).size());
	}
}
