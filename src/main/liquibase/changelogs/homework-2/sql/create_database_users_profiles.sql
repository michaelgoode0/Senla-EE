CREATE TABLE users_profiles (
    id bigserial not null,
    name character varying(255),
    users_id bigint,
    FOREIGN KEY (users_id) REFERENCES users(id),
    primary key(id)
);