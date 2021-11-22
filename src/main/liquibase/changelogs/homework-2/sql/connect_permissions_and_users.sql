CREATE TABLE users_roles (
    user_id bigint,
    role_id bigint,
    primary key(user_id,role_id),
    FOREIGN key (user_id) REFERENCES users(id),
    FOREIGN key (role_id) REFERENCES roles(id)
);