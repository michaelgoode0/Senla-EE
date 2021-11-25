CREATE TABLE users (
    id bigserial not null,
    email character varying(255),
    password character varying(255),
    primary key(id)
);