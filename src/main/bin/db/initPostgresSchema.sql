--
-- PostgreSQL database dump
--

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

CREATE SCHEMA ec;

COMMENT ON SCHEMA ec IS 'standard ec schema';

SET default_tablespace = '';

SET default_table_access_method = heap;

-- 'Courses' table stores information about a course
CREATE TABLE ec.courses (
    course_id character varying NOT NULL,
    code character varying,
    name character varying,
    creation_timestamp timestamp default current_timestamp
);

ALTER TABLE ONLY ec.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY ("course_id");


-- 'Enrolments' table tracks which course a user is enrolled in
CREATE TABLE ec.enrolments (
    enrolment_id character varying NOT NULL,
    user_id character varying NOT NULL,
    course_id character varying NOT NULL
);

ALTER TABLE ONLY ec.enrolments
    ADD CONSTRAINT enrolments_pkey PRIMARY KEY ("enrolment_id", "user_id", "course_id");


-- 'Messages' table stores the message thread within a solution
CREATE TABLE ec.messages (
    message_id character varying NOT NULL,
    solution_id character varying NOT NULL,
    user_id character varying NOT NULL,
    parent_id character varying,
    body text,
    sent_timestamp timestamp default current_timestamp
);

ALTER TABLE ONLY ec.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY ("message_id", "solution_id", "user_id");


-- 'Solutions' table stores information regarding a solution to a test
CREATE TABLE ec.solutions (
    solution_id character varying NOT NULL,
    test_id character varying NOT NULL,
    user_id character varying NOT NULL,
    vote_total integer,
    recorded_score integer,
    estimated_time integer,
    root_message_id character varying NOT NULL,
    creation_timestamp timestamp default current_timestamp
);


-- 'Tests' table stores information regarding a test uploaded by a user
CREATE TABLE ec.tests (
    test_id character varying NOT NULL,
    user_id character varying NOT NULL,
    course_id character varying NOT NULL,
    test_type character(250),
    number_of_questions integer,
    estimated_time integer,
    creation_timestamp timestamp default current_timestamp
);

ALTER TABLE ONLY ec.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY ("test_id", "user_id", "course_id");


-- 'Users' table stores information about a user
CREATE TABLE ec.users (
    user_id character varying NOT NULL,
    email character varying NOT NULL,
    first_name character(250) NOT NULL,
    last_name character(250) NOT NULL,
    password character varying NOT NULL,
    creation_timestamp timestamp default current_timestamp
);

ALTER TABLE ONLY ec.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


-- Inserting dummy data

DELETE FROM ec.courses;
INSERT INTO ec.courses VALUES ('h9ib1a73','CSC207', 'Software Design', current_timestamp),
('aa7hcumk','CSC236', 'Introduction to the Theory of Computation', current_timestamp),
('koz8t694','CSC209', 'Software Tools and Systems Programming', current_timestamp),
('nmbyan9a','CSC263', 'Data Structures and Analysis', current_timestamp),
('u8489vqk','CSC309', 'Programming on the Web', current_timestamp);

DELETE FROM ec.enrolments;
INSERT into ec.enrolments 
VALUES ('slhu4d40', 'wx1of70l', 'h9ib1a73'),
('qd3qx7yk', 'wx1of70l', 'koz8t694'),
('bhljuq6s', 'wx1of70l', 'u8489vqk'),
('fjoa25pm', 'mtq2r09h', 'h9ib1a73'),
('l06590op', 'mtq2r09h', 'u8489vqk'),
('5gjnmbg9', 'b36as7qx', 'aa7hcumk'),
('q50jleu2', 'b36as7qx', 'h9ib1a73'),
('tvqkk4cl', '4b6v23zg', 'aa7hcumk'),
('fhb9n1jw', '4b6v23zg', 'nmbyan9a'),
('jdl3mgjq', 'csye07l8', 'nmbyan9a');

DELETE FROM ec.users;
INSERT INTO ec.users
VALUES ('wx1of70l', 'harvey@utoronto.ca', 'Harvey', 'Donnelley', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', current_timestamp), -- abc123
('mtq2r09h', 'kento@utoronto.ca', 'Kento', 'Takeda', '8f61ad5cfa0c471c8cbf810ea285cb1e5f9c2c5e5e5e4f58a3229667703e1587', current_timestamp), -- def456
('b36as7qx', 'paul@utoronto.ca', 'Paul', 'Bangalan', 'dd130a849d7b29e5541b05d2f7f86a4acd4f1ec598c1c9438783f56bc4f0ff80', current_timestamp), -- 123abc
('4b6v23zg', 'darlyn@utoronto.ca', 'Darlyn', 'Nguyen', '5e689fe2eaed097f8a9dc74e6d2aaaef59688d820ecb4cefc7b1a65cc78fa0a2', current_timestamp), -- 456def
('csye07l8', 'nyko@utoronto.ca', 'Nyko', 'Dionisio', '1ae86748124fc4bb2e8ddf430cf146689dec449c811096329beeef55d357967d', current_timestamp), -- 1abc2
('hph52pue', 'nicholas@utoronto.ca', 'Nicholas', 'Mckee', '743d0b05d4b0b58b2b516b83919c782b0443e25e1b2618b28328cbae7570ff9a', current_timestamp), -- 3def4
('bzjpq4bh', 'humraj@utoronto.ca', 'Humraj', 'Bhoday', '1f91bc2741ddcd07dc2abd277d412010e6e0bdf6532aae15e8d0715555415661', current_timestamp), -- 5ghi6
('timlxndy', 'zhihao@utoronto.ca', 'Zhihao', 'Lim', '5a3cf14cf8263fa316a6dad08d1a2f14432fcbfd74e3ae97e9fd1b3dee16703b', current_timestamp); -- 7jkl8

DELETE FROM ec.tests;
INSERT INTO ec.tests 
VALUES ('igt4sstc', 'wx1of70l', 'h9ib1a73', 'MCQ', '10', '30', current_timestamp),
('wdgi1rq1', 'mtq2r09h', 'h9ib1a73', 'MCQ', '20', '60', current_timestamp),
('86pmfyv6', 'wx1of70l', 'aa7hcumk', 'Multiselect', '10', '20', current_timestamp),
('lfv2iub7', 'b36as7qx', 'koz8t694', 'Open-ended', '5', '60', current_timestamp),
('54exqbk6', 'b36as7qx', 'nmbyan9a', 'MCQ', '30', '60', current_timestamp),
('6uobpwih', '4b6v23zg', 'koz8t694', 'Multiselect', '10', '70', current_timestamp),
('kv9nno9k', '4b6v23zg', 'aa7hcumk', 'MCQ', '20', '40', current_timestamp),
('igptejsm', 'csye07l8', 'nmbyan9a', 'Open-ended', '3', '50', current_timestamp),
('33bpyulj', 'hph52pue', 'h9ib1a73', 'MCQ', '1', '50', current_timestamp),
('beeg1li4', 'bzjpq4bh', 'aa7hcumk', 'MCQ', '12', '25', current_timestamp);

DELETE FROM ec.solutions;
INSERT INTO ec.solutions
VALUES ('r5hdyrg2', 'igt4sstc', 'mtq2r09h', 5, 10, 20, 'w2ilpab8', current_timestamp),
('v5apbbr0', 'igt4sstc', 'b36as7qx', 10, 10, 20, 'i524wrxd', current_timestamp),
('pw7mh6vf', 'wdgi1rq1', 'b36as7qx', 15, 10, 20, 'b2jyql0z', current_timestamp),
('hi0ve0z5', 'wdgi1rq1', 'mtq2r09h', 2, 10, 20, 'lswlchjc', current_timestamp),
('wq3t8cps', '86pmfyv6', '4b6v23zg', 10, 10, 20, 'o9szv3uk', current_timestamp);

DELETE FROM ec.messages;
INSERT INTO ec.messages
VALUES ('w2ilpab8', 'r5hdyrg2', 'b36as7qx', null, 'great answer!', current_timestamp),
('i524wrxd', 'v5apbbr0', '4b6v23zg', null, 'best answer!', current_timestamp),
('b2jyql0z', 'pw7mh6vf', 'mtq2r09h', null, 'excellent answer!', current_timestamp),
('lswlchjc', 'hi0ve0z5', 'mtq2r09h', null, 'decent answer!', current_timestamp),
('o9szv3uk', 'wq3t8cps', 'mtq2r09h', null, 'wrong answer!', current_timestamp),
('jrkkjkd2', 'r5hdyrg2', 'wx1of70l', 'w2ilpab8', 'great answer 2!', current_timestamp),
('jm8hn6u8', 'r5hdyrg2', '4b6v23zg', 'jrkkjkd2', 'great answer 3!', current_timestamp),
('b7jk4iy3', 'v5apbbr0', 'csye07l8', 'i524wrxd', 'best answer 2!', current_timestamp),
('99koseyd', 'v5apbbr0', 'mtq2r09h', 'b7jk4iy3', 'best answer 3!', current_timestamp),
('7zsgpyrp', 'pw7mh6vf', 'csye07l8', 'b2jyql0z', 'excellent answer 2!', current_timestamp),
('8kh1bmaa', 'pw7mh6vf', 'hph52pue', '7zsgpyrp', 'excellent answer 3!', current_timestamp);
