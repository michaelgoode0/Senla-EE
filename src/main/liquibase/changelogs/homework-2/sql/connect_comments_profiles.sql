CREATE TABLE comments_profiles(
                               comment_id bigserial,
                               profile_id bigserial,
                               primary key(comment_id,profile_id),
                               FOREIGN key (comment_id) REFERENCES post_comments(id),
                               FOREIGN key (profile_id) REFERENCES users_profiles(id)
);