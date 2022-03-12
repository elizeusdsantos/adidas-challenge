--liquibase formatted sql

CREATE TABLE subscription (
  id SERIAL PRIMARY KEY,
  email VARCHAR(100),
  first_name VARCHAR(50),
  gender VARCHAR(15),
  birth VARCHAR(20),
  newsletter boolean,
  campaign_id INT
)
