CREATE KEYSPACE product WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE product.product (
   provider_id varchar,
   provider_name varchar,
   id varchar,
   name varchar,
   value double,
   timestamp timestamp,
   reference varchar,
   info varchar,
   PRIMARY KEY (provider_id, id)
);

CREATE TABLE product.image (
   provider_id varchar,
   product_id varchar,
   id varchar,
   path varchar,
   timestamp timestamp,
   PRIMARY KEY (provider_id, product_id, id)
);

