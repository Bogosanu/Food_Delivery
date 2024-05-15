
create table customer
(
    phoneNumber varchar(50) not null,
    firstName   varchar(50) not null,
    lastName    varchar(50) not null,
    address     varchar(50) null,
    adult       tinyint(1)  null,
    primary key (firstName, lastName)
);

create index customer_firstName_index
    on customer (firstName);

create index customer_lastName_index
    on customer (lastName);

create table driver
(
    phoneNumber  varchar(50) not null,
    firstName    varchar(50) not null,
    lastName     varchar(50) not null,
    licensePlate varchar(50) null,
    carModel     varchar(50) null,
    primary key (firstName, lastName)
);

create index driver_firstName_index
    on driver (firstName);

create index driver_lastName_index
    on driver (lastName);

create table provider
(
    name        varchar(50) not null
        primary key,
    phoneNumber varchar(50) null,
    address     varchar(50) null
);

create table `order`
(
    number       int         not null
        primary key,
    cstFirstName varchar(50) null,
    cstLastName  varchar(50) null,
    drvFirstName varchar(50) null,
    drvLastName  varchar(50) null,
    providerName varchar(50) null,
    constraint order_ibfk_1
        foreign key (cstFirstName) references customer (firstName),
    constraint order_ibfk_2
        foreign key (cstLastName) references customer (lastName),
    constraint order_ibfk_3
        foreign key (drvFirstName) references driver (firstName),
    constraint order_ibfk_4
        foreign key (drvLastName) references driver (lastName),
    constraint order_ibfk_5
        foreign key (providerName) references provider (name)
);

create index cstFirstName
    on `order` (cstFirstName);

create index cstLastName
    on `order` (cstLastName);

create index drvFirstName
    on `order` (drvFirstName);

create index drvLastName
    on `order` (drvLastName);

create index providerName
    on `order` (providerName);

create table product
(
    name         varchar(50) not null,
    adultsOnly   tinyint(1)  null,
    price        float       null,
    weight       int         null,
    providerName varchar(50) not null,
    primary key (name, providerName),
    constraint product___fk
        foreign key (providerName) references provider (name)
);

create table orderproduct
(
    productName varchar(50) not null,
    orderNumber int         not null,
    primary key (productName, orderNumber),
    constraint orderproduct_order_number_fk
        foreign key (orderNumber) references `order` (number),
    constraint orderproduct_product__fk
        foreign key (productName) references product (name)
);

