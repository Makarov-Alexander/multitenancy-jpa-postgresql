-- add partitioning by department
CREATE TABLE customer_test (
  department varchar(100),
  value      int
) PARTITION BY LIST (department);

CREATE TABLE customer_test_njgniy_vartovsk PARTITION OF customer_test
FOR VALUES IN ('njgniy_vartovsk');

insert into customer_test (department, value) values ('njgniy_vartovsk',1);

select * from customer_test;
select * from customer_test_njgniy_vartovsk;
