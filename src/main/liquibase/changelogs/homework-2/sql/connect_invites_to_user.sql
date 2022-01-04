CREATE TABLE invites_to_user (
                                invites_id bigserial,
                                to_user_id bigserial,
                                primary key(invites_id,to_user_id),
                                FOREIGN key (invites_id) REFERENCES invites(id),
                                FOREIGN key (to_user_id) REFERENCES users_profiles(id)
);