CREATE TABLE transaction
    (
    id int auto_increment,
    customer_id INT NOT NULL ,
    amount DECIMAL(20,2) NULL ,
	purchase_date DATETIME NULL ,
	primary key (id)
    );

INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(1,200,'2023-2-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(1,800,'2023-2-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(1,500,'2023-1-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(1,100,'2022-12-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(1,250,'2022-12-05');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(1,250.50,'2022-12-05');

INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,200,'2023-2-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,800,'2023-2-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,500,'2023-1-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,100,'2022-12-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,250,'2020-12-05');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,250.50,'2022-12-05');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(2,250,'2022-11-05');



INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(3,120,'2022-12-05');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(3,100,CURDATE());
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(3,100,'2023-1-04');

INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(4,100,'2023-2-24');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(4,200,'2023-12-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(4,500,'2023-1-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(4,300,'2023-11-04');

INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(5,100,'2023-2-24');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(5,400,'2023-12-04');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(5,200,'2020-11-04');

INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(6,50,'2022-12-05');
INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(7,101,'2022-12-05');