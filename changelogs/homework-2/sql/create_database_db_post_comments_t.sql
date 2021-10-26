CREATE TABLE post_comments_t (
    id bigint not null ,
    text character varying(255),
    posts_id bigint,
    FOREIGN KEY (posts_id) REFERENCES posts_t(id),
    PRIMARY KEY(id)
);