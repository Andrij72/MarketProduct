drop table if exists shop.buyer cascade;
drop table if exists shop.product cascade;
drop table if exists shop.purchase cascade;

create table shop.buyer
(
    id         bigint generated always as identity
        constraint buyers_pkey
            primary key,
    first_name text not null,
    last_name  text not null
);

create table shop.product
(
    id   bigint generated always as identity
        constraint products_pkey
            primary key,
    name text   not null,
    cost bigint not null
);


create table shop.purchase
(
    id bigint generated always as identity
      primary key, product_id bigint not null
        constraint fkey_product references shop.product ON DELETE CASCADE,
    date_purchase timestamp with time zone default now() not null,
    buyer_id bigint not null
        constraint fkey_buyer references shop.buyer ON DELETE CASCADE
);