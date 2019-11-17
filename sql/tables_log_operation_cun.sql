----房源地址表----

CREATE SEQUENCE seq_hkp_log_operation
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_log_operation
  OWNER TO hkp;

CREATE TABLE t_hkp_log_operation
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_log_operation' :: REGCLASS),
  opt_code         CHARACTER VARYING,
  opt_type         CHARACTER VARYING,
  biz_code         CHARACTER VARYING,
  url              CHARACTER VARYING,
  content          CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_log_operation PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_log_operation
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_log_operation IS '操作日志表';
COMMENT ON COLUMN t_hkp_log_operation.opt_code IS '操作编码';
COMMENT ON COLUMN t_hkp_log_operation.opt_type IS '操作类型';
COMMENT ON COLUMN t_hkp_log_operation.biz_code IS '业务编码';
COMMENT ON COLUMN t_hkp_log_operation.content IS '操作描述';

