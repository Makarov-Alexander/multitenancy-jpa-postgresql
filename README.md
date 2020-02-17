# Multitenancy JPA PostgreSQL
MVP multitenancy spring boot JPA application, which is working thanks PostgreSQL row-level-security.

# Note
Application use row-level-security feature in PostgreSQL.  
There is analog sql-example below.
```
create table customer
(
  id         bigint       not null
    constraint customer_pkey
    primary key,
  department varchar(255) not null,
  first_name varchar(255),
  last_name  varchar(255)
);

ALTER TABLE customer ENABLE ROW LEVEL SECURITY;

GRANT SELECT, UPDATE, DELETE ON TABLE customer TO bob;
GRANT SELECT, UPDATE, DELETE ON TABLE customer TO alice;

CREATE POLICY customer_policy
  ON customer
USING (first_name = CURRENT_USER);

create role bob;
create role alice;

insert into customer values (2, 'bob', 'bob-last');
insert into customer values (3, 'alice', 'alice-last');

set role bob;
select * from customer;

set role alice;
select * from customer;

reset role;
select * from customer;
```
