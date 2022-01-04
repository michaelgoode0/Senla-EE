CREATE TABLE invites_from_user (
                                 invites_id bigserial,
                                 from_user_id bigserial,
                                 primary key(invites_id,from_user_id),
                                 FOREIGN key (invites_id) REFERENCES invites(id),
                                 FOREIGN key (from_user_id) REFERENCES users_profiles(id)
);