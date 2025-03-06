DROP TABLE IF EXISTS Member;

create table Member
(
    id integer not null,
    name varchar(255) not null,
    primary key (id)
);

DROP TABLE IF EXISTS URL;

CREATE TABLE URL (
    id integer not null,
    origin_url VARCHAR(10000) not null unique,
    count integer default 0,
    short_url VARCHAR(8) unique,
    primary key (id)
);