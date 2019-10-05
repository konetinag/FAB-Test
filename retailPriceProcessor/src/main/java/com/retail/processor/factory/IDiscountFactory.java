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

import com.retail.processor.Discount.IDiscount;
import com.retail.processor.exception.IncorrectCustomerTypeException;

/**
 * @author nagendra
 *
 */
public interface IDiscountFactory {

	/**
	 * This method is capable of generating discount type object to the client by receiving the type as input.
	 * @param customerType  : specify the customer type
	 * @return IDiscount : return the desired discount object.
	 * @throws IncorrectCustomerTypeException : throws when receive the invalid customer type.
	 */
	public IDiscount getDiscountType(String customerType) throws IncorrectCustomerTypeException;
}
