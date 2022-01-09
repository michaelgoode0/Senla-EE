CREATE TABLE users (
    id bigserial not null,
    username character varying(255) UNIQUE ,
    password character varying(255),
    profile_id bigint,
    FOREIGN KEY (profile_id) references users_profiles(id),
    primary key(id)
);