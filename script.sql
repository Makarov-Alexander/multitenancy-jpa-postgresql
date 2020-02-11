create role application_user;

grant all on all tables in schema public to application_user;

alter table customer enable row level security;

create policy data_owner
  on customer
for all
to application_user
using (
  (
  select true as bool from (
    select first_name
      from customer
     where first_name = current_user
    ) as cfn
  ) = true
) with check (true);

insert into customer values (2, 'bob', 'bob-last');

insert into customer values (3, 'alice', 'alice-last');

create table users (
  name text not null primary key,
  role text unique not null
);

insert into users values ('bob', 'bob');
insert into users values ('alice', 'alice');


set role bob;
select * from customer;

