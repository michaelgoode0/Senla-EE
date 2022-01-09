CREATE TABLE posts (
    id bigserial NOT NULL,
    comments_count bigint,
    likes_count bigint,
    text character varying(255),
    author bigint,
    FOREIGN KEY (author) references users_profiles(id),
    primary key(id)
);