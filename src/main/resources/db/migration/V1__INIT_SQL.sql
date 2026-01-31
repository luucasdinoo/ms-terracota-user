-- Table: Role
CREATE TABLE public.trc_role (
         role_id varchar(32) NOT NULL,
         description varchar(15) NOT NULL,
         CONSTRAINT trc_role_pkey PRIMARY KEY (role_id)
);

-- Table: Document
CREATE TABLE public.trc_document (
     document_id varchar(32) NOT NULL,
     document_type varchar(10) NOT NULL,
     value varchar(15) NOT NULL,
     CONSTRAINT trc_document_document_type_check CHECK (((document_type)::text = ANY ((ARRAY['CPF'::character varying, 'CNPJ'::character varying])::text[]))),
	 CONSTRAINT trc_document_pkey PRIMARY KEY (document_id)
);

-- Table: User
CREATE TABLE public.trc_user (
     user_id varchar(32) NOT NULL,
     active bool NOT NULL DEFAULT TRUE,
     email varchar(150) NOT NULL UNIQUE,
     name varchar(254) NOT NULL,
     password varchar(254) NOT NULL,
     phone varchar(11) NULL,
     username varchar(50) NOT NULL UNIQUE,
     user_type varchar(15) NOT NULL,
     CONSTRAINT trc_user_pkey PRIMARY KEY (user_id),
     CONSTRAINT trc_user_username_uq UNIQUE (username),
     CONSTRAINT trc_user_email_uq UNIQUE (email),
     CONSTRAINT trc_user_type_check CHECK (((user_type)::text = ANY (ARRAY[('CUSTOMER'::character varying)::text, ('CRAFTSMAN'::character varying)::text, ('ENTERPRISE'::character varying)::text, ('ADMIN'::character varying)::text, ('USER'::character varying)::text])))
);

-- Table: User_Role
CREATE TABLE public.trc_user_role (
      user_id varchar(32) NOT NULL,
      role_id varchar(32) NOT NULL,
      CONSTRAINT trc_user_role_pkey PRIMARY KEY (user_id, role_id),
      CONSTRAINT trc_user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES public.trc_user(user_id),
      CONSTRAINT trc_user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES public.trc_role(role_id)
);

-- Table: Address
CREATE TABLE public.trc_address (
    address_id varchar(32) NOT NULL,
    address_type varchar(15) NOT NULL,
    city varchar(60) NOT NULL,
    country varchar(90) NOT NULL,
    address_name varchar(100) NOT NULL,
    neighborhood varchar(60) NOT NULL,
    number varchar(10) NOT NULL,
    street varchar(90) NOT NULL,
    zip varchar(8) NOT NULL,
    address_user_id varchar(32) NOT NULL,
    CONSTRAINT trc_address_address_type_check CHECK (((address_type)::text = ANY ((ARRAY['BILLING'::character varying, 'DELIVERY'::character varying])::text[]))),
	CONSTRAINT trc_address_pkey PRIMARY KEY (address_id),
	CONSTRAINT trc_address_address_user_id_fk FOREIGN KEY (address_user_id) REFERENCES public.trc_user(user_id)
);

-- Table: Customer
CREATE TABLE public.trc_customer (
     customer_id varchar(32) NOT NULL,
     created_at timestamptz(6) NOT NULL,
     date_of_birth timestamp(6) NOT NULL,
     updated_at timestamptz(6) NULL,
     document_id varchar(32) NOT NULL UNIQUE,
     user_id varchar(32) NOT NULL UNIQUE,
     CONSTRAINT trc_customer_pkey PRIMARY KEY (customer_id),
     CONSTRAINT trc_customer_user_id_uq UNIQUE (user_id),
     CONSTRAINT trc_customer_document_id_uq UNIQUE (document_id),
     CONSTRAINT trc_customer_document_id_fk FOREIGN KEY (document_id) REFERENCES public.trc_document(document_id),
     CONSTRAINT trc_customer_user_id_fk FOREIGN KEY (user_id) REFERENCES public.trc_user(user_id)
);

-- Table: Craftsman
CREATE TABLE public.trc_craftsman (
      craftsman_id varchar(32) NOT NULL,
      created_at timestamptz(6) NOT NULL,
      date_of_birth timestamp(6) NOT NULL,
      updated_at timestamptz(6) NULL,
      document_id varchar(32) NOT NULL,
      user_id varchar(32) NOT NULL,
      CONSTRAINT trc_craftsman_pkey PRIMARY KEY (craftsman_id),
      CONSTRAINT trc_craftsman_document_id_uq UNIQUE (document_id),
      CONSTRAINT trc_craftsman_user_id_uq UNIQUE (user_id),
      CONSTRAINT trc_craftsman_user_id_fk FOREIGN KEY (user_id) REFERENCES public.trc_user(user_id),
      CONSTRAINT trc_craftsman_document_id_fk FOREIGN KEY (document_id) REFERENCES public.trc_document(document_id)
);