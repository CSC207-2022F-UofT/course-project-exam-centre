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
    "courseId" bigint NOT NULL,
    code character varying,
    name character varying,
    "creationTimestamp" timestamp without time zone
);

ALTER TABLE ONLY ec.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY ("courseId");


-- 'Enrolments' table tracks which course a user is enrolled in
CREATE TABLE ec.enrolments (
    "enrollmentId" bigint NOT NULL,
    "userId" bigint NOT NULL,
    "courseId" bigint NOT NULL
);

ALTER TABLE ONLY ec.enrolments
    ADD CONSTRAINT enrolments_pkey PRIMARY KEY ("enrollmentId", "userId", "courseId");


-- 'Messages' table stores the message thread within a solution
CREATE TABLE ec.messages (
    "messageId" bigint NOT NULL,
    "solutionId" bigint NOT NULL,
    "userId" bigint NOT NULL,
    "parentId" bigint,
    body text,
    "sentTimestamp" timestamp without time zone NOT NULL
);

ALTER TABLE ONLY ec.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY ("messageId", "solutionId", "userId");


-- 'Solutions' table stores information regarding a solution to a test
CREATE TABLE ec.solutions (
    "solutionId" bigint NOT NULL,
    "testId" bigint NOT NULL,
    "userId" bigint NOT NULL,
    "voteTotal" integer,
    "recordedScore" integer,
    "estimateTime" integer,
    "rootMessageId" bigint NOT NULL,
    "creationTimestamp" timestamp without time zone
);


-- 'Tests' table stores information regarding a test uploaded by a user
CREATE TABLE ec.tests (
    "testId" bigint NOT NULL,
    "userId" bigint NOT NULL,
    "courseId" bigint NOT NULL,
    "testType" character(250),
    "numberOfQuestions" integer,
    "estimatedTime" integer,
    "creationTimestamp" timestamp without time zone
);

ALTER TABLE ONLY ec.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY ("testId", "userId", "courseId");


-- 'Users' table stores information about a user
CREATE TABLE ec.users (
    userid bigint NOT NULL,
    email character varying NOT NULL,
    "firstName" character(250) NOT NULL,
    "lastName" character(250) NOT NULL,
    password character varying NOT NULL,
    "creationTimestamp" timestamp without time zone NOT NULL
);

ALTER TABLE ONLY ec.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


-- Inserting dummy data

DELETE FROM courses;
INSERT INTO courses VALUES (1,'CSC207', 'Software Design', current_timestamp),
(2,'CSC236', 'Introduction to the Theory of Computation', current_timestamp),
(3,'CSC209', 'Software Tools and Systems Programming', current_timestamp),
(4,'CSC263', 'Data Structures and Analysis', current_timestamp),
(5,'CSC309', 'Programming on the Web', current_timestamp);

DELETE FROM enrolments;
INSERT into enrolments 
VALUES (1, 1, 1),
(2, 1, 3),
(3, 1, 5),
(4, 2, 1),
(5, 2, 5),
(6, 3, 2),
(7, 3, 1),
(8, 4, 2),
(9, 4, 4),
(10, 5, 4);

DELETE FROM users;
INSERT INTO users
VALUES (1, 'harvey@utoronto.ca', 'Harvey', 'Donnelley', 'abc123', current_timestamp),
(2, 'kento@utoronto.ca', 'Kento', 'Takeda', 'def456', current_timestamp),
(3, 'paul@utoronto.ca', 'Paul', 'Bangalan', 'abc123', current_timestamp),
(4, 'darlyn@utoronto.ca', 'Darlyn', 'Nguyen', 'def456', current_timestamp),
(5, 'nyko@utoronto.ca', 'Nyko', 'Dionisio', 'abc123', current_timestamp),
(6, 'nicholas@utoronto.ca', 'Nicholas', 'Mckee', 'def456', current_timestamp),
(7, 'humraj@utoronto.ca', 'Humraj', 'Bhoday', 'abc123', current_timestamp),
(8, 'zhihao@utoronto.ca', 'Zhihao', 'Lim', 'def456', current_timestamp);

DELETE FROM tests;
INSERT INTO tests 
VALUES (1, 1, 1, 'MCQ', '10', '30', current_timestamp),
(2, 2, 1, 'MCQ', '20', '60', current_timestamp),
(3, 1, 2, 'Multiselect', '10', '20', current_timestamp),
(4, 3, 3, 'Open-ended', '5', '60', current_timestamp),
(5, 3, 4, 'MCQ', '30', '60', current_timestamp),
(6, 4, 3, 'Multiselect', '10', '70', current_timestamp),
(7, 4, 2, 'MCQ', '20', '40', current_timestamp),
(8, 5, 4, 'Open-ended', '3', '50', current_timestamp),
(9, 6, 1, 'MCQ', '1', '50', current_timestamp),
(10, 7, 2, 'MCQ', '12', '25', current_timestamp);

DELETE FROM solutions;
INSERT INTO solutions
VALUES (1, 1, 2, 5, 10, 20, 1, current_timestamp),
(2, 1, 3, 10, 10, 20, 2, current_timestamp),
(3, 2, 3, 15, 10, 20, 3, current_timestamp),
(4, 2, 2, 2, 10, 20, 4, current_timestamp),
(5, 3, 4, 10, 10, 20, 5, current_timestamp);

DELETE FROM messages;
INSERT INTO messages
VALUES (1, 1, 3, 1, 'great answer!', current_timestamp),
(2, 2, 4, 2, 'best answer!', current_timestamp),
(3, 3, 2, 3, 'excellent answer!', current_timestamp),
(4, 4, 2, 4, 'decent answer!', current_timestamp),
(5, 5, 2, 5, 'wrong answer!', current_timestamp),
(6, 1, 1, 1, 'great answer 2!', current_timestamp),
(7, 1, 4, 6, 'great answer 3!', current_timestamp),
(8, 2, 5, 2, 'best answer 2!', current_timestamp),
(9, 2, 2, 8, 'best answer 3!', current_timestamp),
(10, 3, 5, 3, 'excellent answer 2!', current_timestamp),
(11, 3, 6, 10, 'excellent answer 3!', current_timestamp);