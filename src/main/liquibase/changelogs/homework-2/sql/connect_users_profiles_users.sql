CREATE TABLE profiles_users(
                             user_profile_id bigserial,
                             user_id bigserial,
                             primary key(user_profile_id,user_id),
                             FOREIGN key (user_profile_id) REFERENCES users_profiles(id),
                             FOREIGN key (user_id) REFERENCES users(id)
);