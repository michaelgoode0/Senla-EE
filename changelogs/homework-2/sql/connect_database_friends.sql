CREATE TABLE friends (
    to_user_id bigint,
    from_user_id bigint,
    primary key(to_user_id,from_user_id),
    FOREIGN key (to_user_id) REFERENCES users_profiles(id),
    FOREIGN key (from_user_id) REFERENCES users_profiles(id)
);