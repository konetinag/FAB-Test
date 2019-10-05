/*************************************************************************************************
Copyright © 2019 by XXXXXXXX
All rights reserved. No part of this publication may be reproduced, distributed, 
or transmitted in any form or by any means, including photocopying, recording, 
or other electronic or mechanical methods, without the prior written permission 
of the publisher,except in the case of brief quotations embodied in critical reviews 
and certain other noncommercial uses permitted by copyright law.
***************************************************************************************************/

package com.retail.processor.factory;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.processor.Discount.IDiscount;
import com.retail.processor.exception.IncorrectCustomerTypeException;

@RunWith(SpringRunner.class)
@TestPropertySource
class DiscountFactoryImplTest {

	@Autowired
	private IDiscountFactory idiscountFactory;

	@BeforeEach
	public void setUp() {
	}

	@Test
	void given_regularCustomer_when_right_type_then_GetDiscountObject() throws IncorrectCustomerTypeException {
		IDiscount idiscount = idiscountFactory.getDiscountType("REGULAR_CUST");
		assertNotNull(idiscount);
	}

	@Test
	void given_affiliateCustomer_when_right_type_then_GetDiscountObject() throws IncorrectCustomerTypeException {
		IDiscount idiscount = idiscountFactory.getDiscountType("AFFILIATE_CUST");
		assertNotNull(idiscount);
	}

	@Test
	void given_employeeCustomer_when_right_type_then_GetDiscountObject() throws IncorrectCustomerTypeException {
		IDiscount idiscount = idiscountFactory.getDiscountType("EMPLOYEE_CUST");
		assertNotNull(idiscount);
	}

	@Test()
	void given_customer_when_wrong_type_then_GetDiscountObject() {
		Assertions.assertThrows(IncorrectCustomerTypeException.class,()->{	
				idiscountFactory.getDiscountType("XXXXXXXXX");
		});
	
	}

}
