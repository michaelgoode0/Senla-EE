CREATE TABLE reactions_profiles(
                               reaction_id bigserial,
                               profile_id bigserial,
                               primary key(reaction_id,profile_id),
                               FOREIGN key (reaction_id) REFERENCES reactions(id),
                               FOREIGN key (profile_id) REFERENCES users_profiles(id)
);