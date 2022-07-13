
CREATE TABLE IF NOT EXISTS public.tenants (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE public.tenants_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.tenants_id_seq OWNED BY public.tenants.id;

ALTER TABLE ONLY public.tenants ALTER COLUMN id SET DEFAULT nextval('public.tenants_id_seq'::regclass);

ALTER TABLE ONLY public.tenants
    DROP CONSTRAINT IF EXISTS tenants_pkey;

ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.tenants
    DROP CONSTRAINT IF EXISTS uk_4moql6miwoh3w0drxa2gmjbll;

ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT uk_4moql6miwoh3w0drxa2gmjbll UNIQUE (name);

----------------------------------------------------------------------------------------------
CREATE TABLE public.users (
    id integer NOT NULL,
    tenant_id integer NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

ALTER TABLE ONLY public.users
    DROP CONSTRAINT IF EXISTS uk_6dotkott2kjsp8vw4d0m25fb7;

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

ALTER TABLE ONLY public.users
    DROP CONSTRAINT IF EXISTS users_pkey;

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    DROP CONSTRAINT IF EXISTS fk_tenant_id;

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_tenant_id FOREIGN KEY (tenant_id) REFERENCES public.tenants(id)

