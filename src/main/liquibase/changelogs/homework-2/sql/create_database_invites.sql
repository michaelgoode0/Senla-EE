CREATE TABLE invites(
    id bigserial,
    date_of_invite DATE,
    status varchar(255),
    from_user_id bigint,
    to_user_id bigint,
    FOREIGN KEY (from_user_id) references users_profiles(id),
    FOREIGN KEY (to_user_id) references users_profiles(id),
    primary key(id)
);