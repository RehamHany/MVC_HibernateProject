create database productDB;
use productDB;


create table product_details(
                                id int auto_increment primary key ,
                                name varchar(255) not null ,
                                expiration_date date not null,
                                manufacturer varchar(255) not null ,
                                price double not null ,
                                available boolean not null default false
);

create table product(
                        id int auto_increment primary key ,
                        name varchar(255) not null,
                        product_details_id int not null ,
                        foreign key (product_details_id) references product_details(id)
);


INSERT INTO productDB.product_details (id, name, expiration_date, manufacturer, price, available) VALUES (1,'milk', '2024-02-08', 'dina farms', 30, 1);
INSERT INTO productDB.product_details (id, name, expiration_date, manufacturer, price, available) VALUES (2,'fish', '2024-02-15', 'abu elsayed', 100, 1);
INSERT INTO productDB.product_details (id, name, expiration_date, manufacturer, price, available) VALUES (3,'cheese', '2024-06-11', 'dina farms', 50, 1);
INSERT INTO productDB.product_details (id, name, expiration_date, manufacturer, price, available) VALUES (4,'chocolate ', '2024-02-29', 'cadbury', 60, 0);

INSERT INTO productDB.product (id, name , product_details_id) VALUES (1, 'milk' , 1);
INSERT INTO productDB.product (id, name , product_details_id) VALUES (2, 'fish' , 2);
INSERT INTO productDB.product (id, name , product_details_id) VALUES (3, 'cheese' , 3);
INSERT INTO productDB.product (id, name , product_details_id) VALUES (4, 'chocolate' , 4);
