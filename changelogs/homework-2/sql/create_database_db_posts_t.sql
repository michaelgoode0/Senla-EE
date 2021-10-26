CREATE TABLE posts_t (
    id bigint NOT NULL,
    comments_count bigint,
    likes_count bigint,
    users_profiles_id bigint,
    text character varying(255),
    FOREIGN KEY (users_profiles_id) REFERENCES users_profiles_t(id),
    primary key(id)
);