--liquibase formatted sql

--changeset apitest_[API_ID]:[API_ID]
INSERT INTO user_service (id, user_id) VALUES ('[ID]','[USER_ID]');