DROP DATABASE IF EXISTS beerinventoryservice;
DROP USER IF EXISTS beer_inventory_user;
CREATE USER beer PASSWORD 'beer_inventory_password';
CREATE DATABASE beerinventoryservice;
GRANT ALL PRIVILEGES ON DATABASE beerinventoryservice TO beer_inventory_user;
