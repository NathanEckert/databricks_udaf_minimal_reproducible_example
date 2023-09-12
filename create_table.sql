CREATE SCHEMA test_schema_debug;


CREATE TABLE
  `test_schema_debug`.`table_for_udaf`
(
  id BIGINT NOT NULL,
  quantity DOUBLE NOT NULL
);

INSERT INTO
  `test_schema_debug`.`table_for_udaf`
VALUES
(1, 1.0),
(2, 2.0);