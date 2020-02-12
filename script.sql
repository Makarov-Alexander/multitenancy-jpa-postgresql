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
