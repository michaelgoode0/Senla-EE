CREATE TABLE reactions (
    id bigserial NOT NULL,
    reaction boolean,
    post_id bigint,
    author bigint,
    FOREIGN KEY (post_id) references posts(id),
    FOREIGN KEY (author) references users_profiles(id),
    primary key(id)
);