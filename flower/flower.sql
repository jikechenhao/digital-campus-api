
CREATE TABLE flower_t_group
(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    class_id UUID
);
CREATE UNIQUE INDEX study_evaluation_t_group_id_uindex ON flower_t_group (id);
CREATE TABLE flower_t_group_assess
(
    id UUID DEFAULT uuid_generate_v1(),
    group_id INTEGER,
    assess_date DATE,
    section_id UUID,
    subject_id UUID,
    penalize_score SMALLINT DEFAULT 0,
    award_score SMALLINT DEFAULT 0
);
COMMENT ON COLUMN flower_t_group_assess.assess_date IS '评价日期';
COMMENT ON COLUMN flower_t_group_assess.section_id IS '小节id';
COMMENT ON COLUMN flower_t_group_assess.subject_id IS '科目id';
COMMENT ON COLUMN flower_t_group_assess.penalize_score IS '批评总分';
COMMENT ON COLUMN flower_t_group_assess.award_score IS '表扬总分';
CREATE TABLE flower_t_section
(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    begin_time TIMESTAMP,
    end_time TIMESTAMP
);
COMMENT ON COLUMN flower_t_section.name IS '小节名称';
CREATE UNIQUE INDEX study_evaluation_t_section_id_uindex ON flower_t_section (id);
CREATE TABLE flower_t_student_assess
(
    id UUID DEFAULT uuid_generate_v1(),
    student_id UUID,
    group_id UUID,
    assess_date DATE,
    section_id UUID,
    subject_id UUID,
    penalize_score SMALLINT DEFAULT 0,
    award_score SMALLINT DEFAULT 0
);
COMMENT ON COLUMN flower_t_student_assess.group_id IS '小组id';
COMMENT ON COLUMN flower_t_student_assess.assess_date IS '评价日期
';
COMMENT ON COLUMN flower_t_student_assess.section_id IS '小节id';
COMMENT ON COLUMN flower_t_student_assess.subject_id IS '科目id';
COMMENT ON COLUMN flower_t_student_assess.penalize_score IS '批评总分';
COMMENT ON COLUMN flower_t_student_assess.award_score IS '奖励总分';
CREATE TABLE flower_t_student_group
(
    id UUID DEFAULT uuid_generate_v1(),
    student_id UUID,
    group_id UUID
);
CREATE FUNCTION uuid_generate_v1() RETURNS UUID;
CREATE FUNCTION uuid_generate_v1mc() RETURNS UUID;
CREATE FUNCTION uuid_generate_v3(namespace UUID, name TEXT) RETURNS UUID;
CREATE FUNCTION uuid_generate_v4() RETURNS UUID;
CREATE FUNCTION uuid_generate_v5(namespace UUID, name TEXT) RETURNS UUID;
CREATE FUNCTION uuid_nil() RETURNS UUID;
CREATE FUNCTION uuid_ns_dns() RETURNS UUID;
CREATE FUNCTION uuid_ns_oid() RETURNS UUID;
CREATE FUNCTION uuid_ns_url() RETURNS UUID;
CREATE FUNCTION uuid_ns_x500() RETURNS UUID;

、
;