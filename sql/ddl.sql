drop table if exists member CASCADE;
CREATE TABLE member (
    id bigint generated default by identity,
    name varchar(255),
    primary key(id)
);
