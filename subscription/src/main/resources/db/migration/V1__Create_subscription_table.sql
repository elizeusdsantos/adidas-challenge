--liquibase formatted sql

CREATE TABLE subscription (
  id UUID NOT NULL,
  subscription_id SERIAL PRIMARY KEY,
  email VARCHAR(200) NOT NULL,
  first_name VARCHAR(50),
  gender VARCHAR(15),
  birth_date DATE NOT NULL,
  active boolean NOT NULL,
  campaign_id INT NOT NULL
)
