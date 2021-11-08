CREATE TABLE reactions (
    id bigint NOT NULL,
    reaction bit,
    user_profile_id bigint,
    post_id bigint,
    FOREIGN key (user_profile_id) REFERENCES users_profiles(id),
    FOREIGN KEY (post_id) REFERENCES posts(id),
    primary key(id)
);