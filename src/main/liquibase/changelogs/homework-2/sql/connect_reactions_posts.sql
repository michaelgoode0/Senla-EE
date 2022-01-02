CREATE TABLE reactions_posts(
                               reaction_id bigserial,
                               post_id bigserial,
                               primary key(reaction_id,post_id),
                               FOREIGN key (reaction_id) REFERENCES reactions(id),
                               FOREIGN key (post_id) REFERENCES posts(id)
);