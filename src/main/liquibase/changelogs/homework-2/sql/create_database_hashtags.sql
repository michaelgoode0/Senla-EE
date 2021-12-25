CREATE TABLE hashtags(
                       id bigserial not null ,
                       value character varying(255) UNIQUE,
                       primary key(id)
);