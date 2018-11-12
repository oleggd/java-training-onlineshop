------------ products -----------------------------------
DROP TABLE public.product;
DROP SEQUENCE public.product_id_seq CASCADE;

CREATE SEQUENCE public.product_id_seq START 1 INCREMENT 1;

CREATE TABLE public.product (
    id            SERIAL                   NOT NULL,
    name          CHARACTER VARYING(255)   NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    price         REAL                     NOT NULL
);

ALTER TABLE ONLY public.product ADD CONSTRAINT product_pk PRIMARY KEY (id);

ALTER TABLE public.product OWNER to postgres;

------------ user -----------------------------------
DROP TABLE public.users;
DROP SEQUENCE public.user_id_seq CASCADE;

CREATE TABLE public.users (
    id            SERIAL                   NOT NULL,
    name          CHARACTER VARYING(255)   NOT NULL,
    password      CHARACTER VARYING(255)   NOT NULL,
    sole          CHARACTER VARYING(255)   NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    role          CHARACTER VARYING(255)   NOT NULL
);

ALTER TABLE ONLY public.users ADD CONSTRAINT users_pk PRIMARY KEY (id);

ALTER TABLE public.users OWNER to postgres;

CREATE SEQUENCE public.user_id_seq START 1 INCREMENT 1;

------------ permissions -----------------------------------
DROP TABLE public.permissions;
DROP SEQUENCE public.permission_id_seq CASCADE;

CREATE TABLE public.permissions (
    id            SERIAL                   NOT NULL,
    role          CHARACTER VARYING(255)   NOT NULL,
    object        CHARACTER VARYING(255)   NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL
);

ALTER TABLE ONLY public.permissions ADD CONSTRAINT permissions_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.permissions ADD CONSTRAINT permissions_uk UNIQUE (role,object);

ALTER TABLE public.permissions OWNER to postgres;

CREATE SEQUENCE public.permission_id_seq START 1 INCREMENT 1;