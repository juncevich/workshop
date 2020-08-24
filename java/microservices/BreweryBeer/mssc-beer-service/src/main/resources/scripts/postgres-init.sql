DROP DATABASE IF EXISTS beerservice;
DROP USER IF EXISTS beer_service_user;
CREATE USER beer PASSWORD 'beer_service_password';
CREATE DATABASE beerservice;
GRANT ALL PRIVILEGES ON DATABASE beerservice TO beer_service_user;
