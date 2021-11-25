CREATE TABLE post_comments (
    id bigserial not null ,
    text character varying(255),
    posts_id bigint,
    users_profile_id bigint,
    FOREIGN KEY (posts_id) REFERENCES posts(id),
    FOREIGN KEY  (users_profile_id) REFERENCES users_profiles(id),
    PRIMARY KEY(id)
);