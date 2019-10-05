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

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author nagendra
 *
 */
@Data
@Component
public class Bill {

	private int billId;
	private double totalBill;
	private double groceryIteamsBill;
	private double nonGroceryIteamsBill;
	private double netPayableAmount;
	private double totalDiscount;
}
