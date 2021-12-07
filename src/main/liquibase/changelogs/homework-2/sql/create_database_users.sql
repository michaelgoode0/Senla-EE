CREATE TABLE users (
    id bigserial not null,
    username character varying(255) UNIQUE ,
    password character varying(255),
    primary key(id)
);