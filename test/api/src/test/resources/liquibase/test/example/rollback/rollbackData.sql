--liquibase formatted sql

--changeset apitest_rollback_[API_ID]:[API_ID]
DELETE FROM user_service WHERE id = '[ID]';