CREATE TABLE Books (
	book_id SERIAL PRIMARY KEY,
	title VARCHAR(150),
	genre VARCHAR(150),
	price INT NOT NULL,
	stock INT
);

CREATE TABLE Authors (
	author_id SERIAL PRIMARY KEY,
	author_name VARCHAR(150)
);

CREATE TABLE Customer (
	customer_id SERIAL PRIMARY KEY,
	customer_name VARCHAR(150),
	email VARCHAR(150),
	address VARCHAR(150)
);

CREATE TABLE Orders (
	order_id SERIAL PRIMARY KEY,
	customer_id INT REFERENCES Customer(customer_id),
	order_date DATE,
	total_price INT NOT NULL
);

CREATE TABLE BooksInfo (
	book_id INT REFERENCES Books(book_id),
	author_id INT REFERENCES Authors(author_id),
	PRIMARY KEY (book_id, author_id)
);

CREATE TABLE OrdersInfo (
	order_id INT REFERENCES Orders(order_id),
	book_id INT REFERENCES Books(book_id),
	ordered_books INT,
	PRIMARY KEY (order_id, book_id)
);