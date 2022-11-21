--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-1.pgdg20.04+1)
-- Dumped by pg_dump version 15.0

-- Started on 2022-11-02 20:47:56 EDT

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: ec; Type: SCHEMA; Schema: -; Owner: ogbpbdlmyteimu
--

CREATE SCHEMA ec;


-- ALTER SCHEMA ec OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 4325 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA ec; Type: COMMENT; Schema: -; Owner: ogbpbdlmyteimu
--

COMMENT ON SCHEMA ec IS 'standard ec schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 14076539)
-- Name: courses; Type: TABLE; Schema: ec; Owner: ogbpbdlmyteimu
--

CREATE TABLE ec.courses (
    course_id character varying NOT NULL,
    code character varying,
    name character varying,
    creation_timestamp timestamp without time zone
);


-- ALTER TABLE ec.courses OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 212 (class 1259 OID 14076904)
-- Name: enrolments; Type: TABLE; Schema: ec; Owner: ogbpbdlmyteimu
--

CREATE TABLE ec.enrolments (
    enrollment_id character varying NOT NULL,
    user_id character varying NOT NULL,
    course_id character varying NOT NULL
);


-- ALTER TABLE ec.enrolments OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 215 (class 1259 OID 14077254)
-- Name: messages; Type: TABLE; Schema: ec; Owner: ogbpbdlmyteimu
--

CREATE TABLE ec.messages (
    message_id character varying NOT NULL,
    solution_id character varying NOT NULL,
    user_id character varying NOT NULL,
    parent_id character varying,
    body text,
    sent_timestamp timestamp without time zone NOT NULL
);


-- ALTER TABLE ec.messages OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 214 (class 1259 OID 14077153)
-- Name: solutions; Type: TABLE; Schema: ec; Owner: ogbpbdlmyteimu
--

CREATE TABLE ec.solutions (
    solution_id character varying NOT NULL,
    test_id character varying NOT NULL,
    user_id character varying NOT NULL,
    vote_total integer,
    recorded_score integer,
    estimated_time integer,
    root_message_id bigint NOT NULL,
    creation_timestamp timestamp without time zone
);


-- ALTER TABLE ec.solutions OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 213 (class 1259 OID 14076919)
-- Name: tests; Type: TABLE; Schema: ec; Owner: ogbpbdlmyteimu
--

CREATE TABLE ec.tests (
    test_id character varying NOT NULL,
    user_id character varying NOT NULL,
    course_id character varying NOT NULL,
    test_type character(250),
    number_of_questions integer,
    estimated_time integer,
    creation_timestamp timestamp without time zone
);


-- ALTER TABLE ec.tests OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 210 (class 1259 OID 14076040)
-- Name: users; Type: TABLE; Schema: ec; Owner: ogbpbdlmyteimu
--

CREATE TABLE ec.users (
    userid bigint NOT NULL,
    email character varying NOT NULL,
    first_name character(250) NOT NULL,
    last_name character(250) NOT NULL,
    password character varying NOT NULL,
    creation_timestamp timestamp without time zone NOT NULL
);


-- ALTER TABLE ec.users OWNER TO ogbpbdlmyteimu;

--
-- TOC entry 4174 (class 2606 OID 14076545)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: ec; Owner: ogbpbdlmyteimu
--

ALTER TABLE ONLY ec.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (course_id);


--
-- TOC entry 4176 (class 2606 OID 14076908)
-- Name: enrolments enrolments_pkey; Type: CONSTRAINT; Schema: ec; Owner: ogbpbdlmyteimu
--

ALTER TABLE ONLY ec.enrolments
    ADD CONSTRAINT enrolments_pkey PRIMARY KEY (enrollment_id, user_id, course_id);


--
-- TOC entry 4180 (class 2606 OID 14077260)
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: ec; Owner: ogbpbdlmyteimu
--

ALTER TABLE ONLY ec.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (message_id, solution_id, user_id);


--
-- TOC entry 4178 (class 2606 OID 14076923)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: ec; Owner: ogbpbdlmyteimu
--

ALTER TABLE ONLY ec.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (test_id, user_id, course_id);


--
-- TOC entry 4172 (class 2606 OID 14076046)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: ec; Owner: ogbpbdlmyteimu
--

ALTER TABLE ONLY ec.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- TOC entry 4326 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA ec; Type: ACL; Schema: -; Owner: ogbpbdlmyteimu
--

-- REVOKE USAGE ON SCHEMA ec FROM ec;
-- GRANT ALL ON SCHEMA ec TO ec;


--
-- TOC entry 4327 (class 0 OID 0)
-- Dependencies: 846
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

-- GRANT ALL ON LANGUAGE plpgsql TO ogbpbdlmyteimu;


-- Completed on 2022-11-02 20:47:58 EDT

--
-- PostgreSQL database dump complete
--

