---------房源收藏表---------
CREATE SEQUENCE seq_hkp_hos_favorite
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_favorite
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_favorite
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_hos_favorite' :: REGCLASS),
  emp_code        CHARACTER VARYING,
  house_code       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_favorite PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_favorite
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_favorite IS '房源收藏表';
COMMENT ON COLUMN t_hkp_hos_favorite.emp_code IS '用户编码';
COMMENT ON COLUMN t_hkp_hos_favorite.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_hos_favorite.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_hos_favorite.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_hos_favorite.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_hos_favorite.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_hos_favorite.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_hos_favorite.is_deleted IS '是否删除';
