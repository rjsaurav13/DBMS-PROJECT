create database bank_144;
use bank_144;
show tables;
create table login(
name varchar(50),
usn varchar(10)
);
insert into login values('satyam','4nm19is143');
insert into login values('saurav','4nm19is144');
select * from login;
CREATE TABLE customer
   (
       customer_id int,
       fname VARCHAR(50),
       lname VARCHAR(50),
       city VARCHAR(50),
       occupation VARCHAR(50),
	   dob DATE,
       mobileno bigint,
      
       PRIMARY KEY(customer_id)   
   );
INSERT INTO customer VALUES(00001,'Ramesh','Chandra','Delhi','Service','1976-12-06',9543198345);
INSERT INTO customer VALUES(00002,'Avinash','Sunder','Delhi','Service','1974-10-16',9876532109);
INSERT INTO customer VALUES(00003,'Rahul','Dugar','Delhi','Student','1981-09-26',9765178901);
INSERT INTO customer VALUES(00004,'Parul','Gandhi','Delhi','Housewife','1976-11-03',9876532109);
INSERT INTO customer VALUES(00005,'Naveen','Chandra','Mumbai','Service','1976-09-19',8976523190);
INSERT INTO customer VALUES(00006,'Chitresh','Barwe','Mumbai','Student','1992-11-06',7651298321);
INSERT INTO customer VALUES(00007,'Amit','Kumar','Mumbai','Student','1981-09-06',9875189761);
INSERT INTO customer VALUES(00008,'Nisha','Damle','Mumbai','Service','1975-12-03',7954198761);
INSERT INTO customer VALUES(00009,'Abhishek','Dutta','Kolkata','Service','1973-05-22',9856198761);
INSERT INTO customer  VALUES(00010,'Shankar','Nair','Chennai','Service','1976-07-12',876548907);
select * from customer;

/*---branch-----*/
CREATE TABLE branch
   (
    branch_id int,
    branch_name VARCHAR(50),
    branch_city VARCHAR(50),
    PRIMARY KEY(branch_id) 
   );
INSERT INTO branch VALUES(00001,'Asaf ali road','Delhi');
INSERT INTO branch VALUES(00002,'New delhi main branch','Delhi');
INSERT INTO branch VALUES(00003,'Delhi cantt','Delhi');
INSERT INTO branch VALUES(00004,'Jasola','Delhi');
INSERT INTO branch VALUES(00005,'Mahim','Mumbai');
INSERT INTO branch VALUES(00006,'Vile parle','Mumbai');
INSERT INTO branch VALUES(00007,'Mandvi','Mumbai');
INSERT INTO branch VALUES(00008,'Jadavpur','Kolkata');
INSERT INTO branch VALUES(00009,'Kodambakkam','Chennai');
 select * from branch;  
 
 create table account(
   account_number int,
   customer_id int,
   branch_id int,
   opening_amount int,
   opening_date date,
   account_type varchar(50),
   account_status varchar(50),
   primary key(account_number),
   foreign key(customer_id) references customer(customer_id),
   foreign key(branch_id) references branch(branch_id) 
    );
INSERT INTO account VALUES(00001,00001,00001,1000,'2012-12-15','Saving','Active');
INSERT INTO account VALUES(00002, 00002 , 00001 ,1000,'2012-06-12','Saving','Active');
INSERT INTO account VALUES(00003, 00003 , 00002 ,1000,'2012-05-17','Saving','Active');
INSERT INTO account VALUES(00004, 00002 , 00005 ,1000,'2013-01-27','Saving','Active');
INSERT INTO account VALUES(00005, 00006 , 00006 ,1000,'2012-12-17','Saving','Active');
INSERT INTO account VALUES(00006, 00007 , 00007 ,1000,'2010-08-12','Saving','Suspended');
INSERT INTO account VALUES(00007, 00007 , 00001 ,1000,'2012-10-02','Saving','Active');
INSERT INTO account VALUES(00008, 00001 , 00003 ,1000,'2009-11-09','Saving','Terminated');
INSERT INTO account VALUES(00009, 00003 , 00007 ,1000,'2008-11-30','Saving','Terminated');
INSERT INTO account VALUES(00010, 00004 , 00002 ,1000,'2013-03-01','Saving','Active');
INSERT INTO account VALUES(12345,117,1123,12000,'2012-12-15','NRI accounts','Active');
INSERT INTO account VALUES(12346,117,1123,12000,'2012-12-15','saving','suspended');
INSERT INTO account VALUES(12347,123,2245,12150,'2019-12-25','Salary account','Active');
INSERT INTO account VALUES(12348,123,2245,12150,'2020-12-25','NRI account','Terminated');
INSERT INTO account VALUES(12349,126,4490,12500,'2021-12-25','Saving account','Active');
INSERT INTO account VALUES(12350,133,1123,19000,'1987-12-25','Salary account','Suspended');
INSERT INTO account VALUES(12351,143,3367,19050,'2001-03-15','Saving-account','Active');
INSERT INTO account VALUES(12352,144,4489,20000,'2000-03-13','Salary-account','Active');

    SELECT * FROM ACCOUNT;
   
create table transaction_detail(
   transaction_number int,
   account_number int,
   date_of_transaction date,
   mode varchar(20),
   type varchar(20),
   amount int,
   primary key(transaction_number),
   foreign key(account_number) references account(account_number)
    );
INSERT INTO transaction_detail VALUES(00001,00001,'2013-01-01','Cheque','Deposit',2000);
INSERT INTO transaction_detail VALUES(00002, 00001 ,'2013-02-01','Cash','Withdrawal',1000);
INSERT INTO transaction_detail VALUES(00003, 00002 ,'2013-01-01','Cash','Deposit',2000);
INSERT INTO transaction_detail VALUES(00004, 00002 ,'2013-02-01','Cash','Deposit',3000);
INSERT INTO transaction_detail VALUES(00005, 00007 ,'2013-01-11','Cash','Deposit',7000);
INSERT INTO transaction_detail VALUES(00006, 00007 ,'2013-01-13','Cash','Deposit',9000);
INSERT INTO transaction_detail VALUES(00007, 00001 ,'2013-03-13','Cash','Deposit',4000);
INSERT INTO transaction_detail VALUES(00008, 00001 ,'2013-03-14','Cheque','Deposit',3000);
INSERT INTO transaction_detail VALUES(00009, 00001 ,'2013-03-21','Cash','Withdrawal',9000);
INSERT INTO transaction_detail VALUES(00010, 00001 ,'2013-03-22','Cash','Withdrawal',2000);
INSERT INTO transaction_detail VALUES(00011, 00002 ,'2013-03-25','Cash','Withdrawal',7000);
INSERT INTO transaction_detail VALUES(00012, 00007 ,'2013-03-26','Cash','Withdrawal',2000);
select * from transaction_detail;



  create table loan(
   customer_id int,
   branch_id int,
   amount int,
   primary key(customer_id,branch_id),
	FOREIGN KEY(customer_id) REFERENCES  customer(customer_id),
	FOREIGN KEY(branch_id) REFERENCES  branch(branch_id)
   );
   
INSERT INTO loan VALUES(00001,00001,100000);
INSERT INTO loan VALUES(00002,00002,200000);
INSERT INTO loan VALUES(00009,00008,400000);
INSERT INTO loan VALUES(00010,00009,500000);
INSERT INTO loan VALUES(00001,00003,600000);
INSERT INTO loan VALUES(00002,00001,600000);

DELIMITER $$
CREATE PROCEDURE QUERY1()
BEGIN
	SELECT distinct (CUSTOMER_ID), fname, lname,dob
	FROM customer ,branch
	ORDER BY EXTRACT(year FROM dob), fname ASC;
    END$$
DELIMITER ;
DELIMITER $$
	CREATE PROCEDURE QUERY2()
	BEGIN
	SELECT account.customer_id, customer.fname, account.account_number
	FROM account, customer
	WHERE account.customer_id = customer.customer_id
	AND day(opening_date) > 15;
    END$$
DELIMITER ;

DELIMITER $$
	CREATE PROCEDURE QUERY3()
	BEGIN
	SELECT DISTINCT (customer.fname), customer.city, account.account_number,occupation
	FROM account, customer
	WHERE account.customer_id = customer.customer_id
	AND NOT(occupation='business' or occupation='service' or occupation='student');
    END$$
DELIMITER ;	

DELIMITER $$
	CREATE PROCEDURE QUERY4()
	BEGIN
	SELECT branch_city, count(*)
	AS Count_Branch 
	FROM branch
	Group By branch_city;
	END$$
DELIMITER ;

DELIMITER $$
	CREATE PROCEDURE QUERY5()
	BEGIN
	SELECT customer.customer_id, customer.fname, branch.branch_id, loan.amount
	FROM ((loan
	INNER JOIN customer ON loan.customer_id=customer.customer_id)
	INNER JOIN branch ON loan.branch_id=branch.branch_id);
	END$$
DELIMITER ;	
	
delimiter $$
create trigger check_age
before insert on customer
for each row
begin
if (year(curdate())-year(NEW.dob)) < 18 then
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'ERROR: Age must Be Atleast 18 Years !' ;
end if;
end;
$$

delimiter $$
create trigger check_branch
before insert on branch
for each row
begin
     declare i int;
	 begin
		  set i = (select count(*) from branch where branch_city=NEW.branch_city group by branch_city );
		  if (i)=4 then
          SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT = 'ERROR: One City can have atmost 4 branches !' ;
          end if;
    end;
end;
$$
show procedure status;
    

call QUERY6();
show tables;