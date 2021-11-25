CREATE TABLE roles (
    id bigserial not null ,
    permission character varying(255),
    primary key(id)
);