CREATE TABLE users (
    id bigserial not null,
    username character varying(255),
    password character varying(255),
    token character varying(255),
    primary key(id)
);