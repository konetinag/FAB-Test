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

import com.retail.processor.entity.Cart;
import com.retail.processor.entity.Customer;

/**
 * @author nagendra
 *
 */
public interface IPriceCalculatorService {
	
	/**
	 * This method is calculated the price details of cart for the inputed customer type.
	 * 
	 * @param cart : User selected cart, which has items.
	 * @param customer : this holds customer information.
	 * @return : Bill details of the selected cart items.
	 */
	public double calculatePrice(Cart cart, Customer customer);

	

}
