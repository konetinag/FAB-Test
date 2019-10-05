/*************************************************************************************************
Copyright © 2019 by XXXXXXXX
All rights reserved. No part of this publication may be reproduced, distributed, 
or transmitted in any form or by any means, including photocopying, recording, 
or other electronic or mechanical methods, without the prior written permission 
of the publisher,except in the case of brief quotations embodied in critical reviews 
and certain other noncommercial uses permitted by copyright law.
***************************************************************************************************/

/**
 * 
 */
package com.retail.processor.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import com.retail.processor.entity.Cart;
import com.retail.processor.entity.Customer;
import com.retail.processor.entity.Product;

/**
 * @author nagendra
 * 
 */
@PropertySource({ "classpath:application.properties" })
@SpringBootTest
class PriceCalculatorServiceImplTest {

	@Autowired
	private IPriceCalculatorService iPriceCalculatorService;

	@Autowired
	private Cart cart;

	@BeforeEach
	public void setUp() {

		List<Product> products = new ArrayList<Product>();
		getProducts(products);
		cart.setCartId(1L);
		cart.setProducts(products);

	}

	private void getProducts(List<Product> products) {

		Product product1 = new Product();
		product1.setProductId(1L);
		product1.setPrice(20.0d);
		product1.setProductCategory("GROCERY");
		products.add(product1);

		Product product2 = new Product();
		product2.setProductId(2L);
		product2.setPrice(20.0d);
		product2.setProductCategory("NON_GROCERY");
		products.add(product2);

		Product product3 = new Product();
		product3.setProductId(3L);
		product3.setPrice(50.0d);
		product3.setProductCategory("NON_GROCERY");
		products.add(product3);

		Product product4 = new Product();
		product4.setProductId(4L);
		product4.setPrice(40.0d);
		product4.setProductCategory("NON_GROCERY");
		products.add(product4);
	}

	@Test
	void given_regularCustomer_when_eligiableFor_discount_then_CalculatePrice() {

		Customer regularCustomer = new Customer();
		regularCustomer.setFirstPurcheseDate(LocalDate.now().minusYears(3L));
		regularCustomer.setId(1L);
		regularCustomer.setType("REGULAR_CUST");

		double netPayableAmount = iPriceCalculatorService.calculatePrice(cart, regularCustomer);
		assertNotNull(netPayableAmount);
	}

	@Test
	void given_regularCustomer_when_nonEligiableFor_discount_then_CalculatePrice() {

		Customer regularCustomer = new Customer();
		regularCustomer.setFirstPurcheseDate(LocalDate.now().minusYears(1L));
		regularCustomer.setId(1L);
		regularCustomer.setType("REGULAR_CUST");

		double netPayableAmount = iPriceCalculatorService.calculatePrice(cart, regularCustomer);
		System.out.println("The payment amount is : " + netPayableAmount);
		assertNotNull(netPayableAmount);
	}

	@Test
	void given_employeeCustomer_when_eligiableFor_discount_then_CalculatePrice() {

		Customer regularCustomer = new Customer();
		regularCustomer.setFirstPurcheseDate(LocalDate.now().minusYears(1L));
		regularCustomer.setId(1L);
		regularCustomer.setType("EMPLOYEE_CUST");

		double netPayableAmount = iPriceCalculatorService.calculatePrice(cart, regularCustomer);
		assertNotNull(netPayableAmount);
	}

	@Test
	void given_affiliateCustomer_when_eligiableFor_discount_then_CalculatePrice() {

		Customer regularCustomer = new Customer();
		regularCustomer.setFirstPurcheseDate(LocalDate.now().minusYears(1L));
		regularCustomer.setId(1L);
		regularCustomer.setType("AFFILIATE_CUST");

		double netPayableAmount = iPriceCalculatorService.calculatePrice(cart, regularCustomer);
		assertNotNull(netPayableAmount);
	}

	@Test
	void given_unknownCustomer_when_eligiableFor_discount_then_CalculatePrice() {

		Customer regularCustomer = new Customer();
		regularCustomer.setFirstPurcheseDate(LocalDate.now().minusYears(1L));
		regularCustomer.setId(1L);
		regularCustomer.setType("xxxxxx");

		double netPayableAmount = iPriceCalculatorService.calculatePrice(cart, regularCustomer);
		assertNotNull(netPayableAmount);
	}

}
