CREATE TABLE posts (
    id bigserial NOT NULL,
    comments_count bigint,
    likes_count bigint,
    users_profiles_id bigint,
    text character varying(255),
    FOREIGN KEY (users_profiles_id) REFERENCES users_profiles(id),
    primary key(id)
);