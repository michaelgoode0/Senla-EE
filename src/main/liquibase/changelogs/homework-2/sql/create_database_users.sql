CREATE TABLE users (
    id bigint not null,
    email character varying(255),
    password character varying(255),
    users_profile_id bigint,
    primary key(id)
);