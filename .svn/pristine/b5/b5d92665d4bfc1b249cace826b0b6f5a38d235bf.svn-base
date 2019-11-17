----合作表----
CREATE SEQUENCE seq_hkp_union_cooperate
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_union_cooperate
  OWNER TO hkp;

CREATE TABLE t_hkp_union_cooperate
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_union_cooperate' :: REGCLASS),
  coo_code        CHARACTER VARYING,
  coo_status        CHARACTER VARYING,
  house_code        CHARACTER VARYING,
  cus_code      CHARACTER VARYING,
  apply_code      CHARACTER VARYING,
  apply_type      CHARACTER VARYING,
  receive_code        CHARACTER VARYING,
  reject_reason       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_union_cooperate PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_union_cooperate
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_union_cooperate IS '合作表';
COMMENT ON COLUMN t_hkp_union_cooperate.coo_code IS '合作编码';
COMMENT ON COLUMN t_hkp_union_cooperate.coo_status IS '合作状态';
COMMENT ON COLUMN t_hkp_union_cooperate.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_union_cooperate.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_union_cooperate.apply_code IS '合作申请人编码';
COMMENT ON COLUMN t_hkp_union_cooperate.apply_type IS '申请的类型';
COMMENT ON COLUMN t_hkp_union_cooperate.receive_code IS '接收人编码';
COMMENT ON COLUMN t_hkp_union_cooperate.reject_reason IS '拒绝理由';

