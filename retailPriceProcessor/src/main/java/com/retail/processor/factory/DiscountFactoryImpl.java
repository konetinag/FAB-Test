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
package com.retail.processor.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retail.processor.Discount.AffiliateDiscount;
import com.retail.processor.Discount.EmployeeDiscount;
import com.retail.processor.Discount.IDiscount;
import com.retail.processor.Discount.RegularCustomerDiscount;
import com.retail.processor.exception.IncorrectCustomerTypeException;

/**
 * @author nagendra
 *
 */
@Component
public class DiscountFactoryImpl implements IDiscountFactory {

	private static final Logger logger = LoggerFactory.getLogger(DiscountFactoryImpl.class);

	@Autowired
	private EmployeeDiscount employeeDiscount;
	
	@Autowired
	private AffiliateDiscount affiliateDiscount;
	
	@Autowired
	private RegularCustomerDiscount regularCustomerDiscount;
	
	@Override
	public IDiscount getDiscountType(String customerType) throws IncorrectCustomerTypeException {
		logger.info("DiscountFactoryImpl of getDiscountType() : the customer Type is :"+customerType); 
		IDiscount discount;

		switch (customerType) {

		case "EMPLOYEE_CUST":
			discount = employeeDiscount;
			break;
		case "AFFILIATE_CUST":
			discount = affiliateDiscount;
			break;
		case "REGULAR_CUST":
			discount = regularCustomerDiscount;
			break;
		default:
			throw new IncorrectCustomerTypeException("Got the incorrect customer type");
		}
		logger.info("DiscountFactoryImpl of getDiscountType() : the Discount Type is :"+discount.getClass()); 
		return discount;
	}

}
