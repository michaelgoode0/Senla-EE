CREATE TABLE reactions (
    id bigserial NOT NULL,
    reaction boolean,
    user_profile_id bigint,
    post_id bigint,
    FOREIGN key (user_profile_id) REFERENCES users_profiles(id),
    FOREIGN KEY (post_id) REFERENCES posts(id),
    primary key(id)
);