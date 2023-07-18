create table if not exists public.clients
(
    balance        numeric(38, 2),
    created_at     timestamp(6),
    id             bigserial
    primary key,
    updated_at     timestamp(6),
    account_number varchar(255) unique,
    currency       varchar(255),
    first_name     varchar(255),
    last_name      varchar(255)
    );

alter table public.clients
    owner to postgres;

create table if not exists public.payments
(
    transaction_sum numeric(38, 2),
    created_at      timestamp(6),
    id              bigserial
    primary key,
    receiver_id     bigint references public.clients(id) on delete cascade,
    sender_id       bigint references public.clients(id) on delete cascade,
    updated_at      timestamp(6)
    );

alter table public.payments
    owner to postgres;

create table if not exists public.transactions
(
    transaction_sum         numeric(38, 2),
    created_at              timestamp(6),
    id                      bigserial
    primary key,
    updated_at              timestamp(6),
    receiver_account_number varchar(255) references public.clients(account_number) on delete cascade,
    sender_account_number   varchar(255) references public.clients(account_number) on delete cascade
    );

alter table public.transactions
    owner to postgres;
