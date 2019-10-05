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
package com.retail.processor.Discount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author nagendra
 *
 */
@Component
public class RegularCustomerDiscount implements IDiscount {
	
	private static final Logger logger = LoggerFactory.getLogger(RegularCustomerDiscount.class);

	@Value("${regular.discount}")
	private int discount;
	
	@Override
	public int getDiscount() {
		logger.info("RegularCustomerDiscount- getDiscount() is get called.");
		return discount;
	}

}
