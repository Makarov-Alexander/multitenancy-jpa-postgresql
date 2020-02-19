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

CREATE POLICY customer_policy
  ON customer
USING (department = CURRENT_USER);

create role omsk;
create role tomsk;

GRANT SELECT, UPDATE, DELETE ON TABLE customer TO omsk;
GRANT SELECT, UPDATE, DELETE ON TABLE customer TO tomsk;

insert into customer values (2, 'omsk', 'bob', 'bob-last');
insert into customer values (3, 'tomsk', 'alice', 'alice-last');

set role omsk;
select * from customer;

set role tomsk;
select * from customer;

reset role;
select * from customer;
```
