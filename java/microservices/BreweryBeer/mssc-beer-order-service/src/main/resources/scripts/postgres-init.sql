DROP DATABASE IF EXISTS beerorderservice;
DROP USER IF EXISTS beer_order_user;
CREATE USER beer PASSWORD 'beer_order_password';
CREATE DATABASE beerorderservice;
GRANT ALL PRIVILEGES ON DATABASE beerorderservice TO beer_order_user;
