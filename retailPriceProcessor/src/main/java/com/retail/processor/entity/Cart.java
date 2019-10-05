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
package com.retail.processor.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author nagendra
 *
 */
@Data
@RequiredArgsConstructor
@Component
public class Cart {

	private Long cartId;
	private List<Product> products;

	/**
	 * @param cartId   : identification of cart
	 * @param products : list of products got selected by customer
	 *//*
		 * public Cart(int cartId, List<Product> products) { super(); this.cartId =
		 * cartId; this.products = products; }
		 */

}
