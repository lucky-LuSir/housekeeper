--我是先用navicat创建的表 然后给id创建序列
-- ----------------------------
-- Table structure for t_hkp_apply_user_evaluate  用户合作评价表
-- ----------------------------
CREATE SEQUENCE seq_t_hkp_apply_user_evaluate
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_t_hkp_apply_user_evaluate
  OWNER TO hkp;

CREATE TABLE t_hkp_apply_user_evaluate (
  id  BIGINT NOT NULL DEFAULT nextval('seq_t_hkp_apply_user_evaluate' :: REGCLASS),
  to_user_code   CHARACTER VARYING,
  from_user_code CHARACTER VARYING  ,
  content CHARACTER VARYING  ,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_t_hkp_apply_user_evaluate PRIMARY KEY (id)
)WITH (OIDS = FALSE);

ALTER TABLE t_hkp_apply_user_evaluate
  OWNER TO hkp;

COMMENT ON COLUMN t_hkp_apply_user_evaluate.to_user_code IS '被评价者编码';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.from_user_code IS '评价者编码';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.content IS '评价内容';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.create_code IS '创建者编号';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.create_dept_code IS '创建者部门编号';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.last_update_code IS '最后修改者编号';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_apply_user_evaluate.is_deleted IS '是否删除 true删除了';
COMMENT ON TABLE  t_hkp_apply_user_evaluate IS '用户合作评价表';
