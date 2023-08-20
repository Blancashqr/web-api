create table cart (
    id int NOT NULL,
    PRIMARY KEY (id)
    );

create table card (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    number varchar(255) NOT NULL,
    cvc int NOT NULL,
    expiry_date varchar(255) NOT NULL,
    PRIMARY KEY (id)
    );

create table user (
    id int NOT NULL,
    email varchar(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (card_id) REFERENCES card(id),
    FOREIGN KEY (wishlist_id) REFERENCES wishlist(id)
    );

create table product (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    price double NOT NULL,
    PRIMARY KEY(id)
    );

create table cart_product (
    cart_id int NOT NULL,
    product_id int NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    PRIMARY KEY (cart_id, product_id)
);

create table purchase (
    id int NOT NULL,
    total_value double NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

create table purchase_product (
    purchase_id int NOT NULL,
    product_id int NOT NULL,
    FOREIGN KEY (purchase_id) REFERENCES purchase(id)
    FOREIGN KEY (product_id) REFERENCES product(id),
    PRIMARY KEY (purchase_id, product_id)
);

create table wishlist (
    id int NOT NULL,
    PRIMARY KEY (id)
);

create table wishlist_product (
    wishlist_id int NOT NULL,
    product_id int NOT NULL,
    FOREIGN KEY (wishlist_id) REFERENCES wishlist(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    PRIMARY KEY (wishlist_id, product_id)
);

