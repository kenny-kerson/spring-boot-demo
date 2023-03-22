--schema-@@platform@@.sql 파일을 구동시, 스프링부트가 플랫폼별로 자동으로 실행시켜준다.
--all 옵션은, 플랫폼에 상관없이 모두 실행시킨다.
DROP TABLE people IF EXISTS;

CREATE TABLE people (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);