CREATE TABLE transaction
    (
    id int auto_increment,
    customer_id INT NOT NULL ,
    amount DECIMAL(20,2) NULL ,
	purchase_date DATETIME NULL ,
	primary key (id)
    );