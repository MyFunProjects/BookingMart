INSERT INTO 
	TBL_PRODUCT (product_name, product_price,avail_quantity) 
VALUES
  	('Rice', 500, 50),
  	('Wheat', 400 , 40),
	('Oil', 700, 30),
	('Ragi', 400 , 10),
	('Brown rice',800, 20),
	('Cereals', 1000, 100),
	('Dry fruits', 1300, 25);


INSERT INTO 
	TBL_CUSTOMER (first_name, contact_number) 
VALUES
  	('Michael', 9030293040),
	('Sandra', 9484658243);

INSERT INTO 	
	TBL_PRODUCT_BOOKING (customer_id, product_details, billing_comments, billing_date, total_price)
VALUES

	(1 ,'[{"productId": 1,"quantity" : 2},{"productId": 2,"quantity" : 2}]','NIL',21/2/2019,180),
	(1 ,'[{"productId": 2,"quantity" : 3},{"productId": 4,"quantity" : 2}]','2nd purchase',3/10/2020,1000);