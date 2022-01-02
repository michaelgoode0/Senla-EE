CREATE TABLE posts_profiles(
                               post_id bigserial,
                               profile_id bigserial,
                               primary key(post_id,profile_id),
                               FOREIGN key (post_id) REFERENCES posts(id),
                               FOREIGN key (profile_id) REFERENCES users_profiles(id)

);