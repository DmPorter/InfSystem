--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

-- Started on 2023-02-28 22:53:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3413 (class 1262 OID 33089)
-- Name: infSystem; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "infSystem" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "infSystem" OWNER TO postgres;

\connect "infSystem"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 33142)
-- Name: ingredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredient (
                                   id_record bigint NOT NULL,
                                   id_recipe bigint NOT NULL,
                                   quantity integer NOT NULL,
                                   product_id_product bigint
);


ALTER TABLE public.ingredient OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33145)
-- Name: Ingredient_id_record_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ingredient ALTER COLUMN id_record ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Ingredient_id_record_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 33125)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
                                id_product bigint NOT NULL,
                                name text NOT NULL,
                                quantity integer NOT NULL,
                                id_type_product integer NOT NULL,
                                id_unit_measure integer NOT NULL,
                                cost_price double precision,
                                id_provider bigint
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 33124)
-- Name: Product_id_product_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN id_product ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Product_id_product_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 211 (class 1259 OID 33098)
-- Name: recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipe (
                               id_recipe bigint NOT NULL,
                               name text NOT NULL,
                               cost double precision NOT NULL,
                               type_recipe_id_type integer NOT NULL
);


ALTER TABLE public.recipe OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 33182)
-- Name: Recipe_id_recipe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.recipe ALTER COLUMN id_recipe ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Recipe_id_recipe_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 212 (class 1259 OID 33110)
-- Name: type_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_product (
                                     id_type_product bigint NOT NULL,
                                     name text NOT NULL
);


ALTER TABLE public.type_product OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 33173)
-- Name: TypeProduct_id_type_product_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.type_product ALTER COLUMN id_type_product ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."TypeProduct_id_type_product_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 210 (class 1259 OID 33091)
-- Name: type_recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_recipe (
                                    id_type bigint NOT NULL,
                                    text text NOT NULL
);


ALTER TABLE public.type_recipe OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 33090)
-- Name: TypeRecipe_id_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.type_recipe ALTER COLUMN id_type ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."TypeRecipe_id_type_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 213 (class 1259 OID 33117)
-- Name: unit_measurement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.unit_measurement (
                                         id_unit integer NOT NULL,
                                         name text NOT NULL
);


ALTER TABLE public.unit_measurement OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 33172)
-- Name: UnitMeasurement_id_unit_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.unit_measurement ALTER COLUMN id_unit ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."UnitMeasurement_id_unit_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 232 (class 1259 OID 58074)
-- Name: additive; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.additive (
                                 id bigint NOT NULL,
                                 id_product bigint NOT NULL,
                                 id_type_recipe integer NOT NULL,
                                 quantity integer NOT NULL
);


ALTER TABLE public.additive OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 58073)
-- Name: additive_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additive_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additive_id_seq OWNER TO postgres;

--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 231
-- Name: additive_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additive_id_seq OWNED BY public.additive.id;


--
-- TOC entry 234 (class 1259 OID 58091)
-- Name: additive_order_position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.additive_order_position (
                                                id bigint NOT NULL,
                                                id_additive bigint NOT NULL,
                                                quantity integer NOT NULL,
                                                id_order_position bigint NOT NULL
);


ALTER TABLE public.additive_order_position OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 58090)
-- Name: additive_order_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additive_order_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additive_order_position_id_seq OWNER TO postgres;

--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 233
-- Name: additive_order_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additive_order_position_id_seq OWNED BY public.additive_order_position.id;


--
-- TOC entry 221 (class 1259 OID 49858)
-- Name: order_position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_position (
                                       id_pos bigint NOT NULL,
                                       id_recipe bigint NOT NULL,
                                       quantity integer NOT NULL,
                                       id_order bigint NOT NULL
);


ALTER TABLE public.order_position OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 49879)
-- Name: order_position_id_pos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.order_position ALTER COLUMN id_pos ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.order_position_id_pos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 49868)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
                               id_order bigint NOT NULL,
                               date timestamp with time zone NOT NULL,
                               id_person bigint NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 49878)
-- Name: orders_id_order_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id_order ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.orders_id_order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 57981)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
                               id bigint NOT NULL,
                               username text NOT NULL,
                               password text NOT NULL,
                               id_role bigint NOT NULL,
                               name text NOT NULL
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 57980)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO postgres;

--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 227
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- TOC entry 226 (class 1259 OID 57967)
-- Name: provider; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.provider (
                                 id bigint NOT NULL,
                                 name text NOT NULL,
                                 address text NOT NULL
);


ALTER TABLE public.provider OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 57966)
-- Name: provider_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.provider_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.provider_id_seq OWNER TO postgres;

--
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 225
-- Name: provider_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.provider_id_seq OWNED BY public.provider.id;


--
-- TOC entry 230 (class 1259 OID 57990)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
                             id bigint NOT NULL,
                             name text NOT NULL,
                             description text NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 57989)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 229
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- TOC entry 3227 (class 2604 OID 58077)
-- Name: additive id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive ALTER COLUMN id SET DEFAULT nextval('public.additive_id_seq'::regclass);


--
-- TOC entry 3228 (class 2604 OID 58094)
-- Name: additive_order_position id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive_order_position ALTER COLUMN id SET DEFAULT nextval('public.additive_order_position_id_seq'::regclass);


--
-- TOC entry 3225 (class 2604 OID 57984)
-- Name: person id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- TOC entry 3224 (class 2604 OID 57970)
-- Name: provider id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.provider ALTER COLUMN id SET DEFAULT nextval('public.provider_id_seq'::regclass);


--
-- TOC entry 3226 (class 2604 OID 57993)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 3240 (class 2606 OID 33150)
-- Name: ingredient Ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT "Ingredient_pkey" PRIMARY KEY (id_record);


--
-- TOC entry 3238 (class 2606 OID 33131)
-- Name: product Product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY (id_product);


--
-- TOC entry 3232 (class 2606 OID 33104)
-- Name: recipe Recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT "Recipe_pkey" PRIMARY KEY (id_recipe);


--
-- TOC entry 3234 (class 2606 OID 33116)
-- Name: type_product TypeProduct_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_product
    ADD CONSTRAINT "TypeProduct_pkey" PRIMARY KEY (id_type_product);


--
-- TOC entry 3230 (class 2606 OID 33097)
-- Name: type_recipe TypeRecipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_recipe
    ADD CONSTRAINT "TypeRecipe_pkey" PRIMARY KEY (id_type);


--
-- TOC entry 3236 (class 2606 OID 33123)
-- Name: unit_measurement UnitMeasurement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unit_measurement
    ADD CONSTRAINT "UnitMeasurement_pkey" PRIMARY KEY (id_unit);


--
-- TOC entry 3254 (class 2606 OID 58096)
-- Name: additive_order_position additive_order_position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive_order_position
    ADD CONSTRAINT additive_order_position_pkey PRIMARY KEY (id_additive);


--
-- TOC entry 3252 (class 2606 OID 58079)
-- Name: additive additive_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive
    ADD CONSTRAINT additive_pkey PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 49872)
-- Name: orders order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT order_pkey PRIMARY KEY (id_order);


--
-- TOC entry 3242 (class 2606 OID 49862)
-- Name: order_position order_position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_position
    ADD CONSTRAINT order_position_pkey PRIMARY KEY (id_pos);


--
-- TOC entry 3248 (class 2606 OID 57988)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 3246 (class 2606 OID 57974)
-- Name: provider provider_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.provider
    ADD CONSTRAINT provider_pkey PRIMARY KEY (id);


--
-- TOC entry 3250 (class 2606 OID 57997)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 33132)
-- Name: product TypeProduct_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT "TypeProduct_fk" FOREIGN KEY (id_type_product) REFERENCES public.type_product(id_type_product);


--
-- TOC entry 3257 (class 2606 OID 33137)
-- Name: product UnitMeasurement; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT "UnitMeasurement" FOREIGN KEY (id_unit_measure) REFERENCES public.unit_measurement(id_unit);


--
-- TOC entry 3267 (class 2606 OID 58097)
-- Name: additive_order_position additive_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive_order_position
    ADD CONSTRAINT additive_fk FOREIGN KEY (id_additive) REFERENCES public.additive(id);


--
-- TOC entry 3262 (class 2606 OID 49873)
-- Name: order_position fkey_order; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_position
    ADD CONSTRAINT fkey_order FOREIGN KEY (id_order) REFERENCES public.orders(id_order) NOT VALID;


--
-- TOC entry 3261 (class 2606 OID 49863)
-- Name: order_position fkey_recipe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_position
    ADD CONSTRAINT fkey_recipe FOREIGN KEY (id_recipe) REFERENCES public.recipe(id_recipe);


--
-- TOC entry 3255 (class 2606 OID 33105)
-- Name: recipe id_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT id_type_fk FOREIGN KEY (type_recipe_id_type) REFERENCES public.type_recipe(id_type);


--
-- TOC entry 3268 (class 2606 OID 58102)
-- Name: additive_order_position order_position_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive_order_position
    ADD CONSTRAINT order_position_fk FOREIGN KEY (id_order_position) REFERENCES public.order_position(id_pos);


--
-- TOC entry 3263 (class 2606 OID 58003)
-- Name: orders person_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT person_fk FOREIGN KEY (id_person) REFERENCES public.person(id) NOT VALID;


--
-- TOC entry 3258 (class 2606 OID 57975)
-- Name: product proder_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT proder_fk FOREIGN KEY (id_provider) REFERENCES public.provider(id) NOT VALID;


--
-- TOC entry 3260 (class 2606 OID 33156)
-- Name: ingredient product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id_product) REFERENCES public.product(id_product) NOT VALID;


--
-- TOC entry 3265 (class 2606 OID 58080)
-- Name: additive product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive
    ADD CONSTRAINT product_fk FOREIGN KEY (id_product) REFERENCES public.product(id_product);


--
-- TOC entry 3259 (class 2606 OID 33151)
-- Name: ingredient recipe_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT recipe_fk FOREIGN KEY (id_recipe) REFERENCES public.recipe(id_recipe) NOT VALID;


--
-- TOC entry 3264 (class 2606 OID 57998)
-- Name: person role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT role_fk FOREIGN KEY (id_role) REFERENCES public.role(id) NOT VALID;


--
-- TOC entry 3266 (class 2606 OID 58085)
-- Name: additive type_recipe_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additive
    ADD CONSTRAINT type_recipe_fk FOREIGN KEY (id_type_recipe) REFERENCES public.type_recipe(id_type);


-- Completed on 2023-02-28 22:53:51

--
-- PostgreSQL database dump complete
--

