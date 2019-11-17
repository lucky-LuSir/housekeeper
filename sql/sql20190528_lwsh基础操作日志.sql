


----基础操作日志表----
create sequence seq_hkp_base_operation
increment 1
minvalue 1
maxvalue 9223372036854775807
start 1
cache 1;
alter table seq_hkp_base_operation
  OWNER TO hkp;

create TABLE t_hkp_base_operation
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_base_operation' :: REGCLASS),
  opt              CHARACTER VARYING,
  opt_type         CHARACTER VARYING,
  url				CHARACTER VARYING,
  opt_code       CHARACTER VARYING,
  opt_name   	 CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_base_operation PRIMARY KEY (id)
) with (OIDS = FALSE
);
alter table t_hkp_base_operation
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_base_operation IS '基础操作日志表';
COMMENT ON COLUMN t_hkp_base_operation.opt IS '操作名';
COMMENT ON COLUMN t_hkp_base_operation.opt_type IS '操作类型';
COMMENT ON COLUMN t_hkp_base_operation.url IS '操作接口url';
COMMENT ON COLUMN t_hkp_base_operation.opt_code IS '操作人编号'
COMMENT ON COLUMN t_hkp_base_operation.opt_name IS '操作人';
COMMENT ON COLUMN t_hkp_base_operation.create_time IS '操作时间';

