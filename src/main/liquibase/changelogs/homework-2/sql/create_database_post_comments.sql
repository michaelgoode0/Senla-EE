CREATE TABLE post_comments (
    id bigserial not null ,
    text character varying(255),
    post_id bigint,
    author bigint,
    FOREIGN KEY (post_id) references posts(id),
    FOREIGN KEY (author) references users_profiles(id),
    PRIMARY KEY(id)
);