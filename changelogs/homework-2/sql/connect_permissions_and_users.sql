CREATE TABLE users_permissions (
    users_id bigint,
    permissions_id bigint,
    primary key(users_id,permissions_id),
    FOREIGN key (users_id) REFERENCES users_t(id),
    FOREIGN key (permissions_id) REFERENCES permissions_t(id)
);