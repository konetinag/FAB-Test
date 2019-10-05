Entities:  
   1) Discount : which is holding discount information for various types of customers.
   2) customer : which is holding the customer information including type of customer.
   3) product  : it is an iteam, which got purchaged by customer.
   4) Cart     : this cart holding the products, which the customer willing to buy.
   5) Bill     : this hold the information of price details for each cart. 
Processors : 
	1) IPriceCalculator : this processor is calculate the final price to be paid by customer for his selected cart.
Factory Class:
    1) IDiscountFactory   : this factory is genarate the discount object by type.
Service : 
   1) PriceCalculatorServiceImpl : Its actually calculate the bill details for the cart.
     calculateDiscount() return the netPayableAmount for the input.
   2) DiscountFactoryImpl : It is the factory class to create object for the requested type.
   
Run :

  1) Open commond prompt and run the below command : java -jar ../lombok-1.18.4.jar
		Or Add same command in java arguments 
  2) Run mvn package
 