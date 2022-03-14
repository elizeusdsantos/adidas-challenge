--liquibase formatted sql

CREATE TABLE subscription (
  id UUID,
  subscription_id SERIAL PRIMARY KEY,
  email VARCHAR(200),
  first_name VARCHAR(50),
  gender VARCHAR(15),
  birth_date DATE,
  active boolean,
  campaign_id INT
)
