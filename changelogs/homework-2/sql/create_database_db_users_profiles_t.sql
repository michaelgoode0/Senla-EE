CREATE TABLE users_profiles_t (
    id bigint not null,
    name character varying(255),
    users_id bigint,
    FOREIGN KEY (users_id) REFERENCES users_t(id),
    primary key(id)
);