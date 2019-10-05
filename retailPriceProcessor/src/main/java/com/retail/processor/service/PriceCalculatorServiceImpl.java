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

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.processor.Discount.IDiscount;
import com.retail.processor.entity.Bill;
import com.retail.processor.entity.Cart;
import com.retail.processor.entity.Customer;
import com.retail.processor.exception.IncorrectCustomerTypeException;
import com.retail.processor.factory.IDiscountFactory;
import com.retail.processor.utill.CustomerTypes;
import com.retail.processor.utill.ProductCategory;
/**
 * @author nagendra
 *
 */
@Service
public class PriceCalculatorServiceImpl implements IPriceCalculatorService {

	private static final Logger logger = LoggerFactory.getLogger(PriceCalculatorServiceImpl.class);
	
	@Autowired
	private IDiscountFactory iDiscountFactory;
	/**			
	 * This method is cable of calculating the detailed price information for the
	 * inputed cart.
	 * 
	 * @param cart         : this input variable holding the products wich selected by the customer.
	 * @param customerType : this will be holding the customer type
	 * @return price : returns the final payable amount for the inputed items.
	 */
	@Override
	public double calculatePrice(Cart cart, Customer customer) {
		logger.info("PriceCalculatorServiceImpl- calculatePrice() Started : The cartId is {} and customer id is {}"+cart.getCartId(),customer.getId());
		Bill bill = new Bill();
		bill.setGroceryIteamsBill(cart.getProducts().stream()
				.filter(item -> item.getProductCategory().equals(ProductCategory.GROCERY.toString()))
				.map(item -> item.getPrice()).reduce(0d, Double::sum));

		bill.setNonGroceryIteamsBill(cart.getProducts().stream()
				.filter(item -> item.getProductCategory().equals(ProductCategory.NON_GROCERY.toString()))
				.map(item -> item.getPrice()).reduce(0d, Double::sum));

		bill.setTotalBill(Double.sum(bill.getGroceryIteamsBill(), bill.getNonGroceryIteamsBill())); // Calculating total amount
		try {
			bill.setTotalDiscount(calculateDiscount(bill, customer));
		} catch (IncorrectCustomerTypeException e) {
			logger.error("error occurred while getting the discount object, the error is : {}",e.getMessage());
		}
		bill.setNetPayableAmount(Double.min(bill.getTotalBill(),bill.getTotalDiscount())); // reduced discount amount on total bill
		logger.info("PriceCalculatorServiceImpl- calculatePrice(): Ened : the net payable amount is {} ",bill.getNetPayableAmount());
		return bill.getNetPayableAmount();
	}

	/**
	 * This method will be calculate the final amount to be paid for the cart and
	 * setting into bill object. 
	 * step 1: if the customer is regular_customer then
	 * 		  find whether is eligible or not else other customers applied respective
	 * 		  discount on non_groceries .
	 * step 2: Applied second discount on bill (every $100 discount would be $5) on total bill.
	 * 
	 * @param cartId : unique identification of cart
	 * @return : net payable amount for the requested cart
	 * @throws IncorrectCustomerTypeException 
	 */
	private double calculateDiscount(Bill bill, Customer customer) throws IncorrectCustomerTypeException {
		logger.info("PriceCalculatorServiceImpl- calculateDiscount()-> Started");
		IDiscount persontageDiscount = iDiscountFactory.getDiscountType(customer.getType());
		double nonGroceryItemsbill = bill.getNonGroceryIteamsBill();
		double percentageDiscountAmount = 0d;
		double discountOnTotalBill = 0.0d;
		/**
		 * If the customer is regular type then checking the relationship period else
		 * getting the respective discount as configured
		 **/
		if (CustomerTypes.REGULAR_CUST.toString().equals(customer.getType())) {
			
			//Finding the relation period for the customer till day
			Period period = Period.between(customer.getFirstPurcheseDate(), LocalDate.now());
			logger.info("PriceCalculatorServiceImpl- calculateDiscount()-> Relationship period is: {} ",period.getYears());
			if (period.getYears() >= 2)
				percentageDiscountAmount = ((persontageDiscount.getDiscount() * 100) / nonGroceryItemsbill);

		} else
			percentageDiscountAmount = ((persontageDiscount.getDiscount() * 100) / nonGroceryItemsbill); // Finding the percentage amount

		discountOnTotalBill = Double.min(bill.getTotalBill(), bill.getTotalBill() / 100); // Every $100 discount is $5
		logger.info("PriceCalculatorServiceImpl- calculateDiscount()-> Ended discountonTotalBill is: {} ",discountOnTotalBill);
		return Double.sum(percentageDiscountAmount, discountOnTotalBill); // Returning the total discount amount
	}

}
