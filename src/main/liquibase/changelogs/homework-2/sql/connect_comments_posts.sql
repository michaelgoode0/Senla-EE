CREATE TABLE comments_posts(
                               comment_id bigserial,
                               post_id bigserial,
                               primary key(comment_id,post_id),
                               FOREIGN key (comment_id) REFERENCES post_comments(id),
                               FOREIGN key (post_id) REFERENCES posts(id)

);