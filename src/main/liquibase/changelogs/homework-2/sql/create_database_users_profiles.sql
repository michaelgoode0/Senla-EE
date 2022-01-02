CREATE TABLE users_profiles (
    id bigserial not null,
    firstname character varying(255),
    surname character varying(255),
    town character varying(255),
    phoneNumber bigint,
    primary key(id)
);