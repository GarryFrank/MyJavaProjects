--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

-- Started on 2017-06-27 14:48:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 192 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 192
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 16664)
-- Name: classrooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE classrooms (
    classroom_id integer NOT NULL,
    number character varying(30)
);


ALTER TABLE classrooms OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16662)
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE classrooms_classroom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE classrooms_classroom_id_seq OWNER TO postgres;

--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 184
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE classrooms_classroom_id_seq OWNED BY classrooms.classroom_id;


--
-- TOC entry 183 (class 1259 OID 16651)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE groups (
    group_id integer NOT NULL,
    number character varying(30)
);


ALTER TABLE groups OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16649)
-- Name: groups_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE groups_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE groups_group_id_seq OWNER TO postgres;

--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 182
-- Name: groups_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE groups_group_id_seq OWNED BY groups.group_id;


--
-- TOC entry 191 (class 1259 OID 16688)
-- Name: lessonitems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE lessonitems (
    lessonitem_id integer NOT NULL,
    lesson character varying(50),
    classroom_id integer,
    group_id integer,
    teacher_id integer,
    date timestamp without time zone
);


ALTER TABLE lessonitems OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16686)
-- Name: lessonitems_lessonitem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lessonitems_lessonitem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE lessonitems_lessonitem_id_seq OWNER TO postgres;

--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 190
-- Name: lessonitems_lessonitem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lessonitems_lessonitem_id_seq OWNED BY lessonitems.lessonitem_id;


--
-- TOC entry 189 (class 1259 OID 16680)
-- Name: schedules; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE schedules (
    schedule_id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE schedules OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16678)
-- Name: schedules_schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedules_schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE schedules_schedule_id_seq OWNER TO postgres;

--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 188
-- Name: schedules_schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedules_schedule_id_seq OWNED BY schedules.schedule_id;


--
-- TOC entry 181 (class 1259 OID 16643)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE students (
    student_id integer NOT NULL,
    group_id integer,
    name character varying(50)
);


ALTER TABLE students OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16641)
-- Name: students_student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE students_student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE students_student_id_seq OWNER TO postgres;

--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 180
-- Name: students_student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE students_student_id_seq OWNED BY students.student_id;


--
-- TOC entry 187 (class 1259 OID 16672)
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE teachers (
    teacher_id integer NOT NULL,
    name character varying(90)
);


ALTER TABLE teachers OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16670)
-- Name: teachers_teacher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE teachers_teacher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE teachers_teacher_id_seq OWNER TO postgres;

--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 186
-- Name: teachers_teacher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE teachers_teacher_id_seq OWNED BY teachers.teacher_id;


--
-- TOC entry 2013 (class 2604 OID 16667)
-- Name: classroom_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY classrooms ALTER COLUMN classroom_id SET DEFAULT nextval('classrooms_classroom_id_seq'::regclass);


--
-- TOC entry 2012 (class 2604 OID 16654)
-- Name: group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups ALTER COLUMN group_id SET DEFAULT nextval('groups_group_id_seq'::regclass);


--
-- TOC entry 2016 (class 2604 OID 16691)
-- Name: lessonitem_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lessonitems ALTER COLUMN lessonitem_id SET DEFAULT nextval('lessonitems_lessonitem_id_seq'::regclass);


--
-- TOC entry 2015 (class 2604 OID 16683)
-- Name: schedule_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedules ALTER COLUMN schedule_id SET DEFAULT nextval('schedules_schedule_id_seq'::regclass);


--
-- TOC entry 2011 (class 2604 OID 16646)
-- Name: student_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students ALTER COLUMN student_id SET DEFAULT nextval('students_student_id_seq'::regclass);


--
-- TOC entry 2014 (class 2604 OID 16675)
-- Name: teacher_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teachers ALTER COLUMN teacher_id SET DEFAULT nextval('teachers_teacher_id_seq'::regclass);


--
-- TOC entry 2150 (class 0 OID 16664)
-- Dependencies: 185
-- Data for Name: classrooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO classrooms VALUES (1, 'R12');
INSERT INTO classrooms VALUES (2, 'R13');
INSERT INTO classrooms VALUES (3, 'R14');
INSERT INTO classrooms VALUES (4, 'R15');
INSERT INTO classrooms VALUES (5, 'R16');
INSERT INTO classrooms VALUES (6, 'R20');
INSERT INTO classrooms VALUES (7, 'R20');
INSERT INTO classrooms VALUES (8, 'R21');
INSERT INTO classrooms VALUES (9, 'R21');
INSERT INTO classrooms VALUES (10, 'R21');
INSERT INTO classrooms VALUES (11, 'R21');
INSERT INTO classrooms VALUES (12, 'R16');
INSERT INTO classrooms VALUES (13, 'R16');
INSERT INTO classrooms VALUES (14, 'R16');
INSERT INTO classrooms VALUES (15, 'R16');


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 184
-- Name: classrooms_classroom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('classrooms_classroom_id_seq', 15, true);


--
-- TOC entry 2148 (class 0 OID 16651)
-- Dependencies: 183
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO groups VALUES (155, 'Gr9');
INSERT INTO groups VALUES (157, 'Gr11');
INSERT INTO groups VALUES (1, 'Gr1');
INSERT INTO groups VALUES (3, 'Gr3');
INSERT INTO groups VALUES (162, 'Gr12');
INSERT INTO groups VALUES (4, 'Gr4');
INSERT INTO groups VALUES (163, 'Gr14');
INSERT INTO groups VALUES (164, 'Gr20');
INSERT INTO groups VALUES (166, 'Gr21');
INSERT INTO groups VALUES (177, 'Gr25');
INSERT INTO groups VALUES (178, 'Gr26');
INSERT INTO groups VALUES (189, 'Gr28');
INSERT INTO groups VALUES (190, 'Gr29');
INSERT INTO groups VALUES (24, 'Gr5');
INSERT INTO groups VALUES (26, 'Gr6');
INSERT INTO groups VALUES (28, 'Gr7');
INSERT INTO groups VALUES (29, 'Gr10');
INSERT INTO groups VALUES (207, 'Gr31');


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 182
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('groups_group_id_seq', 216, true);


--
-- TOC entry 2156 (class 0 OID 16688)
-- Dependencies: 191
-- Data for Name: lessonitems; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO lessonitems VALUES (1, 'English', 1, 1, 1, '2017-05-01 09:00:00');
INSERT INTO lessonitems VALUES (2, 'Programming', 15, 1, 2, '2017-05-01 10:00:00');


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 190
-- Name: lessonitems_lessonitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lessonitems_lessonitem_id_seq', 3, true);


--
-- TOC entry 2154 (class 0 OID 16680)
-- Dependencies: 189
-- Data for Name: schedules; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO schedules VALUES (1, 'ScheduleGr1');
INSERT INTO schedules VALUES (2, 'ScheduleGr2');


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 188
-- Name: schedules_schedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('schedules_schedule_id_seq', 2, true);


--
-- TOC entry 2146 (class 0 OID 16643)
-- Dependencies: 181
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO students VALUES (1, 1, 'Ivan Ivanov');
INSERT INTO students VALUES (188, 0, 'Andy Johnson11');
INSERT INTO students VALUES (3, 2, 'John Johnson');
INSERT INTO students VALUES (4, 1, 'Inna Tarapatova');
INSERT INTO students VALUES (190, 0, 'Andy Johnson11');
INSERT INTO students VALUES (192, 0, 'Andy Johnson11');
INSERT INTO students VALUES (91, 1, 'Igor Grachev');
INSERT INTO students VALUES (92, 1, 'Igor Grach');
INSERT INTO students VALUES (93, 1, 'Igor Grach');
INSERT INTO students VALUES (94, 1, 'Igor Grach');
INSERT INTO students VALUES (95, 1, 'Igor Grach');
INSERT INTO students VALUES (96, 1, 'Igor Grach');
INSERT INTO students VALUES (97, 1, 'Igor Grach');
INSERT INTO students VALUES (28, 3, 'Alex Kovalev');
INSERT INTO students VALUES (29, 3, 'Alex Kovalev');
INSERT INTO students VALUES (43, 3, 'Tver');
INSERT INTO students VALUES (44, 3, 'Tver');
INSERT INTO students VALUES (45, 3, 'Tver');
INSERT INTO students VALUES (46, 3, 'Tver');
INSERT INTO students VALUES (47, 3, 'Tver');
INSERT INTO students VALUES (48, 3, 'Tver');
INSERT INTO students VALUES (49, 3, 'Tver');
INSERT INTO students VALUES (56, 3, 'Garry Lineker');
INSERT INTO students VALUES (57, 3, 'Garry Lineker');
INSERT INTO students VALUES (58, 3, 'Garry Lineker');
INSERT INTO students VALUES (59, 3, 'Garry Lineker');
INSERT INTO students VALUES (60, 3, 'Garry Lineker');
INSERT INTO students VALUES (61, 3, 'Garry Lineker');
INSERT INTO students VALUES (62, 3, 'Garry Lineker');
INSERT INTO students VALUES (63, 3, 'Garry Lineker');
INSERT INTO students VALUES (64, 3, 'Garry Lineker');
INSERT INTO students VALUES (65, 3, 'John Newman');
INSERT INTO students VALUES (66, 3, 'John Newman');
INSERT INTO students VALUES (67, 3, 'John Newman');
INSERT INTO students VALUES (68, 3, 'John Newman');
INSERT INTO students VALUES (69, 3, 'John Newman');
INSERT INTO students VALUES (70, 2, 'Dima Shehtman');
INSERT INTO students VALUES (71, 2, 'Dima Shehtman');
INSERT INTO students VALUES (98, 1, 'Igor Grach');
INSERT INTO students VALUES (126, 147, 'Vadim Vetrov');
INSERT INTO students VALUES (127, 148, 'Vadim Vetrov');
INSERT INTO students VALUES (128, 149, 'Vadim Vetrov');
INSERT INTO students VALUES (129, 150, 'Vadim Vetrov');
INSERT INTO students VALUES (130, 151, 'Vadim Vetrov');
INSERT INTO students VALUES (133, 152, 'Vadim Vetrov');
INSERT INTO students VALUES (134, 153, 'Vadim Vetrov');
INSERT INTO students VALUES (135, 155, 'Vadim Vetrov');
INSERT INTO students VALUES (136, 0, 'Vadim Vetrov');
INSERT INTO students VALUES (137, 157, 'Kiril Lavrov');
INSERT INTO students VALUES (138, 0, 'Kiril Lavrov');
INSERT INTO students VALUES (139, 0, 'Kiril Lavrov');
INSERT INTO students VALUES (140, 0, 'Kiril Lavrov');
INSERT INTO students VALUES (141, 0, 'Kiril Lavrov');
INSERT INTO students VALUES (142, 162, 'Kiril Lavrov1');
INSERT INTO students VALUES (143, 163, 'Kiril Lavrov1');
INSERT INTO students VALUES (144, 164, 'Andy Johnson');
INSERT INTO students VALUES (145, 0, 'Andy Johnson');
INSERT INTO students VALUES (169, 189, 'Andy Johnson9');
INSERT INTO students VALUES (189, 0, 'Andy Johnson11');
INSERT INTO students VALUES (191, 0, 'Andy Johnson11');
INSERT INTO students VALUES (187, 207, 'Andy Johnson11');


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 180
-- Name: students_student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('students_student_id_seq', 192, true);


--
-- TOC entry 2152 (class 0 OID 16672)
-- Dependencies: 187
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO teachers VALUES (1, 'Mark Zaharov');
INSERT INTO teachers VALUES (2, 'Leon Kruglov');
INSERT INTO teachers VALUES (3, 'Alex Kuzin');
INSERT INTO teachers VALUES (4, 'Leon Kruglov');
INSERT INTO teachers VALUES (5, 'Leon Kruglov');
INSERT INTO teachers VALUES (6, 'Leon Kruglov');


--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 186
-- Name: teachers_teacher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('teachers_teacher_id_seq', 6, true);


--
-- TOC entry 2024 (class 2606 OID 16669)
-- Name: classrooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY classrooms
    ADD CONSTRAINT classrooms_pkey PRIMARY KEY (classroom_id);


--
-- TOC entry 2020 (class 2606 OID 16740)
-- Name: constraint_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT constraint_name UNIQUE (number);


--
-- TOC entry 2022 (class 2606 OID 16656)
-- Name: groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- TOC entry 2030 (class 2606 OID 16693)
-- Name: lessonitems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lessonitems
    ADD CONSTRAINT lessonitems_pkey PRIMARY KEY (lessonitem_id);


--
-- TOC entry 2028 (class 2606 OID 16685)
-- Name: schedules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedules
    ADD CONSTRAINT schedules_pkey PRIMARY KEY (schedule_id);


--
-- TOC entry 2018 (class 2606 OID 16648)
-- Name: students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (student_id);


--
-- TOC entry 2026 (class 2606 OID 16677)
-- Name: teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (teacher_id);


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-06-27 14:48:58

--
-- PostgreSQL database dump complete
--

